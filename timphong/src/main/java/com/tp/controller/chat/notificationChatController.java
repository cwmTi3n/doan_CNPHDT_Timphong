package com.tp.controller.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tp.entity.TaikhoanEntity;
import com.tp.entity.TinNhanEntity;
import com.tp.service.TinNhanService;
import com.tp.util.Constant;

@RestController
public class notificationChatController {
    @Autowired
    TinNhanService tinNhanService;
    @PostMapping("/notification-chat")
    public String notificationChat(@RequestParam(defaultValue = "0") int id) {
        TinNhanEntity tinNhanEntity = tinNhanService.getTinnhanByTinnhanId(id);
        if(tinNhanEntity != null) {
            tinNhanEntity.setTrangthai(1);
            tinNhanService.SavedRequest(tinNhanEntity);
            return "Da xem";
        }
        return "Co loi";
    }

    @GetMapping("/count-tn-chuaxem")
    public String countTinnhanChuaxem() {
        try {
            TaikhoanEntity taikhoanEntity = Constant.getUserLogin();
            if(taikhoanEntity != null) {
                return String.valueOf(tinNhanService.countTinnhanChuaxem(taikhoanEntity.getTaikhoanId()));
    
            }
            return "0";
        } catch (Exception e) {
            return "0";
        }

    }
}
