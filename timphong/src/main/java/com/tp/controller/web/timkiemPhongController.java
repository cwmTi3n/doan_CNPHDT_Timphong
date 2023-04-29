package com.tp.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tp.entity.loaiphongEntity;
import com.tp.entity.phongEntity;
import com.tp.service.loaiphongService;
import com.tp.service.phongService;
import com.tp.util.Constant;

@Controller
@RequestMapping("timkiem")
public class timkiemPhongController {
	@Autowired
	phongService phongService;
	@Autowired
	loaiphongService loaiphongService;
	@GetMapping("")
	public String getTimkiemPhong(@RequestParam(name = "keyword", defaultValue = "") String keyword,
		ModelMap map) {
		List<loaiphongEntity> loaiphongs = loaiphongService.findAll();
		Page<phongEntity> pPhong;
		if(keyword.equals("")) {
			pPhong = phongService.findAll(true, 0, Constant.PAGESIZE_PHONG, "ten");
		}
		else {
			pPhong = phongService.searchPhong(true, keyword, "ten", 0, Constant.PAGESIZE_PHONG);
		}
		map.addAttribute("pPhong", pPhong);
		map.addAttribute("loaiphongs", loaiphongs);
		map.addAttribute("keyword", keyword);
		return "web/timkiem";
	}
}
