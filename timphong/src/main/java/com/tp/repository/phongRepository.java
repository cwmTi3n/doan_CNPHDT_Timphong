package com.tp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp.entity.LoaiphongEntity;
import com.tp.entity.PhongEntity;
import com.tp.entity.TaikhoanEntity;

public interface PhongRepository extends JpaRepository<PhongEntity, Integer>{
    @Query("select p from PhongEntity p where p.trangthai = :trangthai and (p.ten like %:keyword% or p.mota like %:keyword%)")
    Page<PhongEntity> searchPhong(boolean trangthai, String keyword, Pageable pageable);

    @Query("select p from PhongEntity p where p.trangthai = :trangthai and (p.ten like %:keyword% or p.mota like %:keyword%) and p.loaiphong.loaiphongId = :loaiphongId and diachi like %:tinh% and diachi like %:huyen% and diachi like %:xa%")
    Page<PhongEntity> filterPhong(boolean trangthai, String keyword , Integer loaiphongId, String tinh, String huyen, String xa, Pageable pageable);

    @Query("select p from PhongEntity p where p.trangthai = :trangthai and (p.ten like %:keyword% or p.mota like %:keyword%) and diachi like %:tinh% and diachi like %:huyen% and diachi like %:xa%")
    Page<PhongEntity> filterPhongNoLoaiphong(boolean trangthai, String keyword, String tinh, String huyen, String xa, Pageable pageable);
    
    Page<PhongEntity> findByLoaiphongAndTrangthai(LoaiphongEntity loaiphongEntity, boolean trangthai, Pageable pageable);

    Page<PhongEntity> findByTrangthai(boolean trangthai, Pageable pageable);

    PhongEntity findByTrangthaiAndPhongId(boolean trangthai, Integer phongId);

    //seller
    @Query("select p from PhongEntity p where p.trangthai = true and p.taikhoan.taikhoanId = :id and (p.ten like %:keyword% or p.mota like %:keyword%)")
    Page<PhongEntity> sellerSearchphong(Integer id, String keyword, Pageable pageable);

    Page<PhongEntity> findByTaikhoanAndTrangthai(TaikhoanEntity taikhoanEntity, boolean trangthai, Pageable pageable);

    @Query("select p from PhongEntity p where p.taikhoan.taikhoanId = :id and p.trangthai = true and (p.ten like %:keyword% or p.mota like %:keyword%) and p.loaiphong.loaiphongId = :loaiphongId and diachi like %:tinh% and diachi like %:huyen% and diachi like %:xa%")
    Page<PhongEntity> sellerFilterPhong(Integer id, String keyword , Integer loaiphongId, String tinh, String huyen, String xa, Pageable pageable);

    @Query("select p from PhongEntity p where p.taikhoan.taikhoanId = :id and p.trangthai = true and (p.ten like %:keyword% or p.mota like %:keyword%) and diachi like %:tinh% and diachi like %:huyen% and diachi like %:xa%")
    Page<PhongEntity> sellerFilterPhongNoLoaiphong(Integer id, String keyword, String tinh, String huyen, String xa, Pageable pageable);

    List<PhongEntity> findByTrangthai(boolean trangthai);
}
