package com.tp.controller.seller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tp.entity.LichhenEntity;
import com.tp.entity.TaikhoanEntity;
import com.tp.entity.ttlichhenEnum;
import com.tp.model.CustomUserDetail;
import com.tp.service.LichhenService;
import com.tp.service.WSService;
import com.tp.util.Constant;

@Controller
public class QuanlyLichhenController {
    @Autowired
    LichhenService lichhenService;

    @Autowired
    WSService wsService;
    private final HttpServletRequest request;
    public QuanlyLichhenController(HttpServletRequest request) {
        this.request = request;
    }
    @GetMapping("seller/quanly-lichhen")
    public String quanlyLichhen(ModelMap map, @RequestParam(defaultValue = "0") int trangthai) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        TaikhoanEntity taikhoanEntity = userDetails.getTaikhoanentity();
        List<LichhenEntity> lichhens = lichhenService.findBySellerAndTrangthai(taikhoanEntity.getTaikhoanId(), ttlichhenEnum.values()[trangthai]);
        map.addAttribute("lichhens", lichhens);
        map.addAttribute("trangthai", trangthai);
        return "seller/quanly-lichhen.html";
    }

    @GetMapping("seller/xacnhan-lichhen")
    public String xacnhanLichhen(@RequestParam(defaultValue = "0") int lichhenId) {
        LichhenEntity lichhenEntity = lichhenService.findById(lichhenId);
        if(lichhenEntity != null) {
            lichhenEntity.setTrangthai(ttlichhenEnum.XACNHAN);
            // Format ngày
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String ngayString = dateFormat.format(lichhenEntity.getNgay());
            // Format giờ
            DateTimeFormatter gioFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String gioString = lichhenEntity.getGio().format(gioFormatter);
            String noidung = "Bạn có thể đến xem phòng của tôi như lịch đã đặt vào lúc " + gioString + " " + ngayString + ": ";
            String url = Constant.protocol + request.getServerName() + "/detail-phong/" + String.valueOf(lichhenEntity.getPhong().getPhongId());
            wsService.sendMessage(Constant.getUserLogin(), lichhenEntity.getTaikhoan(), noidung + url);
            lichhenService.SavedRequest(lichhenEntity);
        }
        return "redirect:/seller/quanly-lichhen?trangthai=1";
    }

    @GetMapping("seller/huy-lichhen")
    public String huyLichhen(@RequestParam(defaultValue = "0") int lichhenId) {
        LichhenEntity lichhenEntity = lichhenService.findById(lichhenId);
        if(lichhenEntity != null) {
            lichhenEntity.setTrangthai(ttlichhenEnum.BIHUY);
            // Format ngày
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String ngayString = dateFormat.format(lichhenEntity.getNgay());
            // Format giờ
            DateTimeFormatter gioFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String gioString = lichhenEntity.getGio().format(gioFormatter);
            String noidung = "Tôi bận vào thời gian này: " + gioString + " " + ngayString + ": ";
            String url = Constant.protocol + request.getServerName() + "/detail-phong/" + String.valueOf(lichhenEntity.getPhong().getPhongId());
            wsService.sendMessage(Constant.getUserLogin(), lichhenEntity.getTaikhoan(), noidung + url);
            lichhenService.SavedRequest(lichhenEntity);
        }
        return "redirect:/seller/quanly-lichhen?trangthai=2";
    }
}
