package com.tp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class binhluanModel {
    private int binhluanId;
    private String noidung;
    private int taikhoanId;
    private int phongId;
}
