package com.tp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tp.entity.TinNhanEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TinNhanRepository extends JpaRepository<TinNhanEntity, Integer>{
    @Query("SELECT t1 " +
            "FROM TinNhanEntity t1 " +
            "WHERE t1.tinnhanId = (SELECT MAX(t2.tinnhanId) FROM TinNhanEntity t2 WHERE (t2.nguoinhan.taikhoanId = :taikhoanId or t2.nguoigui.taikhoanId = :taikhoanId) and ((t2.nguoigui = t1.nguoigui and t2.nguoinhan = t1.nguoinhan) or (t2.nguoigui = t1.nguoinhan and t2.nguoinhan = t1.nguoigui)))")
    List<TinNhanEntity> getByTaikhoan(int taikhoanId);

    @Query("select t from TinNhanEntity t where (t.nguoigui.taikhoanId = :nguoiguiId and t.nguoinhan.taikhoanId = :nguoinhanId) or (t.nguoigui.taikhoanId = :nguoinhanId and t.nguoinhan.taikhoanId = :nguoiguiId) order by t.tinnhanId")
    List<TinNhanEntity> getByNguoiguiIdAndNguoinhanId(int nguoiguiId, int nguoinhanId);
    
    @Query("select count(t.tinnhanId) from TinNhanEntity t where t.nguoinhan.taikhoanId = :taikhoanId and t.trangthai = :trangthai")
    int countTinnhanChuaxem(int taikhoanId, int trangthai);

    @Query("select t from TinNhanEntity t where t.nguoigui.taikhoanId = :nguoiguiId and t.nguoinhan.taikhoanId = :nguoinhanId and t.trangthai = :trangthai")
    List<TinNhanEntity> getBySenderAndReceiver(int nguoiguiId, int nguoinhanId, int trangthai);
}
