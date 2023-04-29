package com.tp.service;

import java.util.List;

import com.tp.entity.hinhanhEntity;
import com.tp.entity.phongEntity;

public interface hinhanhService {
    <S extends hinhanhEntity> S SavedRequest(S entity);
    void deleteById(int id);
    List<hinhanhEntity> findByPhong(phongEntity phongEntity);
    hinhanhEntity findByUrl(String url);
    hinhanhEntity findById(int id);
}
