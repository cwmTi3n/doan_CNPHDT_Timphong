package com.tp.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tp.entity.taikhoanEntity;
import com.tp.entity.traloiEntity;
import com.tp.model.binhluanModel;
import com.tp.model.customUserDetail;
import com.tp.model.traloiModel;
import com.tp.entity.binhluanEntity;
import com.tp.entity.phongEntity;
import com.tp.service.binhluanService;
import com.tp.service.taikhoanService;
import com.tp.service.traloiService;
import com.tp.service.phongService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.beans.BeanUtils;
import java.util.Date;



@RestController
public class thaoluanPhongController {
    @Autowired
    phongService phongService;
    @Autowired
    taikhoanService taikhoanService;
    @Autowired
    binhluanService binhluanService;
    @Autowired
    traloiService traloiService;
    @PostMapping("account/binhluan")
    public ResponseEntity<?> binhluanPhong(@RequestBody binhluanModel binhluanModel) {
        binhluanEntity binhluanEntity = new binhluanEntity();
        BeanUtils.copyProperties(binhluanModel, binhluanEntity);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		customUserDetail userDetails = (customUserDetail) authentication.getPrincipal();
        taikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
        phongEntity phongEntity = phongService.findById(true, binhluanModel.getPhongId());
        binhluanEntity.setPhong(phongEntity);
        binhluanEntity.setTaikhoan(taikhoanEntity);
        binhluanEntity.setThoigian(new Date());
        binhluanService.saveRequest(binhluanEntity);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("account/traloi")
    public ResponseEntity<?> traloiBinhluan(@RequestBody traloiModel traloiModel) {
        traloiEntity traloiEntity = new traloiEntity();
        BeanUtils.copyProperties(traloiModel, traloiEntity);
        traloiEntity.setThoigian(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		customUserDetail userDetails = (customUserDetail) authentication.getPrincipal();
        taikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
        binhluanEntity binhluanEntity = binhluanService.findById(traloiModel.getBinhluanId());
        if(binhluanEntity == null) {
            traloiEntity parentTraloi = traloiService.findById(traloiModel.getParent_traloiId());
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
