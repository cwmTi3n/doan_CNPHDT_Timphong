package com.tp.controller.web;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tp.entity.LichhenEntity;
import com.tp.entity.PhongEntity;
import com.tp.entity.TaikhoanEntity;
import com.tp.entity.ttlichhenEnum;
import com.tp.model.CustomUserDetail;
import com.tp.service.LichhenService;
import com.tp.service.PhongService;
import com.tp.service.WSService;
import com.tp.util.Constant;

@Controller
public class LichhenController {
    @Autowired
    LichhenService lichhenService;
    @Autowired
    PhongService phongService;
    @Autowired
    WSService wsService;
    private final HttpServletRequest request;
    public LichhenController(HttpServletRequest request) {
        this.request = request;
    }
    @PostMapping("/dathen")
    @ResponseBody
    public ResponseEntity<String> dathen(@RequestParam int phongId, @RequestParam Date ngay, @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime gio) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
            TaikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
            LichhenEntity lichhenEntity = new LichhenEntity();
            lichhenEntity.setTaikhoan(taikhoanEntity);
            PhongEntity phongEntity = phongService.findById(true, phongId);
            lichhenEntity.setPhong(phongEntity);
            lichhenEntity.setTrangthai(ttlichhenEnum.DATHEN);
            lichhenEntity.setNgay(ngay);
            lichhenEntity.setGio(gio);
            // Format ngày
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String ngayString = dateFormat.format(ngay);
            // Format giờ
            DateTimeFormatter gioFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String gioString = gio.format(gioFormatter);
            String noidung = "Tôi muốn xem phòng của bạn vào lúc " + gioString + " " + ngayString + ": ";
            String url = Constant.protocol + request.getServerName() + "/detail-phong/" + String.valueOf(phongId);
            wsService.sendMessage(taikhoanEntity, phongEntity.getTaikhoan(), noidung + url);
            lichhenService.SavedRequest(lichhenEntity);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Có lỗi xảy ra khi đặt lịch");
        }
    }

    @GetMapping("account/list-lichhen")
    public String getLichhen(ModelMap map, @RequestParam(defaultValue = "0") int trangthai) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        TaikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
        List<LichhenEntity> lichhens = lichhenService.findByTrangthai(ttlichhenEnum.values()[trangthai], taikhoanEntity);
        map.addAttribute("lichhens", lichhens);
        map.addAttribute("trangthai", trangthai);
        return "web/lichhen.html";
    }

    @GetMapping("account/xoa-lichhen/{id}")
    public String deleteLichhen(@PathVariable Integer id){
        lichhenService.deleteById(id);
        return "redirect:/account/list-lichhen";
    }

    @PostMapping("/chinhsua-lichhen")
    @ResponseBody
    public ResponseEntity<String> editDathen(@RequestParam Date ngay, @RequestParam @DateTimeFormat(pattern = "HH:mm:ss") LocalTime gio, @RequestParam int lichhenId) {
        try {
            LichhenEntity lichhenEntity = lichhenService.findById(lichhenId);
            lichhenEntity.setNgay(ngay);
            lichhenEntity.setGio(gio);
            // Format ngày
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String ngayString = dateFormat.format(ngay);
            // Format giờ
            DateTimeFormatter gioFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String gioString = gio.format(gioFormatter);
            String noidung = "Tôi muốn thay đổi thời gian xem phòng của bạn vào lúc " + gioString + " " + ngayString + ": ";
            String url = Constant.protocol + request.getServerName() + "/detail-phong/" + String.valueOf(lichhenEntity.getPhong().getPhongId());
            wsService.sendMessage(Constant.getUserLogin(), lichhenEntity.getPhong().getTaikhoan(), noidung + url);
            lichhenService.SavedRequest(lichhenEntity);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Có lỗi xảy ra khi chỉnh sửa");
        }
    }
}
