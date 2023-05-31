package com.tp.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tp.entity.BinhluanEntity;
import com.tp.service.BinhluanService;

@Controller
public class LoadBinhluanController {
    @Autowired
    BinhluanService binhluanService;
    @GetMapping("/binhluan")
    @ResponseBody
    public List<BinhluanEntity> getBinhluan(@RequestParam int id) {
        return binhluanService.findByPhongId(id);
    }
}
