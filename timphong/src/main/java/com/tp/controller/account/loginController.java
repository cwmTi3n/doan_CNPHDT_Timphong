package com.tp.controller.account;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class loginController {
    @GetMapping("")
    public String getFormLogin(HttpServletRequest request, ModelMap map) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("username")) {
                    String value = cookie.getValue();
                    map.addAttribute("us", value);
                }
                if(cookie.getName().equals("password")) {
                    String value = cookie.getValue();
                    map.addAttribute("pw", value);
                    map.addAttribute("rmb", "checked");
                }
            }
        }
        return "web/login";
    }
}
