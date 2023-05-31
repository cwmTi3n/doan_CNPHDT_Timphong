package com.tp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.entity.LichhenEntity;
import com.tp.entity.TaikhoanEntity;
import com.tp.entity.ttlichhenEnum;
import com.tp.repository.LichhenRepository;
import com.tp.service.LichhenService;

@Service
public class LichhenServiceImpl implements LichhenService {

    @Autowired
    LichhenRepository lichhenRepository;
    @Override
    public <S extends LichhenEntity> S SavedRequest(S entity) {
        return lichhenRepository.save(entity);
    }

    @Override
    public List<LichhenEntity> findAll() {
        return lichhenRepository.findAll();
    }

    @Override
    public List<LichhenEntity> findByTaikhoanId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByTaikhoanId'");
    }

    @Override
    public LichhenEntity findByTaikhoanAndPhong(int taikhoanId, int phongId) {
        return lichhenRepository.findByTaikhoanIdAndPhongId(taikhoanId, phongId);
    }

    @Override
    public List<LichhenEntity> findByTrangthai(ttlichhenEnum trangthai, TaikhoanEntity taikhoanEntity) {
        return lichhenRepository.findByTrangthaiAndTaikhoan(trangthai, taikhoanEntity);
    }

    @Override
    public void deleteById(int id) {
        lichhenRepository.deleteById(id);
    }

    @Override
    public LichhenEntity findById(int id) {
        return lichhenRepository.findById(id).orElse(null);
    }

    @Override
    public List<LichhenEntity> findBySellerAndTrangthai(int taikhoanId, ttlichhenEnum trangthai) {
        return lichhenRepository.findBySellerAndTrangthai(taikhoanId, trangthai);
    }
    
}
