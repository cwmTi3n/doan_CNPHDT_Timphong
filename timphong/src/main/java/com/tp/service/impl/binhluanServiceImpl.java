package com.tp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tp.entity.BinhluanEntity;
import com.tp.repository.BinhluanRepository;
import com.tp.service.BinhluanService;

@Service
public class BinhluanServiceImpl implements BinhluanService {
    @Autowired
    BinhluanRepository binhluanRepository;

    @Override
    public <S extends BinhluanEntity> S saveRequest(S entity) {
        return binhluanRepository.save(entity);
    }

    @Override
    public List<BinhluanEntity> findByPhongId(int id) {
        Sort sort = Sort.by(Sort.Direction.DESC, "thoigian");
        return binhluanRepository.findByPhongId(id, sort);
    }

    @Override
    public BinhluanEntity findById(int id) {
        return binhluanRepository.findById(id).orElse(null);
    }
    
}
