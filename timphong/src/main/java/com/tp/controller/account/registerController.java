package com.tp.controller.account;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tp.entity.roleEnum;
import com.tp.entity.taikhoanEntity;
import com.tp.model.taikhoanModel;
import com.tp.service.taikhoanService;

@Controller
@RequestMapping("register")
public class registerController {
    @Autowired
    taikhoanService taikhoanService;
    @PostMapping("")
    public String registerUser(@Valid @ModelAttribute("taikhoan") taikhoanModel taikhoanModel, ModelMap map) {
        taikhoanEntity taikhoanEntity = taikhoanService.findByUsername(taikhoanModel.getUsername());
        if(taikhoanEntity == null) {
            taikhoanEntity taikhoan = new taikhoanEntity();
            BeanUtils.copyProperties(taikhoanModel, taikhoan);
            taikhoan.setRole(roleEnum.USER);;
            taikhoan.setTrangthai(true);
            if(taikhoanService.SavedRequest(taikhoan) == null) {
                map.addAttribute("error", "Tạo tài khoản không thành công");
            }
            else {
                map.addAttribute("error", "Tạo tài khoản thành công");
            }
        }
        else {
            map.addAttribute("error", "Tài khoản đã tồn tại");
        }
        return "web/login";
    }
}
