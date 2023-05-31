package com.tp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.tp.entity.roleEnum;
import com.tp.service.PhongService;
import com.tp.service.TaikhoanService;

@Controller
public class ThongkeWeb {
    @Autowired
    PhongService phongService;
    @Autowired
    TaikhoanService taikhoanService;
    @GetMapping("admin/thongke")
    public String thongke(ModelMap map) {
        int user = taikhoanService.findByRole(roleEnum.USER);
        int seller = taikhoanService.findByRole(roleEnum.SELLER);
        int hien = phongService.phongHien();
        int an = phongService.phongAn();
        map.addAttribute("user", user);
        map.addAttribute("seller", seller);
        map.addAttribute("hien", hien);
        map.addAttribute("an", an);
        return "admin/thongke";
    }
}
