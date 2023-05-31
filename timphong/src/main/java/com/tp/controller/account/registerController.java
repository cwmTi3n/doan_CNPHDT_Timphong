package com.tp.controller.account;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tp.entity.roleEnum;
import com.tp.entity.TaikhoanEntity;
import com.tp.model.TaikhoanModel;
import com.tp.service.TaikhoanService;

@Controller
@RequestMapping("register")
public class RegisterController {
    @Autowired
    TaikhoanService taikhoanService;
    @PostMapping("")
    public String registerUser(@Valid @ModelAttribute("taikhoan") TaikhoanModel taikhoanModel, ModelMap map) {
        TaikhoanEntity taikhoanEntity = taikhoanService.findByUsername(taikhoanModel.getUsername());
        if(taikhoanEntity == null) {
            TaikhoanEntity taikhoan = new TaikhoanEntity();
            BeanUtils.copyProperties(taikhoanModel, taikhoan);
            taikhoan.setRole(roleEnum.USER);;
            taikhoan.setTrangthai(true);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            taikhoan.setPassword(passwordEncoder.encode(taikhoan.getPassword()));
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
