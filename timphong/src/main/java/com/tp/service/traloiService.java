package com.tp.service;

import com.tp.entity.TraloiEntity;

public interface TraloiService {
    <S extends TraloiEntity> S SaveRequest(S entity);
    TraloiEntity findById(int id);
}
