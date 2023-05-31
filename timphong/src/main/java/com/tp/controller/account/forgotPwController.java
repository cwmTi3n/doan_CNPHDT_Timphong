package com.tp.controller.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tp.entity.TaikhoanEntity;
import com.tp.service.EmailService;
import com.tp.service.TaikhoanService;
import com.tp.util.Constant;

@Controller
public class ForgotPwController {
    @Autowired
    TaikhoanService taikhoanService;
    @Autowired
    EmailService emailService;
    @GetMapping("forgot-pw")
    public String getForgotPw() {
        return "account/forgot-pw.html";
    }

    @PostMapping("forgot-pw")
    @ResponseBody
    public ResponseEntity<String> postForgotPw(@RequestParam String username, HttpServletRequest request) {
        TaikhoanEntity taikhoanEntity = taikhoanService.findByUsername(username);
        if (taikhoanEntity == null) {
            return ResponseEntity.badRequest().body("error");
        }
        String code = Constant.getCode();
        emailService.sendMail(taikhoanEntity.getEmail(), "Mã code thay đổi mật khẩu", code);
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        session.setAttribute("username", username);
        session.setMaxInactiveInterval(60);
        return ResponseEntity.ok("Ok");
    }

    @PostMapping("change-pw")
    @ResponseBody
    public ResponseEntity<String> changePw(@RequestParam String code, @RequestParam String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        TaikhoanEntity taikhoanEntity = taikhoanService.findByUsername(username);
        String cfCode = (String)session.getAttribute("code");
        if(cfCode.equals(code)) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encoder = passwordEncoder.encode(password);
            System.out.println(encoder);
            taikhoanEntity.setPassword(encoder);
            taikhoanService.SavedRequest(taikhoanEntity);
            return ResponseEntity.ok("Ok");
        }
        return ResponseEntity.badRequest().body("error");
    }
}
