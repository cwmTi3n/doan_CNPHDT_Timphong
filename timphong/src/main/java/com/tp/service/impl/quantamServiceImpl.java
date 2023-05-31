package com.tp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.entity.PhongEntity;
import com.tp.entity.QuantamEntity;
import com.tp.entity.TaikhoanEntity;
import com.tp.repository.QuantamRepository;
import com.tp.service.QuantamService;

@Service
public class QuantamServiceImpl implements QuantamService {
    @Autowired
    QuantamRepository quantamRepository;

    @Override
    public <S extends QuantamEntity> S SavedRequest(S entity) {
        return quantamRepository.save(entity);
    }

    @Override
    public void deleteById(int id) {
        quantamRepository.deleteById(id);
    }

    @Override
    public QuantamEntity findByTaikhoanAndPhong(TaikhoanEntity taikhoanEntity, PhongEntity phongEntity) {
        return quantamRepository.findByTaikhoanAndPhong(taikhoanEntity, phongEntity);
    }

    @Override
    public List<QuantamEntity> findByTaikhoan(TaikhoanEntity taikhoanEntity) {
        return quantamRepository.findByTaikhoan(taikhoanEntity);
    }

}
