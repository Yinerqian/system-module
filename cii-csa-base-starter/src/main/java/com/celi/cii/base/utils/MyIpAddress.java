package com.celi.cii.base.utils;

import lombok.Data;

@Data
public class MyIpAddress {

    //国家
    private String country;

    //省
    private String province;

    //市
    private String city;

    //是否内网  true:内网   false：外网
    private boolean isInnerNet;

    public String toAddressString() {
        if (isInnerNet) {
            return "内网";
        } else {
            if (this.province != null && this.city != null) {
                return this.province + this.city;
            } else if (this.province != null) {
                return this.province;
            } else {
                return this.city;
            }
        }
    }
}
