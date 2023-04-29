package com.tp.controller.seller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.tp.service.*;
import com.tp.util.Constant;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tp.entity.hinhanhEntity;
import com.tp.entity.loaiphongEntity;
import com.tp.entity.phongEntity;
import com.tp.model.customUserDetail;
import com.tp.model.phongModel;

@Controller
@RequestMapping("seller")
public class crudPhongSellerController {
	@Autowired
	taikhoanService taikhoanService;
	@Autowired
	phongService phongService;
	@Autowired
	loaiphongService loaiphongService;
	@Autowired
	imageService imageService;
	@Autowired
	cloudinaryService cloudinaryService;
	@Autowired
	hinhanhService hinhanhService;
	@GetMapping("phong")
	public String getQuanlyphong(@RequestParam(name = "keyword", defaultValue = "") String keyword,
	@RequestParam(name = "orderby", defaultValue = "ten") String orderby, ModelMap map, HttpSession session) {
		List<loaiphongEntity> loaiphongs = loaiphongService.findAll();
		Page<phongEntity> pPhong;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		customUserDetail userDetails = (customUserDetail) authentication.getPrincipal();
		if(keyword.equals("")) {
			pPhong = phongService.findBySeller(userDetails.getTaikhoanentity().getTaikhoanId(), 0, Constant.PAGESIZE_PHONG, orderby);
		}
		else {
			pPhong = phongService.sellerSearchphong(userDetails.getTaikhoanentity().getTaikhoanId(), keyword, 0, Constant.PAGESIZE_PHONG, orderby);
		}
		map.addAttribute("loaiphongs", loaiphongs);
		map.addAttribute("pPhong", pPhong);
		map.addAttribute("keyword", keyword);
		return "seller/quanlyphong";
	}
	
	@PostMapping("them-phong")
	public String themPhong(@Valid @ModelAttribute("phong") phongModel phongModel,
	@RequestParam MultipartFile anhphu1, @RequestParam MultipartFile anhphu2) throws IOException {
		phongModel.setTrangthai(true);
		phongModel.setNgaydang(new Date());
		phongEntity phongEntity = new phongEntity();
		loaiphongEntity lpEntity = loaiphongService.findById(phongModel.getLoaiphongId());
		BeanUtils.copyProperties(phongModel, phongEntity);
		phongEntity.setLoaiphong(lpEntity);
		// String filename = imageService.save(phongModel.getAnhchinh());
		String filename = cloudinaryService.uploadFile(phongModel.getAnhchinh());
		phongEntity.setAnhchinh(filename);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		customUserDetail userDetails = (customUserDetail) authentication.getPrincipal();

		phongEntity.setTaikhoan(userDetails.getTaikhoanentity());	
		if(phongService.SavedRequest(phongEntity) != null) {
			savaImage(anhphu1, phongEntity);
			savaImage(anhphu2, phongEntity);
		}
		return "redirect:/seller/phong";
	}
	
	@GetMapping("xoa-phong")
	public String xoaPhong(@RequestParam("id") Integer id) throws IOException {
		phongEntity phongEntity = phongService.findById(true, id);
		List<hinhanhEntity> hinhanhs = hinhanhService.findByPhong(phongEntity);
		for(hinhanhEntity ha : hinhanhs) {
			cloudinaryService.deleteImage(ha.getUrl());
		}
		// imageService.delete(phongEntity.getAnhchinh());
		cloudinaryService.deleteImage(phongEntity.getAnhchinh());
		phongService.deleteById(id);
		return "redirect:/seller/phong";
	}
	
	@GetMapping("chinhsua-phong")
	public String getFormChinhsuaPhong(@RequestParam("id") Integer id, ModelMap map) {
		phongEntity phongEntity = phongService.findById(true, id);
		List<loaiphongEntity> loaiphongs = loaiphongService.findAll();
		map.addAttribute("loaiphongs", loaiphongs);
		map.addAttribute("phong", phongEntity);
		List<hinhanhEntity> hinhanhs = phongEntity.getHinhanhs();
		map.addAttribute("hinhanhs", hinhanhs);
		return "seller/chinhsua-phong";
	}
	
	@PostMapping("chinhsua-phong")
	public String chinhsuaPhong(@Valid @ModelAttribute("phong") phongModel phongModel, 
	@RequestParam MultipartFile anhphu1, 
	@RequestParam MultipartFile anhphu2,
	@RequestParam(defaultValue = "") String url1,
	@RequestParam(defaultValue = "") String url2) throws IOException {
		phongEntity phongEntity = new phongEntity();
		loaiphongEntity lpEntity = loaiphongService.findById(phongModel.getLoaiphongId());
		BeanUtils.copyProperties(phongModel, phongEntity);
		phongEntity.setLoaiphong(lpEntity);
		phongEntity oldPhogEntity = phongService.findById(true, phongModel.getPhongId());
		phongEntity.setNgaydang(oldPhogEntity.getNgaydang());
		String oldFilename = oldPhogEntity.getAnhchinh();
		if(phongModel.getAnhchinh() == null || phongModel.getAnhchinh().isEmpty()) {
			phongEntity.setAnhchinh(oldFilename);
		}
		else {
			// String filename = imageService.save(phongModel.getAnhchinh());
			// imageService.delete(oldFilename);
			String filename = cloudinaryService.uploadFile(phongModel.getAnhchinh());
			cloudinaryService.deleteImage(oldFilename);
			phongEntity.setAnhchinh(filename);
		}
		phongEntity.setTrangthai(true);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		customUserDetail userDetails = (customUserDetail) authentication.getPrincipal();

		phongEntity.setTaikhoan(userDetails.getTaikhoanentity());
		phongService.SavedRequest(phongEntity);
		editImage(anhphu1, url1, phongEntity);
		editImage(anhphu2, url2, phongEntity);
		return "redirect:/seller/phong";
	}

	@GetMapping("delete-anhphu/{id}")
	public String deleteAnhphu(@PathVariable int id) throws IOException {
		hinhanhEntity hinhanhEntity = hinhanhService.findById(id);
		if(hinhanhEntity != null) {
			hinhanhService.deleteById(id);
			cloudinaryService.deleteImage(hinhanhEntity.getUrl());
			return "redirect:/seller/chinhsua-phong?id=" + String.valueOf(hinhanhEntity.getPhong().getPhongId());
		}
		return "redirect:/seller/phong";
	}

	private void savaImage(MultipartFile image, phongEntity phongEntity) {
		try {
			String filename = cloudinaryService.uploadFile(image);
			if(filename != null) {
				hinhanhEntity hinhanhEntity = new hinhanhEntity();
				hinhanhEntity.setPhong(phongEntity);
				hinhanhEntity.setUrl(filename);
				hinhanhService.SavedRequest(hinhanhEntity);
			}
		} catch (IOException e) {
		}
	}

	private void editImage(MultipartFile newImage, String url, phongEntity phongEntity) throws IOException {
		if( newImage != null && !newImage.isEmpty()) {
			if(url.equals("")) {
				savaImage(newImage, phongEntity);
			}
			else {
				hinhanhEntity hinhanhEntity = hinhanhService.findByUrl(url);
				if(hinhanhEntity != null) {
					cloudinaryService.deleteImage(url);
					String filename = cloudinaryService.uploadFile(newImage);
					if(filename != null) {
						hinhanhEntity.setUrl(filename);
						hinhanhService.SavedRequest(hinhanhEntity);
					}
				}
			}
		}
	}
}
