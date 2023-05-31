package com.tp.controller.chat;

import com.tp.entity.TinNhanEntity;
import com.tp.service.TinNhanService;
import com.tp.service.WSService;
import com.tp.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tp.entity.TaikhoanEntity;
import com.tp.service.TaikhoanService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("account")
public class ChatController {
    @Autowired
    TaikhoanService taikhoanService;
    @Autowired
    TinNhanService tinNhanService;
    @Autowired
    WSService wsService;
    @GetMapping("list-chat")
    public String listChat(ModelMap map) {
        TaikhoanEntity taikhoanEntity = Constant.getUserLogin();
        List<TinNhanEntity> tinnhans = tinNhanService.getListChatByTaikhoan(taikhoanEntity.getTaikhoanId());
        for(TinNhanEntity tn : tinnhans) {
            if(tn.getNguoinhan().getTaikhoanId() == taikhoanEntity.getTaikhoanId() && tn.getTrangthai() == 0) {
                tn.setTrangthai(2);
            }
            if(tn.getNguoigui().getTaikhoanId() != taikhoanEntity.getTaikhoanId()) {
                tn.setNguoinhan(tn.getNguoigui());
            }
        }
        map.addAttribute("tinnhans", tinnhans);
        map.addAttribute("taikhoanId", taikhoanEntity.getTaikhoanId());
        return "chat/list-chat";
    }

    @GetMapping("room-chat/{id}")
    public ModelAndView roomChat(@RequestParam(defaultValue = "0") int type, @PathVariable("id") Integer id, ModelMap map, HttpServletRequest request) {
        TaikhoanEntity nguoinhan = taikhoanService.findById(id);
        TaikhoanEntity nguoigui = Constant.getUserLogin();
        if(type != 0) {
            String noidung = "Tôi muốn biết thêm thông tin về phòng: ";
            String url = Constant.protocol + request.getServerName() + "/detail-phong/" + String.valueOf(type);
            wsService.sendMessage(nguoigui, nguoinhan, noidung + url);
            return new ModelAndView("redirect:/account/room-chat/" + String.valueOf(id));
        }
        setDaxemForTinnhan(nguoigui.getTaikhoanId(), nguoinhan.getTaikhoanId());
        List<TinNhanEntity> tinnhans = tinNhanService.getTinnhanForRoom(nguoigui.getTaikhoanId(), nguoinhan.getTaikhoanId());
        map.addAttribute("nguoinhan", nguoinhan);
        map.addAttribute("tinnhans", tinnhans);
        return new ModelAndView("chat/chat");
    }

    private void setDaxemForTinnhan(int accountLogin, int nguoiguiId) {
        List<TinNhanEntity> tinnhans = tinNhanService.getTinnhanBySenderAndReceiver(nguoiguiId, accountLogin);
        for(TinNhanEntity tn : tinnhans) {
            tn.setTrangthai(1);
            tinNhanService.SavedRequest(tn);
        }
    }
}
