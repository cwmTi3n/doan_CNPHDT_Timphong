package com.tp.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tp.entity.loaiphongEntity;
import com.tp.entity.phongEntity;
import com.tp.service.loaiphongService;
import com.tp.service.phongService;
import com.tp.util.Constant;

@Controller
@RequestMapping("admin")
public class crudPhongAdminController {
	@Autowired
	loaiphongService loaiphongService;
	@Autowired
	phongService phongService;
	@GetMapping("phong")
	public String getQuanlyphong(@RequestParam(name = "keyword", defaultValue = "") String keyword,
		@RequestParam(name = "orderby", defaultValue = "ten") String orderby,
		@RequestParam(name = "trangthai", defaultValue = "true") boolean trangthai, ModelMap map) {
		List<loaiphongEntity> lps = loaiphongService.findAll();
		Page<phongEntity> pPhong;
		if(keyword.equals("")) {
			pPhong = phongService.findAll(trangthai, 0, Constant.PAGESIZE_PHONG, orderby);
		}
		else {
			pPhong = phongService.searchPhong(trangthai, keyword, orderby, 0, Constant.PAGESIZE_PHONG);
		}
		map.addAttribute("loaiphongs", lps);
		map.addAttribute("pPhong", pPhong);
		map.addAttribute("keyword", keyword);
		map.addAttribute("trangthai", trangthai);
		return "admin/list-phong";
	}

	@GetMapping("report-phong/{id}")
	public String reportPhong(@PathVariable int id) {
		phongEntity phongEntity = phongService.findById(true, id);
		phongEntity.setTrangthai(false);
		phongService.SavedRequest(phongEntity);
		return "redirect:/admin/phong";
	}

	@GetMapping("check-phong/{id}")
	public String checkPhong(@PathVariable int id) {
		phongEntity phongEntity = phongService.findById(false, id);
		phongEntity.setTrangthai(true);
		phongService.SavedRequest(phongEntity);
		return "redirect:/admin/phong";
	}
}
