package com.tp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.entity.HinhanhEntity;
import com.tp.entity.PhongEntity;
import com.tp.repository.HinhanhRepository;
import com.tp.service.HinhanhService;

@Service
public class HinhanhServiceImpl implements HinhanhService {
    @Autowired
    HinhanhRepository hinhanhRepository;
    @Override
    public <S extends HinhanhEntity> S SavedRequest(S entity) {
        return hinhanhRepository.save(entity);
    }

    @Override
    public void deleteById(int id) {
        hinhanhRepository.deleteById(id);
    }

    @Override
    public List<HinhanhEntity> findByPhong(PhongEntity phongEntity) {
        return hinhanhRepository.findByPhong(phongEntity);
    }

    @Override
    public HinhanhEntity findByUrl(String url) {
        return hinhanhRepository.findByUrl(url);
    }

    @Override
    public HinhanhEntity findById(int id) {
        return hinhanhRepository.findById(id);
    }
    
}
