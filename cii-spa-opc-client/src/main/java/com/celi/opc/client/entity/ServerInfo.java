package com.celi.opc.client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author changAoWen
 * @Date 2024/4/15
 * @Description 描述
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfo {

    private String serverName;

    private String serverCode;

    private String serverId;

    private String serverIp;

    private String serverPort;

    private String userName;

    private String password;

    private Boolean active;

}