package com.tp.service;

import java.util.List;

import com.tp.entity.BinhluanEntity;

public interface BinhluanService {
    <S extends BinhluanEntity> S saveRequest(S entity);
    List<BinhluanEntity> findByPhongId(int id);
    BinhluanEntity findById(int id);
}
