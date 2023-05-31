package com.tp.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tp.entity.TaikhoanEntity;
import com.tp.entity.TraloiEntity;
import com.tp.model.BinhluanModel;
import com.tp.model.CustomUserDetail;
import com.tp.model.TraloiModel;
import com.tp.entity.BinhluanEntity;
import com.tp.entity.PhongEntity;
import com.tp.service.BinhluanService;
import com.tp.service.TaikhoanService;
import com.tp.service.TraloiService;
import com.tp.service.PhongService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.beans.BeanUtils;
import java.util.Date;



@RestController
public class ThaoluanPhongController {
    @Autowired
    PhongService phongService;
    @Autowired
    TaikhoanService taikhoanService;
    @Autowired
    BinhluanService binhluanService;
    @Autowired
    TraloiService traloiService;
    @PostMapping("account/binhluan")
    public ResponseEntity<?> binhluanPhong(@RequestBody BinhluanModel binhluanModel) {
        BinhluanEntity binhluanEntity = new BinhluanEntity();
        BeanUtils.copyProperties(binhluanModel, binhluanEntity);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        TaikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
        PhongEntity phongEntity = phongService.findById(true, binhluanModel.getPhongId());
        binhluanEntity.setPhong(phongEntity);
        binhluanEntity.setTaikhoan(taikhoanEntity);
        binhluanEntity.setThoigian(new Date());
        binhluanService.saveRequest(binhluanEntity);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("account/traloi")
    public ResponseEntity<?> traloiBinhluan(@RequestBody TraloiModel traloiModel) {
        TraloiEntity traloiEntity = new TraloiEntity();
        BeanUtils.copyProperties(traloiModel, traloiEntity);
        traloiEntity.setThoigian(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        TaikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
        BinhluanEntity binhluanEntity = binhluanService.findById(traloiModel.getBinhluanId());
        if(binhluanEntity == null) {
            TraloiEntity parentTraloi = traloiService.findById(traloiModel.getParent_traloiId());
            traloiEntity.setTraloi(parentTraloi);;
        }
        else {
            traloiEntity.setBinhluan(binhluanEntity);
        }
        traloiEntity.setTaikhoan(taikhoanEntity);
        traloiService.SaveRequest(traloiEntity);
        return ResponseEntity.ok("ok");
    }
}
