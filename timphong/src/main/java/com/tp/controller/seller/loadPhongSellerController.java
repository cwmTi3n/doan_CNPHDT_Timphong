package com.tp.controller.seller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tp.entity.PhongEntity;
import com.tp.model.CustomUserDetail;
import com.tp.service.PhongService;
import com.tp.util.Constant;

@Controller
@RequestMapping("seller")
public class LoadPhongSellerController {
    @Autowired
    PhongService phongService;
    @GetMapping("filter-phong")
    @ResponseBody
    public List<PhongEntity> filterPhong(@RequestParam(name = "loaiphongId", defaultValue = "0") Integer loaiphongId,
                                         @RequestParam(name = "orderby", defaultValue = "ten") String orderby,
                                         @RequestParam(name = "page", defaultValue = "1") int p,
                                         @RequestParam(name = "tinh", defaultValue = "") String tinh,
                                         @RequestParam(name = "huyen", defaultValue = "") String huyen,
                                         @RequestParam(name = "xa", defaultValue = "") String xa,
                                         @RequestParam(name = "keyword", defaultValue = "") String keyword,
                                         HttpSession session) {
        p--;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        return phongService.sellerFilterphong(userDetails.getTaikhoanentity().getTaikhoanId(), keyword, loaiphongId, tinh, huyen, xa, orderby, p, Constant.PAGESIZE_PHONG).getContent();
    }
}
