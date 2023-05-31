package com.tp.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tp.service.PhongService;

@Component
public class PhongSchedule {
    @Autowired
    PhongService phongService;
    @Scheduled(cron = "0 0 0 * * ?")
    public void deletePhongExpire() {
        phongService.deletePhongExpire();
        System.out.println("Delete Phong Expire");
    }
}