package com.tp.service;


import java.util.List;

import com.tp.entity.PhongEntity;
import com.tp.entity.QuantamEntity;
import com.tp.entity.TaikhoanEntity;

public interface QuantamService {
    <S extends QuantamEntity> S SavedRequest(S entity);
    void deleteById(int id);
    QuantamEntity findByTaikhoanAndPhong(TaikhoanEntity taikhoanEntity, PhongEntity phongEntity);
    List<QuantamEntity> findByTaikhoan(TaikhoanEntity taikhoanEntity);
}
