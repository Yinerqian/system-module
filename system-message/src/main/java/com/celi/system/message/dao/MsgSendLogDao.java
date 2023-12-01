package com.celi.system.message.dao;

import com.celi.system.message.entity.MsgSendLog;
import com.celi.system.message.enums.MsgSendStatusEnum;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MsgSendLogDao {

    int insertMsgSendLog(MsgSendLog sendLog);

    int deleteMsgSendLog(MsgSendLog sendLog);

    int updateMsgSendLog(MsgSendLog sendLog);

    int updateMsgSendLogStatus(@Param("requestId") String requestId, @Param("status") MsgSendStatusEnum statusEnum);

    MsgSendLog getMsgSendLogById(@Param("requestId") String requestId);

    MsgSendLog getMsgSendLogDetailById(@Param("requestId") String requestId, @Param("tenantId") Integer tenantId);

    List<MsgSendLog> listMsgSendLog(@Param("filter") MsgSendLog filter);

}
