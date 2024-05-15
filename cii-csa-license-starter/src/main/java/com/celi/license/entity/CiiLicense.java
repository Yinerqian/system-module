package com.celi.license.entity;

import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;

@Table(name = "cii_license")
@Entity
@Data
@ToString
@Log4j2
public class CiiLicense {

    /**
     * 主键
     */
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * license内容
     */
    @Column(name = "LICENSE_CONTENT", columnDefinition = "text")
    private String licenseContent;

    /**
     * license授权日期
     */
    @Column(name = "LICENSE_TS")
    private String licenseTs;

}
