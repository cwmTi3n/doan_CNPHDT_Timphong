package com.tp.service;

import java.util.List;

import com.tp.entity.lichhenEntity;
import com.tp.entity.taikhoanEntity;
import com.tp.entity.ttlichhenEnum;

public interface lichhenService {
    <S extends lichhenEntity> S SavedRequest(S entity);
    List<lichhenEntity> findAll();
    List<lichhenEntity> findByTaikhoanId(int id);
    lichhenEntity findByTaikhoanAndPhong(int taikhoanId, int phongId);
    List<lichhenEntity> findByTrangthai(ttlichhenEnum trangthai, taikhoanEntity taikhoanEntity);
    void deleteById(int id);
    lichhenEntity findById(int id);
    List<lichhenEntity> findBySellerAndTrangthai(int taikhoanId, ttlichhenEnum trangthai);
}
