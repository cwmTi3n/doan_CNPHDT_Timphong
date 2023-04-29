package com.tp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.entity.phongEntity;
import com.tp.entity.quantamEntity;
import com.tp.entity.taikhoanEntity;
import com.tp.repository.quantamRepository;
import com.tp.service.quantamService;

@Service
public class quantamServiceImpl implements quantamService{
    @Autowired
    quantamRepository quantamRepository;

    @Override
    public <S extends quantamEntity> S SavedRequest(S entity) {
        return quantamRepository.save(entity);
    }

    @Override
    public void deleteById(int id) {
        quantamRepository.deleteById(id);
    }

    @Override
    public quantamEntity findByTaikhoanAndPhong(taikhoanEntity taikhoanEntity, phongEntity phongEntity) {
        return quantamRepository.findByTaikhoanAndPhong(taikhoanEntity, phongEntity);
    }

    @Override
    public List<quantamEntity> findByTaikhoan(taikhoanEntity taikhoanEntity) {
        return quantamRepository.findByTaikhoan(taikhoanEntity);
    }

}
