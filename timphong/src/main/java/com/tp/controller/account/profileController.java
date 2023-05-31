package com.tp.controller.account;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tp.entity.roleEnum;
import com.tp.entity.TaikhoanEntity;
import com.tp.model.CustomUserDetail;
import com.tp.service.CloudinaryService;
import com.tp.service.TaikhoanService;

@Controller
@RequestMapping(value = {"profile", "admin/profile"})
public class ProfileController {
    @Autowired
    TaikhoanService taikhoanService;
    @Autowired
    CloudinaryService cloudinaryService;
    @GetMapping("")
    public String getProfile(ModelMap map) {
        return "account/profile";
    }

    @PostMapping("chinhsua-hoten")
    public String editHoten(@RequestParam("hoten") String hoten) {
        return editAccount("hoten", hoten);
    }

    @PostMapping("chinhsua-email")
    public String editEmail(@RequestParam("email") String email) {
        return editAccount("email", email);
    }

    @PostMapping("chinhsua-sdt")
    public String editSdt(@RequestParam("sdt") String sdt) {
        return editAccount("sdt", sdt);
    }
    @PostMapping("chinhsua-password")
    public String editPassword(@RequestParam("oldpassword") String oldpassword,
    @RequestParam("newpassword") String newpassword,
    @RequestParam("verifypassword") String verifypassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String url = "redirect:/profile";
        if(newpassword.equals(verifypassword)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
            TaikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
            TaikhoanEntity checkTaikhoan = taikhoanService.findById(taikhoanEntity.getTaikhoanId());
            if(passwordEncoder.matches(oldpassword, taikhoanEntity.getPassword()) && passwordEncoder.matches(oldpassword, checkTaikhoan.getPassword())) {
                System.out.println("==========================change==");
                url = editAccount("password", passwordEncoder.encode(newpassword));
            }
            else {
                System.out.println("==========================no==");
            }
        }
        return url;
    }

    @PostMapping("chinhsua-anhdd")
    private String editAnhdd(@RequestParam MultipartFile anhdd) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        TaikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
        String filename = cloudinaryService.uploadFile(anhdd);
        if(filename != null) {
            if(taikhoanEntity.getAnhdd() != null) {
                cloudinaryService.deleteImage(taikhoanEntity.getAnhdd());
            }
            taikhoanEntity.setAnhdd(filename);
            taikhoanService.SavedRequest(taikhoanEntity);
        }
        return "redirect:/profile";
    }

    @PostMapping("dangky-seller")
    public String dangkySeller(@RequestParam roleEnum role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        TaikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
        taikhoanEntity.setRole(role);
        taikhoanService.SavedRequest(taikhoanEntity);
        return "redirect:/profile";
    }

    private String editAccount(String type, String value) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        TaikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
        if(type.equals("hoten")) {
            taikhoanEntity.setHoten(value);
        }
        else if(type.equals("email")) {
            taikhoanEntity.setEmail(value);
        }
        else if (type.equals("sdt")) {
            taikhoanEntity.setSdt(value);
        }
        else if (type.equals("password")) {
            taikhoanEntity.setPassword(value);
        }
        taikhoanService.SavedRequest(taikhoanEntity);
        if(taikhoanEntity.getRole() == roleEnum.ADMIN) {
            return "redirect:/admin/profile";
        }
        return "redirect:/profile";
    }
}
