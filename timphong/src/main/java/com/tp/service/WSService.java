package com.tp.service;

import com.tp.entity.TaikhoanEntity;

public interface WSService {
    void sendMessage(TaikhoanEntity nguoigui, TaikhoanEntity nguoinhan, String noidung);
}
