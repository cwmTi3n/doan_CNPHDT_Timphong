package com.tp.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tp.entity.LoaiphongEntity;
import com.tp.model.LoaiphongModel;
import com.tp.service.LoaiphongService;

@Controller
@RequestMapping("admin")
public class CrudLoaiphongAdminController {
	@Autowired
    LoaiphongService loaiphongService;
	@GetMapping("loaiphong")
	public String getLoaiphong(ModelMap map) {
		List<LoaiphongEntity> lps = loaiphongService.findAll();
		map.addAttribute("loaiphongs", lps);
		return "admin/list-loaiphong";
	}
	
	@GetMapping("them-loaiphong")
	public String getFormThemLoaiphong() {
		return "admin/save-loaiphong";
	}
	
	@PostMapping("save-loaiphong")
	public String themLoaiphong(@Valid @ModelAttribute("loaiphong") LoaiphongModel loaiphong) {
		LoaiphongEntity loaiphongEntity = new LoaiphongEntity();
		BeanUtils.copyProperties(loaiphong, loaiphongEntity);
		loaiphongService.SavedRequest(loaiphongEntity);
		return "redirect:/admin/loaiphong";
	}
	
	@GetMapping("xoa-loaiphong")
	public String xoaLoaiphong(@RequestParam("id") Integer id) {
		loaiphongService.deleteById(id);
		return "redirect:/admin/loaiphong";
	}
	
	@GetMapping("chinhsua-loaiphong")
	public String chinhsuaLoaiphong(@RequestParam("id") Integer id, ModelMap map) {
		LoaiphongEntity lpEntity = loaiphongService.findById(id);
		map.addAttribute("lp", lpEntity);
		return "admin/save-loaiphong";
	}
	
	@GetMapping("test")
	public String test() {
		return "admin/quanlyphong";
	}
	
}
