package com.tp.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tp.entity.LoaiphongEntity;
import com.tp.entity.PhongEntity;
import com.tp.service.LoaiphongService;
import com.tp.service.PhongService;
import com.tp.service.WSService;
import com.tp.util.Constant;

@Controller
@RequestMapping("admin")
public class CrudPhongAdminController {
	@Autowired
    LoaiphongService loaiphongService;
	@Autowired
	PhongService phongService;
	@Autowired
	WSService wsService;
	@GetMapping("phong")
	public String getQuanlyphong(@RequestParam(name = "keyword", defaultValue = "") String keyword,
		@RequestParam(name = "orderby", defaultValue = "ten") String orderby,
		@RequestParam(name = "trangthai", defaultValue = "true") boolean trangthai, ModelMap map) {
		List<LoaiphongEntity> lps = loaiphongService.findAll();
		Page<PhongEntity> pPhong;
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
	public String reportPhong(@PathVariable int id, HttpServletRequest request) {
		PhongEntity phongEntity = phongService.findById(true, id);
		phongEntity.setTrangthai(false);
		String noidung = "Phòng của bạn đã vi phạm nguyên tắc cộng đồng: ";
		String url = Constant.protocol + request.getServerName() + "/detail-phong/vi-pham/" + String.valueOf(id);
		wsService.sendMessage(Constant.getUserLogin(), phongEntity.getTaikhoan(), noidung + url);
		phongService.SavedRequest(phongEntity);
		return "redirect:/admin/phong";
	}

	@GetMapping("check-phong/{id}")
	public String checkPhong(@PathVariable int id) {
		PhongEntity phongEntity = phongService.findById(false, id);
		phongEntity.setTrangthai(true);
		phongService.SavedRequest(phongEntity);
		return "redirect:/admin/phong";
	}
}
