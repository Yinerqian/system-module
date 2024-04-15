package com.celi.opc.client.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author changAoWen
 * @Date 2024/4/15
 * @Description 读取数据的入参
 */
@Data
public class ReadParams {

    /**==================================*
     *      读取PLC数据的属性              *
     *===================================*/
    private Integer nameSpaceId;

    private String identifier;

    /**==================================*
     *      读取串口数据的属性              *
     *      通过发送数据报文请求数据         *
     *===================================*/
    private String message;

    /**
     * 需要读取数据的点位编码列表
     */
    private List<String> pointCodes;

    private AcqPointConf acqPointConf;
}
