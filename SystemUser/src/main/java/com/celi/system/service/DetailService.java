package com.celi.system.service;

import org.springframework.stereotype.Service;

@Service
public class DetailService {

    public String getDefaultPassword() {
        return "Celi@123456";
    }
}
