package com.tp.util;

import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.tp.entity.TaikhoanEntity;
import com.tp.model.CustomUserDetail;

public class Constant {
    public static final int PAGESIZE_PHONG = 9;
    public static final int PAGESIZE_TAIKHOAN = 3;
    public static String getCode() {
        int codeLength = 6; // độ dài của mã xác nhận
        String chars = "0123456789"; // chỉ sử dụng các chữ số
        StringBuilder code = new StringBuilder(); // tạo một StringBuilder để lưu mã xác nhận
        // tạo một đối tượng Random để tạo số ngẫu nhiên
        Random random = new Random();
        // tạo mã xác nhận bằng cách chọn ngẫu nhiên các ký tự từ chuỗi chars
        for (int i = 0; i < codeLength; i++) {
            int index = random.nextInt(chars.length());
            code.append(chars.charAt(index));
        }
        return code.toString();
    }
    public static TaikhoanEntity getUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        return userDetails.getTaikhoanentity();
    }

    public static final String protocol = "https://";
}
