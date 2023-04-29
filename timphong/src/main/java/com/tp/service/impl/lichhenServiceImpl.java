package com.tp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.entity.lichhenEntity;
import com.tp.entity.taikhoanEntity;
import com.tp.entity.ttlichhenEnum;
import com.tp.repository.lichhenRepository;
import com.tp.service.lichhenService;

@Service
public class lichhenServiceImpl implements lichhenService{

    @Autowired
    lichhenRepository lichhenRepository;
    @Override
    public <S extends lichhenEntity> S SavedRequest(S entity) {
        return lichhenRepository.save(entity);
    }

    @Override
    public List<lichhenEntity> findAll() {
        return lichhenRepository.findAll();
    }

    @Override
    public List<lichhenEntity> findByTaikhoanId(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByTaikhoanId'");
    }

    @Override
    public lichhenEntity findByTaikhoanAndPhong(int taikhoanId, int phongId) {
        return lichhenRepository.findByTaikhoanIdAndPhongId(taikhoanId, phongId);
    }

    @Override
    public List<lichhenEntity> findByTrangthai(ttlichhenEnum trangthai, taikhoanEntity taikhoanEntity) {
        return lichhenRepository.findByTrangthaiAndTaikhoan(trangthai, taikhoanEntity);
    }

    @Override
    public void deleteById(int id) {
        lichhenRepository.deleteById(id);
    }

    @Override
    public lichhenEntity findById(int id) {
        return lichhenRepository.findById(id).orElse(null);
    }

    @Override
    public List<lichhenEntity> findBySellerAndTrangthai(int taikhoanId, ttlichhenEnum trangthai) {
        return lichhenRepository.findBySellerAndTrangthai(taikhoanId, trangthai);
    }
    
}
