package com.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tp.entity.PhongEntity;
import com.tp.entity.QuantamEntity;
import com.tp.entity.TaikhoanEntity;

public interface QuantamRepository extends JpaRepository<QuantamEntity, Integer>{
    QuantamEntity findByTaikhoanAndPhong(TaikhoanEntity taikhoan, PhongEntity phong);
    List<QuantamEntity> findByTaikhoan(TaikhoanEntity taikhoanEntity);
}
