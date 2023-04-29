package com.tp.service;

import java.util.List;

import com.tp.entity.binhluanEntity;

public interface binhluanService {
    <S extends binhluanEntity> S saveRequest(S entity);
    List<binhluanEntity> findByPhongId(int id);
    binhluanEntity findById(int id);
}
