package com.tp.service;

import com.tp.entity.TinNhanEntity;

import java.util.List;

public interface TinNhanService {
    <S extends TinNhanEntity> S SavedRequest(S entity);

    List<TinNhanEntity> getListChatByTaikhoan(int taikhoanId);

    List<TinNhanEntity> getTinnhanForRoom(int nguoiguiId, int nguoinhanId);

    int countTinnhanChuaxem(int taikhoanId);

    TinNhanEntity getTinnhanByTinnhanId(int tinnhanId);

	List<TinNhanEntity> getTinnhanBySenderAndReceiver(int nguoiguiId, int nguoinhanId);

}
