package com.tp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp.entity.loaiphongEntity;
import com.tp.entity.phongEntity;
import com.tp.entity.taikhoanEntity;

public interface phongRepository extends JpaRepository<phongEntity, Integer>{
    @Query("select p from phongEntity p where p.trangthai = :trangthai and (p.ten like %:keyword% or p.mota like %:keyword%)")
    Page<phongEntity> searchPhong(boolean trangthai, String keyword, Pageable pageable);

    @Query("select p from phongEntity p where p.trangthai = :trangthai and (p.ten like %:keyword% or p.mota like %:keyword%) and p.loaiphong.loaiphongId = :loaiphongId and diachi like %:tinh% and diachi like %:huyen% and diachi like %:xa%")
    Page<phongEntity> filterPhong(boolean trangthai, String keyword ,Integer loaiphongId, String tinh, String huyen, String xa, Pageable pageable);

    @Query("select p from phongEntity p where p.trangthai = :trangthai and (p.ten like %:keyword% or p.mota like %:keyword%) and diachi like %:tinh% and diachi like %:huyen% and diachi like %:xa%")
    Page<phongEntity> filterPhongNoLoaiphong(boolean trangthai, String keyword, String tinh, String huyen, String xa, Pageable pageable);
    
    Page<phongEntity> findByLoaiphongAndTrangthai(loaiphongEntity loaiphongEntity, boolean trangthai, Pageable pageable);

    Page<phongEntity> findByTrangthai(boolean trangthai, Pageable pageable);

    phongEntity findByTrangthaiAndPhongId(boolean trangthai, Integer phongId);

    //seller
    @Query("select p from phongEntity p where p.trangthai = true and p.taikhoan.taikhoanId = :id and (p.ten like %:keyword% or p.mota like %:keyword%)")
    Page<phongEntity> sellerSearchphong(Integer id, String keyword, Pageable pageable);

    Page<phongEntity> findByTaikhoanAndTrangthai(taikhoanEntity taikhoanEntity, boolean trangthai, Pageable pageable);

    @Query("select p from phongEntity p where p.taikhoan.taikhoanId = :id and p.trangthai = true and (p.ten like %:keyword% or p.mota like %:keyword%) and p.loaiphong.loaiphongId = :loaiphongId and diachi like %:tinh% and diachi like %:huyen% and diachi like %:xa%")
    Page<phongEntity> sellerFilterPhong(Integer id, String keyword ,Integer loaiphongId, String tinh, String huyen, String xa, Pageable pageable);

    @Query("select p from phongEntity p where p.taikhoan.taikhoanId = :id and p.trangthai = true and (p.ten like %:keyword% or p.mota like %:keyword%) and diachi like %:tinh% and diachi like %:huyen% and diachi like %:xa%")
    Page<phongEntity> sellerFilterPhongNoLoaiphong(Integer id, String keyword, String tinh, String huyen, String xa, Pageable pageable);
}
