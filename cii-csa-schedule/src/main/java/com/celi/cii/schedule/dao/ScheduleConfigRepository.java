package com.celi.cii.schedule.dao;

import com.celi.cii.schedule.entity.ScheduleConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jiangshengjun
 * @date 2023/10/10
 */

@Repository
public interface ScheduleConfigRepository extends JpaRepository<ScheduleConfig, Integer>, JpaSpecificationExecutor<ScheduleConfig> {

    List<ScheduleConfig> findByStatus(Integer status);
}
