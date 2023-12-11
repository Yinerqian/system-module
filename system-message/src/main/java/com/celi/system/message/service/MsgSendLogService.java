package com.celi.system.message.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.celi.cii.base.exception.ServiceException;
import com.celi.cii.base.utils.ClientUtils;
import com.celi.cii.base.utils.ServiceUtil;
import com.celi.system.message.dao.MsgSendLogDao;
import com.celi.system.message.entity.MsgSendLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class MsgSendLogService {

    @Resource
    private MsgSendLogDao msgSendLogDao;

    public void validParam(Object o) {
        if (o == null) {
            throw new ServiceException("操作参数为空, 禁止操作");
        }
    }

    /**
     * 新增一条发送日志
     * @param sendLog
     */
    public MsgSendLog insertMsgSendLog(MsgSendLog sendLog) {
        validParam(sendLog);
        if (StrUtil.isBlank(sendLog.getRequestId())) {
            sendLog.setRequestId(IdUtil.simpleUUID());
        }
        ServiceUtil.insertRecord(sendLog);
        msgSendLogDao.insertMsgSendLog(sendLog);
        return sendLog;
    }

    /**
     * 删除发送日志
     * @param sendLog
     * @return
     */
    public MsgSendLog deleteMsgSendLog(MsgSendLog sendLog) {
        validParam(sendLog);
        ServiceUtil.updateRecord(sendLog);
        msgSendLogDao.deleteMsgSendLog(sendLog);
        return sendLog;
    }

    /**
     * 更改sendLog
     * @param sendLog
     * @return
     */
    public MsgSendLog updateMsgSendLog(MsgSendLog sendLog) {
        validParam(sendLog);
        ServiceUtil.updateRecord(sendLog);
        msgSendLogDao.updateMsgSendLog(sendLog);
        return sendLog;
    }

    /**
     * 根据主键获取记录信息
     * @param requestId
     * @return
     */
    public MsgSendLog getSendLogById(String requestId) {
        return msgSendLogDao.getMsgSendLogDetailById(requestId, ClientUtils.getCurrentTenantId());
    }

    /**
     * 分页获取发送日志
     * @param pageNum
     * @param pageSize
     * @param filter
     * @return
     */
    public PageInfo pageSendLog(int pageNum, int pageSize, MsgSendLog filter) {
        if (filter == null) {
            filter = new MsgSendLog();
        }
        filter.setTenantId(ClientUtils.getCurrentTenantId());
        PageHelper.startPage(pageNum, pageSize, StrUtil.isBlank(filter.getOrderBy()) ? "create_date desc" : filter.getOrderBy());
        return new PageInfo(msgSendLogDao.listMsgSendLog(filter));
    }

}
