package com.celi.system.service;

import org.springframework.stereotype.Service;

/**
 * @Author: changAoWen
 * @Date: 2024/1/11 11:37
 * @Description 描述
 */
@Service
public class DetailService {

    public String getDefaultPassword() {
        return "Celi@123456";
    }
}
