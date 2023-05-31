package com.tp.service;

import java.util.List;

import com.tp.entity.HinhanhEntity;
import com.tp.entity.PhongEntity;

public interface HinhanhService {
    <S extends HinhanhEntity> S SavedRequest(S entity);
    void deleteById(int id);
    List<HinhanhEntity> findByPhong(PhongEntity phongEntity);
    HinhanhEntity findByUrl(String url);
    HinhanhEntity findById(int id);
}
