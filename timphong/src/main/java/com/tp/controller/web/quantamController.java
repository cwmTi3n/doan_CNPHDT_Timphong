package com.tp.controller.web;

import java.util.List;

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

import com.tp.entity.phongEntity;
import com.tp.entity.quantamEntity;
import com.tp.entity.taikhoanEntity;
import com.tp.model.customUserDetail;
import com.tp.service.phongService;
import com.tp.service.quantamService;

@Controller
public class quantamController {
    @Autowired
    quantamService quantamService;
    @Autowired
    phongService phongService;
    @PostMapping("/quantam")
    @ResponseBody
    public ResponseEntity<String> quantam(@RequestParam int phongId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            customUserDetail userDetails = (customUserDetail) authentication.getPrincipal();
            taikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
            phongEntity phongEntity = phongService.findById(true, phongId);
            quantamEntity quantamEntity = quantamService.findByTaikhoanAndPhong(taikhoanEntity, phongEntity);
            if(quantamEntity == null) {
                quantamEntity = new quantamEntity();
                quantamEntity.setPhong(phongEntity);
                quantamEntity.setTaikhoan(taikhoanEntity);
                quantamService.SavedRequest(quantamEntity);
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
        customUserDetail userDetails = (customUserDetail) authentication.getPrincipal();
        taikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
        List<quantamEntity> quantams = quantamService.findByTaikhoan(taikhoanEntity);
        map.addAttribute("quantams", quantams);
        return "web/quantam.html";
    }

    @GetMapping("account/list-quantam/delete/{id}")
    public String deleteQuantam(@PathVariable int id) {
        quantamService.deleteById(id);
        return "redirect:/account/list-quantam";
    }
}
