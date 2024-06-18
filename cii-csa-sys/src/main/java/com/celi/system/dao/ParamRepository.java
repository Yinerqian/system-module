package com.celi.system.dao;

import com.celi.system.entity.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: xining
 * @Date: 2024/06/17 15:51
 * @Description 参数Dao层
 */
@Repository
public interface ParamRepository extends JpaRepository<Param, String> {

    Page<Param> findAllByConfigNameContaining(String configName, Pageable pageable);

    void deleteByConfigId(String configId);

    Param findAllByConfigId(String configId);

    List<Param> findByConfigCode(String configCode);
}
