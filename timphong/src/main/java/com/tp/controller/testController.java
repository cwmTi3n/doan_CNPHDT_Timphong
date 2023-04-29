package com.tp.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tp.service.emailService;

@Controller
public class testController {
    @Autowired
    emailService emailService;
    @GetMapping("test")
    public String test(HttpServletRequest request) {
        System.out.println("========================" + request.getRequestURL().toString());
        return "redirect:/";
    }
}
