package com.tp.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tp.entity.PhongEntity;
import com.tp.service.PhongService;
import com.tp.util.Constant;

@Controller
@RequestMapping("web")
public class LoadPhongController {
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
                                         @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        p--;
        List<PhongEntity> phongs = phongService.filterPhong(true, keyword, loaiphongId, tinh, huyen, xa, orderby, p, Constant.PAGESIZE_PHONG).getContent();
        System.out.println("===============");
        return phongs;
    }
}
