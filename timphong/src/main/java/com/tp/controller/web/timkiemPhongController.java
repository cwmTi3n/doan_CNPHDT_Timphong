package com.tp.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tp.entity.LoaiphongEntity;
import com.tp.entity.PhongEntity;
import com.tp.service.LoaiphongService;
import com.tp.service.PhongService;
import com.tp.util.Constant;

@Controller
@RequestMapping("timkiem")
public class TimkiemPhongController {
	@Autowired
	PhongService phongService;
	@Autowired
    LoaiphongService loaiphongService;
	@GetMapping("")
	public String getTimkiemPhong(@RequestParam(name = "keyword", defaultValue = "") String keyword,
		ModelMap map) {
		List<LoaiphongEntity> loaiphongs = loaiphongService.findAll();
		Page<PhongEntity> pPhong;
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
