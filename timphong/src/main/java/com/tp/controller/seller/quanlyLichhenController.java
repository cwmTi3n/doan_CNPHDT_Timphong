package com.tp.controller.seller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tp.entity.lichhenEntity;
import com.tp.entity.taikhoanEntity;
import com.tp.entity.ttlichhenEnum;
import com.tp.model.customUserDetail;
import com.tp.service.lichhenService;

@Controller
public class quanlyLichhenController {
    @Autowired
    lichhenService lichhenService;
    @GetMapping("seller/quanly-lichhen")
    public String quanlyLichhen(ModelMap map, @RequestParam(defaultValue = "0") int trangthai) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		customUserDetail userDetails = (customUserDetail) authentication.getPrincipal();
        taikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
        List<lichhenEntity> lichhens = lichhenService.findBySellerAndTrangthai(taikhoanEntity.getTaikhoanId(), ttlichhenEnum.values()[trangthai]);
        map.addAttribute("lichhens", lichhens);
        map.addAttribute("trangthai", trangthai);
        return "seller/quanly-lichhen.html";
    }

    @GetMapping("seller/xacnhan-lichhen")
    public String xacnhanLichhen(@RequestParam(defaultValue = "0") int lichhenId) {
        lichhenEntity lichhenEntity = lichhenService.findById(lichhenId);
        if(lichhenEntity != null) {
            lichhenEntity.setTrangthai(ttlichhenEnum.XACNHAN);
            lichhenService.SavedRequest(lichhenEntity);
        }
        return "redirect:/seller/quanly-lichhen?trangthai=1";
    }

    @GetMapping("seller/huy-lichhen")
    public String huyLichhen(@RequestParam(defaultValue = "0") int lichhenId) {
        lichhenEntity lichhenEntity = lichhenService.findById(lichhenId);
        if(lichhenEntity != null) {
            lichhenEntity.setTrangthai(ttlichhenEnum.BIHUY);
            lichhenService.SavedRequest(lichhenEntity);
        }
        return "redirect:/seller/quanly-lichhen?trangthai=2";
    }
}
