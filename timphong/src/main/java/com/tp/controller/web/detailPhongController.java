package com.tp.controller.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tp.entity.LichhenEntity;
import com.tp.entity.PhongEntity;
import com.tp.entity.QuantamEntity;
import com.tp.entity.TaikhoanEntity;
import com.tp.model.CustomUserDetail;
import com.tp.service.LichhenService;
import com.tp.service.PhongService;
import com.tp.service.QuantamService;

@Controller
@RequestMapping("detail-phong")
public class DetailPhongController {
    @Autowired
    PhongService phongService;
    @Autowired
    LichhenService lichhenService;
    @Autowired
    QuantamService quantamService;
    @GetMapping("{id}")
    public String detailPhong(ModelMap map, @PathVariable("id") Integer id, @RequestParam(defaultValue = "0") int lichhenId) {
        PhongEntity phongEntity = phongService.findById(true, id);
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
            TaikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
            if(taikhoanEntity != null) {
                LichhenEntity lichhenEntity = lichhenService.findById(lichhenId);
                QuantamEntity quantamEntity = quantamService.findByTaikhoanAndPhong(taikhoanEntity, phongEntity);
                if(lichhenEntity != null) {
                    map.addAttribute("lichhen", lichhenEntity);
                }
                if(quantamEntity != null) {
                    map.addAttribute("quantam", quantamEntity);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        map.addAttribute("phong", phongEntity);
        return "web/phong";
    }

    @GetMapping("vi-pham/{id}")
    public String phongVipham(ModelMap map, @PathVariable("id") Integer id) {
        PhongEntity phongEntity = phongService.findById(false, id);
        map.addAttribute("phong", phongEntity);
        return "web/phong-vipham";
    }

}
