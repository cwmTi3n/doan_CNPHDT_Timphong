package com.tp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.entity.hinhanhEntity;
import com.tp.entity.phongEntity;
import com.tp.repository.hinhanhRepository;
import com.tp.service.hinhanhService;

@Service
public class hinhanhServiceImpl implements hinhanhService{
    @Autowired
    hinhanhRepository hinhanhRepository;
    @Override
    public <S extends hinhanhEntity> S SavedRequest(S entity) {
        return hinhanhRepository.save(entity);
    }

    @Override
    public void deleteById(int id) {
        hinhanhRepository.deleteById(id);
    }

    @Override
    public List<hinhanhEntity> findByPhong(phongEntity phongEntity) {
        return hinhanhRepository.findByPhong(phongEntity);
    }

    @Override
    public hinhanhEntity findByUrl(String url) {
        return hinhanhRepository.findByUrl(url);
    }

    @Override
    public hinhanhEntity findById(int id) {
        return hinhanhRepository.findById(id);
    }
    
}
