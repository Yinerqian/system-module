package com.celi.cii.schedule.service;

import cn.hutool.core.date.DateUtil;
import com.celi.cii.common.dto.PageInfo;
import com.celi.cii.schedule.common.ScConstant;
import com.celi.cii.schedule.dao.ScheduleConfigRepository;
import com.celi.cii.schedule.entity.ScheduleConfig;
import com.celi.cii.schedule.runner.CornTaskRegistrar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author jiangshengjun
 * @date 2023/10/10
 */

@Service
public class ScheduleConfigService {

    private ScheduleConfigRepository scheduleConfigRepository;
    private CornTaskRegistrar cornTaskRegistrar;

    public ScheduleConfigService(ScheduleConfigRepository scheduleConfigRepository, CornTaskRegistrar cornTaskRegistrar) {
        this.scheduleConfigRepository = scheduleConfigRepository;
        this.cornTaskRegistrar = cornTaskRegistrar;
    }

    @Transactional
    public void saveNewTask(ScheduleConfig scheduleConfig) {
        scheduleConfig.setUpdateDate(DateUtil.date());
        scheduleConfig.setCreateDate(DateUtil.date());
        scheduleConfigRepository.save(scheduleConfig);
        cornTaskRegistrar.addCronTask(scheduleConfig);
    }

    @Transactional
    public void updateTask(ScheduleConfig scheduleConfig) {
        scheduleConfig.setUpdateDate(DateUtil.date());
        scheduleConfigRepository.save(scheduleConfig);
        cornTaskRegistrar.addCronTask(scheduleConfig);
    }

    @Transactional
    public void delTask(Integer jobId) {
        scheduleConfigRepository.deleteById(jobId);
        cornTaskRegistrar.removeCronTask(jobId);
    }

    @Transactional
    public void jobStatusUpdate(Integer jobId, Integer status) {
        Optional<ScheduleConfig> jobConfigOptional = scheduleConfigRepository.findById(jobId);
        if (jobConfigOptional.isPresent()) {
            ScheduleConfig jobConfig = jobConfigOptional.get();
            jobConfig.setStatus(status);
            scheduleConfigRepository.save(jobConfig);
            if (status == ScConstant.SCHEDULE_STATUS_ENABLE) {
                cornTaskRegistrar.addCronTask(jobConfig);
            } else {
                cornTaskRegistrar.removeCronTask(jobId);
            }
        }
    }

    public PageInfo pageSchedule(Integer pageNum, Integer pageSize, Integer status) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "updateDate"));
        Page<ScheduleConfig> result = scheduleConfigRepository.findAll(new Specification<ScheduleConfig>() {
            @Override
            public Predicate toPredicate(Root<ScheduleConfig> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if (null != status) {
                    predicateList.add(cb.equal(root.get("status"), status));
                }
                return query.where(predicateList.toArray(new Predicate[predicateList.size()])).getRestriction();
            }
        }, pageable);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(pageNum);
        pageInfo.setTotal(result.getTotalElements());
        pageInfo.setList(result.getContent());
        return pageInfo;
    }

    public List<ScheduleConfig> queryAllNormalSchedule() {
        return scheduleConfigRepository.findByStatus(ScheduleConfig.DELETED);
    }
}
