package com.tp.service;


import java.util.List;

import com.tp.entity.phongEntity;
import com.tp.entity.quantamEntity;
import com.tp.entity.taikhoanEntity;

public interface quantamService {
    <S extends quantamEntity> S SavedRequest(S entity);
    void deleteById(int id);
    quantamEntity findByTaikhoanAndPhong(taikhoanEntity taikhoanEntity, phongEntity phongEntity);
    List<quantamEntity> findByTaikhoan(taikhoanEntity taikhoanEntity);
}
