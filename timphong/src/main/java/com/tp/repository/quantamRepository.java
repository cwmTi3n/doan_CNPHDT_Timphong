package com.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tp.entity.phongEntity;
import com.tp.entity.quantamEntity;
import com.tp.entity.taikhoanEntity;

public interface quantamRepository extends JpaRepository<quantamEntity, Integer>{
    quantamEntity findByTaikhoanAndPhong(taikhoanEntity taikhoan, phongEntity phong);
    List<quantamEntity> findByTaikhoan(taikhoanEntity taikhoanEntity);
}
