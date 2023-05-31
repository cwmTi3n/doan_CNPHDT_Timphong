package com.tp.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tp.entity.roleEnum;
import com.tp.entity.TaikhoanEntity;
import com.tp.service.TaikhoanService;
import com.tp.util.Constant;

@Controller
@RequestMapping("admin")
public class LoadTaikhoanController {
    @Autowired
    TaikhoanService taikhoanService;
    @GetMapping("paging-taikhoan")
	@ResponseBody
	public List<TaikhoanEntity> pagingUser(@RequestParam(name = "page", defaultValue = "1") int page,
                                           @RequestParam(name = "role", defaultValue = "USER") roleEnum role) {
		page--;
		List<TaikhoanEntity> taikhoans = new ArrayList<>();
		if(role == roleEnum.SELLER) {
			taikhoans = taikhoanService.findAllSeller(page, Constant.PAGESIZE_TAIKHOAN).getContent();
		}
		else if (role == roleEnum.USER) {
			taikhoans = taikhoanService.findAllUser(page, Constant.PAGESIZE_TAIKHOAN).getContent();
		}
		return taikhoans;
	}

    @GetMapping("search-taikhoan")
    @ResponseBody
    public List<TaikhoanEntity> searchTaikhoan(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                               @RequestParam(name = "role", defaultValue = "USER") roleEnum role,
                                               @RequestParam(name = "page", defaultValue = "1") int page) {
        List<TaikhoanEntity> taikhoans = new ArrayList<>();
        if(role == roleEnum.USER || role == roleEnum.SELLER) {
            page--;
            taikhoans = taikhoanService.searchTaikhoan(page, Constant.PAGESIZE_TAIKHOAN, role, keyword).getContent();
        }
        return taikhoans;
    }
}
