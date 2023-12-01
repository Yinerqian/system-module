package com.celi.system.message.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author celi
 * @date 2023-07-18 17:27
 * @desc 请求发送的参数
 */
@Data
public class MsgSendToUsersRequestDTO extends MsgSendRequestDTO {

    /**
     * 该请求实体可接受toUsers指定用户  多个用户用 '，'号分割
     */
    private String toUsers;

}
