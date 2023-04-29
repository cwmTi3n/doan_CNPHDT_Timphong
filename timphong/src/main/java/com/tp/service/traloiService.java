package com.tp.service;

import com.tp.entity.traloiEntity;

public interface traloiService {
    <S extends traloiEntity> S SaveRequest(S entity);
    traloiEntity findById(int id);
}
