package com.tp.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tp.entity.binhluanEntity;
import com.tp.service.binhluanService;

@Controller
public class loadBinhluanController {
    @Autowired binhluanService binhluanService;
    @GetMapping("/binhluan")
    @ResponseBody
    public List<binhluanEntity> get(@RequestParam int id) {
        return binhluanService.findByPhongId(id);
    }
}
