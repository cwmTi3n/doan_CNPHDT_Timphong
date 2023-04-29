package com.tp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tp.entity.binhluanEntity;
import com.tp.repository.binhluanRepository;
import com.tp.service.binhluanService;

@Service
public class binhluanServiceImpl implements binhluanService{
    @Autowired
    binhluanRepository binhluanRepository;

    @Override
    public <S extends binhluanEntity> S saveRequest(S entity) {
        return binhluanRepository.save(entity);
    }

    @Override
    public List<binhluanEntity> findByPhongId(int id) {
        Sort sort = Sort.by(Sort.Direction.DESC, "thoigian");
        return binhluanRepository.findByPhongId(id, sort);
    }

    @Override
    public binhluanEntity findById(int id) {
        return binhluanRepository.findById(id).orElse(null);
    }
    
}
