package com.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp.entity.lichhenEntity;
import com.tp.entity.taikhoanEntity;
import com.tp.entity.ttlichhenEnum;

public interface lichhenRepository extends JpaRepository<lichhenEntity, Integer>{
    @Query("select lh from lichhenEntity lh where lh.taikhoan.taikhoanId = :taikhoanId and lh.phong.phongId = :phongId")
    lichhenEntity findByTaikhoanIdAndPhongId(int taikhoanId, int phongId);
    List<lichhenEntity> findByTrangthaiAndTaikhoan(ttlichhenEnum trangthai, taikhoanEntity taikhoanEntity);
    @Query("select lh from lichhenEntity lh where lh.phong.taikhoan.taikhoanId = :taikhoanId and lh.trangthai = :trangthai")
    List<lichhenEntity> findBySellerAndTrangthai(int taikhoanId, ttlichhenEnum trangthai);
}
