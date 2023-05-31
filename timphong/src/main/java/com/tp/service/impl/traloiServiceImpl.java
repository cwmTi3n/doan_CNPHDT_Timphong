package com.tp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.entity.TraloiEntity;
import com.tp.repository.TraloiRepository;
import com.tp.service.TraloiService;
@Service
public class TraloiServiceImpl implements TraloiService {
    @Autowired
    TraloiRepository traloiRepository;
    @Override
    public <S extends TraloiEntity> S SaveRequest(S entity) {
        return traloiRepository.save(entity);
    }
    @Override
    public TraloiEntity findById(int id) {
        return traloiRepository.findById(id).orElse(null);
    }
    
}
