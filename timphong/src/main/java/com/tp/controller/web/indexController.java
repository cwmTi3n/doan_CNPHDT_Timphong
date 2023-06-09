package com.tp.controller.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tp.entity.PhongEntity;
import com.tp.service.PhongService;
import com.tp.util.Constant;

@Controller
@RequestMapping("")
public class IndexController {
	@Autowired
    PhongService phongService;
	@GetMapping(value = {"", "/trangchu"})
	public String getIndex(ModelMap map, HttpSession session) {
		Page<PhongEntity> pPhong = phongService.findAll(true, 0, Constant.PAGESIZE_PHONG, "ten");
		map.addAttribute("pPhong", pPhong);
		return "web/index";
	}
}
