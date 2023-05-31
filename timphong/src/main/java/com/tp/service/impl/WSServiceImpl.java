package com.tp.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.tp.entity.TaikhoanEntity;
import com.tp.entity.TinNhanEntity;
import com.tp.model.TinNhanModel;
import com.tp.service.TinNhanService;
import com.tp.service.WSService;

@Service
public class WSServiceImpl implements WSService{
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    TinNhanService tinNhanService;
    @Override
    public void sendMessage(TaikhoanEntity nguoigui, TaikhoanEntity nguoinhan, String noidung) {
        TinNhanEntity tinNhanEntity = new TinNhanEntity();
        Date date = new Date();
        tinNhanEntity.setNguoigui(nguoigui);
        tinNhanEntity.setNguoinhan(nguoinhan);
        tinNhanEntity.setThoigian(date);
        tinNhanEntity.setTrangthai(0);
        tinNhanEntity.setNoidung(noidung);
        if(tinNhanService.SavedRequest(tinNhanEntity) != null) {
            TinNhanModel tinNhanModel = new TinNhanModel();
            tinNhanModel.setId(tinNhanEntity.getTinnhanId());
            tinNhanModel.setNguoigui(tinNhanEntity.getNguoigui().getUsername());
            tinNhanModel.setNguoinhan(tinNhanEntity.getNguoinhan().getUsername());
            tinNhanModel.setNoidung(tinNhanEntity.getNoidung());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tinNhanModel.setThoigian(sdf.format(date));
            simpMessagingTemplate.convertAndSendToUser(tinNhanModel.getNguoinhan(), "private", tinNhanModel);
        }
    }
    
}
