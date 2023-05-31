package com.tp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.entity.TinNhanEntity;
import com.tp.repository.TinNhanRepository;
import com.tp.service.TinNhanService;

import java.util.List;

@Service
public class TinNhanServiceImpl implements TinNhanService{
    @Autowired
    TinNhanRepository tinNhanRepository;
    @Override
    public <S extends TinNhanEntity> S SavedRequest(S entity) {
        return tinNhanRepository.save(entity);
    }

    @Override
    public List<TinNhanEntity> getListChatByTaikhoan(int taikhoanId) {
        return tinNhanRepository.getByTaikhoan(taikhoanId);
    }

    @Override
    public List<TinNhanEntity> getTinnhanForRoom(int nguoiguiId, int nguoinhanId) {
        return tinNhanRepository.getByNguoiguiIdAndNguoinhanId(nguoiguiId, nguoinhanId);
    }

    @Override
    public int countTinnhanChuaxem(int taikhoanId) {
        return tinNhanRepository.countTinnhanChuaxem(taikhoanId, 0);
    }

    @Override
    public TinNhanEntity getTinnhanByTinnhanId(int tinnhanId) {
        return tinNhanRepository.findById(tinnhanId).orElse(null);
    }

    @Override
    public List<TinNhanEntity> getTinnhanBySenderAndReceiver(int nguoiguiId, int nguoinhanId) {
        return tinNhanRepository.getBySenderAndReceiver(nguoiguiId, nguoinhanId, 0);
    }

}
