package com.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tp.entity.HinhanhEntity;
import com.tp.entity.PhongEntity;

public interface HinhanhRepository extends JpaRepository<HinhanhEntity, Integer>{
    List<HinhanhEntity> findByPhong(PhongEntity phongEntity);
    HinhanhEntity findByUrl(String url);
    HinhanhEntity findById(int id);
}
