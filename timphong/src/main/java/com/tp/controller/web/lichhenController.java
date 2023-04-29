package com.tp.controller.web;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.tp.entity.lichhenEntity;
import com.tp.entity.phongEntity;
import com.tp.entity.taikhoanEntity;
import com.tp.entity.ttlichhenEnum;
import com.tp.model.customUserDetail;
import com.tp.service.lichhenService;
import com.tp.service.phongService;

@Controller
public class lichhenController {
    @Autowired
    lichhenService lichhenService;
    @Autowired 
    phongService phongService;
    
    @PostMapping("/dathen")
    @ResponseBody
    public ResponseEntity<String> dathen(HttpServletRequest request, @RequestParam int phongId, @RequestParam Date ngay, @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime gio) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            customUserDetail userDetails = (customUserDetail) authentication.getPrincipal();
            taikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
            lichhenEntity lichhenEntity = new lichhenEntity();
            lichhenEntity.setTaikhoan(taikhoanEntity);
            phongEntity phongEntity = phongService.findById(true, phongId);
            lichhenEntity.setPhong(phongEntity);
            lichhenEntity.setTrangthai(ttlichhenEnum.DATHEN);
            lichhenEntity.setNgay(ngay);
            lichhenEntity.setGio(gio);
            lichhenService.SavedRequest(lichhenEntity);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Có lỗi xảy ra khi đặt lịch");
        }
    }

    @GetMapping("account/list-lichhen")
    public String getLichhen(ModelMap map, @RequestParam(defaultValue = "0") int trangthai) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        customUserDetail userDetails = (customUserDetail) authentication.getPrincipal();
        taikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
        List<lichhenEntity> lichhens = lichhenService.findByTrangthai(ttlichhenEnum.values()[trangthai], taikhoanEntity);
        map.addAttribute("lichhens", lichhens);
        map.addAttribute("trangthai", trangthai);
        return "web/lichhen.html";
    }

    @GetMapping("account/xoa-lichhen/{id}")
    public String deleteLichhen(@PathVariable Integer id){
        lichhenService.deleteById(id);
        return "redirect:/account/list-lichhen";
    }

    @PostMapping("/chinhsua-lichhen")
    @ResponseBody
    public ResponseEntity<String> editDathen(@RequestParam Date ngay, @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime gio, @RequestParam int lichhenId) {
        try {
            lichhenEntity lichhenEntity = lichhenService.findById(lichhenId);
            lichhenEntity.setNgay(ngay);
            lichhenEntity.setGio(gio);
            lichhenService.SavedRequest(lichhenEntity);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Có lỗi xảy ra khi chỉnh sửa");
        }
    }
}
