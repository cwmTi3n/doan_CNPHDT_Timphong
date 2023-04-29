package com.tp.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tp.service.phongService;

@Component
public class phongSchedule {
    @Autowired
    phongService phongService;
    @Scheduled(cron = "0 0 0 * * ?")
    public void deletePhongExpire() {
        phongService.deletePhongExpire();
        System.out.println("Delete Phong Expire");
    }
}