package com.tp.controller.admin;


import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tp.entity.roleEnum;
import com.tp.entity.TaikhoanEntity;
import com.tp.model.TaikhoanModel;
import com.tp.service.CloudinaryService;
import com.tp.service.ImageService;
import com.tp.service.TaikhoanService;
import com.tp.util.Constant;

@Controller
@RequestMapping("admin")
public class CrudTaikhoanAdminController {
	@Autowired
	ImageService imageService;
	@Autowired
	TaikhoanService taikhoanService;
	@Autowired
    CloudinaryService cloudinaryService;
	@GetMapping("taikhoan")
	public String getListTaikhoan(@RequestParam(name = "page", defaultValue = "1") int page,
		@RequestParam(name = "keyword", defaultValue = "") String keyword, ModelMap map) {
		page--;
		Page<TaikhoanEntity> pSeller;
		Page<TaikhoanEntity> pUser;
		if(keyword == "") {
			pSeller = taikhoanService.findAllSeller(page, Constant.PAGESIZE_TAIKHOAN);
			pUser = taikhoanService.findAllUser(page, Constant.PAGESIZE_TAIKHOAN);
		}
		else {
			pSeller = taikhoanService.searchTaikhoan(page, Constant.PAGESIZE_TAIKHOAN, roleEnum.SELLER, keyword);
			pUser = taikhoanService.searchTaikhoan(page, Constant.PAGESIZE_TAIKHOAN, roleEnum.USER, keyword);
		}
		map.addAttribute("pUser", pUser);
		map.addAttribute("pSeller", pSeller);
		map.addAttribute("keyword", keyword);
		return "admin/list-taikhoan";
	}
	
	@GetMapping("them-taikhoan")
	public String getFormThemTaikhoan() {
		return "admin/them-taikhoan";
	}
	
	@PostMapping("them-taikhoan")
	public String themTaikhoan(@Valid @ModelAttribute("taikhoan") TaikhoanModel taikhoanModel) throws IOException {
		TaikhoanEntity taikhoanEntity = new TaikhoanEntity();
		BeanUtils.copyProperties(taikhoanModel, taikhoanEntity);
		taikhoanEntity.setTrangthai(true);
		// String filename = imageService.save(taikhoanModel.getAnhdd());
		String filename = cloudinaryService.uploadFile(taikhoanModel.getAnhdd());
		taikhoanEntity.setAnhdd(filename);
		taikhoanService.SavedRequest(taikhoanEntity);
		return "redirect:/admin/taikhoan";
	}
	
	@GetMapping("chinhsua-taikhoan")
	public String getFormChinhSuaTaikhoan(@RequestParam("id") Integer id, ModelMap map) {
		TaikhoanEntity taikhoanEntity = taikhoanService.findById(id);
		map.addAttribute("user", taikhoanEntity);
		return "admin/chinhsua-taikhoan";
	}
	
	@PostMapping("chinhsua-taikhoan")
	public String chinhsuaTaikhoan(@Valid @ModelAttribute("taikhoan") TaikhoanModel taikhoanModel) throws IOException {
		TaikhoanEntity taikhoanEntity = new TaikhoanEntity();
		BeanUtils.copyProperties(taikhoanModel, taikhoanEntity);
		TaikhoanEntity oldTaikhoan = taikhoanService.findById(taikhoanModel.getTaikhoanId());
		String oldFilename = oldTaikhoan.getAnhdd();
		if(taikhoanModel.getAnhdd().isEmpty()) {
			taikhoanEntity.setAnhdd(oldFilename);
		}
		else {
			// String newFilename = imageService.save(taikhoanModel.getAnhdd());
			// imageService.delete(oldFilename);
			String newFilename = cloudinaryService.uploadFile(taikhoanModel.getAnhdd());
			cloudinaryService.deleteImage(oldFilename);
			taikhoanEntity.setAnhdd(newFilename);
		}
		taikhoanService.SavedRequest(taikhoanEntity);
		return "redirect:/admin/taikhoan";
	}
	
	@GetMapping("xoa-taikhoan")
	public String xoaTaikhoan(@RequestParam("id") Integer id) throws IOException {
		TaikhoanEntity taikhoanEntity = taikhoanService.findById(id);
		if(taikhoanEntity != null) {
			cloudinaryService.deleteImage(taikhoanEntity.getAnhdd());
			taikhoanService.deleteById(id);
		}
		return "redirect:/admin/taikhoan";
	}
}
