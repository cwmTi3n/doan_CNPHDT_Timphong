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

import com.tp.entity.lichhenEntity;
import com.tp.entity.phongEntity;
import com.tp.entity.quantamEntity;
import com.tp.entity.taikhoanEntity;
import com.tp.model.customUserDetail;
import com.tp.service.lichhenService;
import com.tp.service.phongService;
import com.tp.service.quantamService;

@Controller
@RequestMapping("detail-phong")
public class detailPhongController {
    @Autowired
    phongService phongService;
    @Autowired
    lichhenService lichhenService;
    @Autowired
    quantamService quantamService;
    @GetMapping("{id}")
    public String detailPhong(ModelMap map, @PathVariable("id") Integer id, @RequestParam(defaultValue = "0") int lichhenId) {
        phongEntity phongEntity = phongService.findById(true, id);
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            customUserDetail userDetails = (customUserDetail) authentication.getPrincipal();
            taikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
            if(taikhoanEntity != null) {
                lichhenEntity lichhenEntity = lichhenService.findById(lichhenId);
                quantamEntity quantamEntity = quantamService.findByTaikhoanAndPhong(taikhoanEntity, phongEntity);
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
}
