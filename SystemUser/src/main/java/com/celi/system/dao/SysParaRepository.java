package com.celi.system.dao;

import com.celi.system.entity.SysPara;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: Changaowen
 * @Date: 2021/12/9 19:55
 */
@Repository
public interface SysParaRepository extends JpaRepository<SysPara, String> {

    void deleteByParaId(String paraId);

    Page<SysPara> findAllByParaNameContainingOrParaValueContaining(String paraName, String paraValue, Pageable pageable);

    Page<SysPara> findAll(Pageable pageable);

    SysPara findByParaId(String paraId);

    SysPara findByParaCode(@Param("paraCode") String paraCode);

}
