package com.tp.controller.socket;

import com.tp.entity.TaikhoanEntity;
import com.tp.entity.TinNhanEntity;
import com.tp.model.TinNhanModel;
import com.tp.service.TaikhoanService;
import com.tp.service.TinNhanService;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private TinNhanService tinNhanService;
    @Autowired
    private TaikhoanService taikhoanService;
    @MessageMapping("/private-message")
    public void handleChatMessage(TinNhanModel message) {
        TaikhoanEntity nguoigui = taikhoanService.findByUsername(message.getNguoigui());
        TaikhoanEntity nguoinhan = taikhoanService.findByUsername(message.getNguoinhan());
        TinNhanEntity tinNhanEntity = new TinNhanEntity();
        tinNhanEntity.setNguoigui(nguoigui);
        tinNhanEntity.setNguoinhan(nguoinhan);
        tinNhanEntity.setNoidung(message.getNoidung());
        Date date = new Date();
        tinNhanEntity.setThoigian(date);
        tinNhanEntity.setTrangthai(0);
        tinNhanService.SavedRequest(tinNhanEntity);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        message.setThoigian(sdf.format(date));
        message.setId(tinNhanEntity.getTinnhanId());
        simpMessagingTemplate.convertAndSendToUser(message.getNguoinhan(), "private", message);
    }
}
