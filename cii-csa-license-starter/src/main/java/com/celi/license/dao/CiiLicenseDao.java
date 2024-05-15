package com.celi.license.dao;

import com.celi.license.entity.CiiLicense;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author jiangshengjun
 * @Date 2024/4/25
 * @Description
 */
public interface CiiLicenseDao extends JpaRepository<CiiLicense, String>  {
}
