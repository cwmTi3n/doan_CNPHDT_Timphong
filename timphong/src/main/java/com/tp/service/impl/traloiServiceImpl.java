package com.tp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.entity.traloiEntity;
import com.tp.repository.traloiRepository;
import com.tp.service.traloiService;
@Service
public class traloiServiceImpl implements traloiService{
    @Autowired
    traloiRepository traloiRepository;
    @Override
    public <S extends traloiEntity> S SaveRequest(S entity) {
        return traloiRepository.save(entity);
    }
    @Override
    public traloiEntity findById(int id) {
        return traloiRepository.findById(id).orElse(null);
    }
    
}
