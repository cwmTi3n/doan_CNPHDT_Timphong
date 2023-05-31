package com.tp.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tp.service.EmailService;

@Controller
public class testController {
    @Autowired
    EmailService emailService;
    @GetMapping("/account/test")
    public String test(HttpServletRequest request) {

        return "chat/list-chat";
    }
}
