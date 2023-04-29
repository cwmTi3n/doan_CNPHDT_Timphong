package com.tp.controller.account;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tp.entity.roleEnum;
import com.tp.entity.taikhoanEntity;
import com.tp.model.customUserDetail;
import com.tp.service.cloudinaryService;
import com.tp.service.taikhoanService;

@Controller
@RequestMapping(value = {"profile", "admin/profile"})
public class profileController {
    @Autowired
    taikhoanService taikhoanService;
    @Autowired
    cloudinaryService cloudinaryService;
    @GetMapping("")
    public String getProfile(ModelMap map) {
        return "account/profile";
    }

    @PostMapping("chinhsua-hoten")
    public String editHoten(@RequestParam("hoten") String hoten, HttpSession session) {
        return editAccount("hoten", hoten, session);
    }

    @PostMapping("chinhsua-email")
    public String editEmail(@RequestParam("email") String email, HttpSession session) {
        return editAccount("email", email, session);
    }

    @PostMapping("chinhsua-sdt")
    public String editSdt(@RequestParam("sdt") String sdt, HttpSession session) {
        return editAccount("sdt", sdt, session);
    }
    @PostMapping("chinhsua-password")
    public String editPassword(@RequestParam("oldpassword") String oldpassword,
    @RequestParam("newpassword") String newpassword,
    @RequestParam("verifypassword") String verifypassword, HttpSession session) {
        String url = "redirect:/profile";
        if(newpassword.equals(verifypassword)) {
            Object object = session.getAttribute("account");
            taikhoanEntity taikhoanEntity = (taikhoanEntity) object;
            taikhoanEntity checkTaikhoan = taikhoanService.findById(taikhoanEntity.getTaikhoanId());
            if(taikhoanEntity.getPassword().equals(oldpassword) && checkTaikhoan.getPassword().equals(oldpassword)) {
                url = editAccount("password", newpassword, session);
            }
        }
        return url;
    }

    @PostMapping("chinhsua-anhdd")
    private String editAnhdd(@RequestParam MultipartFile anhdd) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		customUserDetail userDetails = (customUserDetail) authentication.getPrincipal();
        taikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
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
		customUserDetail userDetails = (customUserDetail) authentication.getPrincipal();
        taikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
        taikhoanEntity.setRole(role);
        taikhoanService.SavedRequest(taikhoanEntity);
        return "redirect:/profile";
    }

    private String editAccount(String type, String value, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		customUserDetail userDetails = (customUserDetail) authentication.getPrincipal();
        taikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
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
