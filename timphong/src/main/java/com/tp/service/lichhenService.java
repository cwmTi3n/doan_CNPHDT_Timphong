package com.tp.service;

import java.util.List;

import com.tp.entity.LichhenEntity;
import com.tp.entity.TaikhoanEntity;
import com.tp.entity.ttlichhenEnum;

public interface LichhenService {
    <S extends LichhenEntity> S SavedRequest(S entity);
    List<LichhenEntity> findAll();
    List<LichhenEntity> findByTaikhoanId(int id);
    LichhenEntity findByTaikhoanAndPhong(int taikhoanId, int phongId);
    List<LichhenEntity> findByTrangthai(ttlichhenEnum trangthai, TaikhoanEntity taikhoanEntity);
    void deleteById(int id);
    LichhenEntity findById(int id);
    List<LichhenEntity> findBySellerAndTrangthai(int taikhoanId, ttlichhenEnum trangthai);
}
