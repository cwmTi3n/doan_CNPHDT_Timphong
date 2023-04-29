package com.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tp.entity.hinhanhEntity;
import com.tp.entity.phongEntity;

public interface hinhanhRepository extends JpaRepository<hinhanhEntity, Integer>{
    List<hinhanhEntity> findByPhong(phongEntity phongEntity);
    hinhanhEntity findByUrl(String url);
    hinhanhEntity findById(int id);
}
