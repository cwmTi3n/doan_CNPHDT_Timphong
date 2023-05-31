package com.tp.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tp.entity.PhongEntity;
import com.tp.entity.QuantamEntity;
import com.tp.entity.TaikhoanEntity;
import com.tp.model.CustomUserDetail;
import com.tp.service.PhongService;
import com.tp.service.QuantamService;
import com.tp.service.WSService;
import com.tp.util.Constant;

@Controller
public class QuantamController {
    @Autowired
    QuantamService quantamService;
    @Autowired
    PhongService phongService;
    @Autowired
    WSService wsService;
    @PostMapping("/quantam")
    @ResponseBody
    public ResponseEntity<String> quantam(@RequestParam int phongId, HttpServletRequest request) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
            TaikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
            PhongEntity phongEntity = phongService.findById(true, phongId);
            QuantamEntity quantamEntity = quantamService.findByTaikhoanAndPhong(taikhoanEntity, phongEntity);
            if(quantamEntity == null) {
                quantamEntity = new QuantamEntity();
                quantamEntity.setPhong(phongEntity);
                quantamEntity.setTaikhoan(taikhoanEntity);
                quantamService.SavedRequest(quantamEntity);
                String noidung = "Tôi quan tâm đến phòng này của bạn: ";
                String url = Constant.protocol + request.getServerName() + "/detail-phong/" + phongEntity.getPhongId();
                wsService.sendMessage(taikhoanEntity, phongEntity.getTaikhoan(), noidung + url);
                return ResponseEntity.ok("<i class=\"bi bi-heart-fill mr-1\"></i>Đang quan tâm");
            }
            else {
                quantamService.deleteById(quantamEntity.getQuantamId());
                return ResponseEntity.ok("<i class=\"bi bi-heart mr-1\"></i>Quan tâm");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Có lỗi xảy ra");
        }
        
    }

    @GetMapping("account/list-quantam")
    public String getQuantam(ModelMap map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        TaikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
        List<QuantamEntity> quantams = quantamService.findByTaikhoan(taikhoanEntity);
        map.addAttribute("quantams", quantams);
        return "web/quantam.html";
    }

    @GetMapping("account/list-quantam/delete/{id}")
    public String deleteQuantam(@PathVariable int id) {
        quantamService.deleteById(id);
        return "redirect:/account/list-quantam";
    }
}
