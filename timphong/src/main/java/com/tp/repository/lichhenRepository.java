package com.tp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp.entity.LichhenEntity;
import com.tp.entity.TaikhoanEntity;
import com.tp.entity.ttlichhenEnum;

public interface LichhenRepository extends JpaRepository<LichhenEntity, Integer>{
    @Query("select lh from LichhenEntity lh where lh.taikhoan.taikhoanId = :taikhoanId and lh.phong.phongId = :phongId")
    LichhenEntity findByTaikhoanIdAndPhongId(int taikhoanId, int phongId);
    List<LichhenEntity> findByTrangthaiAndTaikhoan(ttlichhenEnum trangthai, TaikhoanEntity taikhoanEntity);
    @Query("select lh from LichhenEntity lh where lh.phong.taikhoan.taikhoanId = :taikhoanId and lh.trangthai = :trangthai")
    List<LichhenEntity> findBySellerAndTrangthai(int taikhoanId, ttlichhenEnum trangthai);
}
