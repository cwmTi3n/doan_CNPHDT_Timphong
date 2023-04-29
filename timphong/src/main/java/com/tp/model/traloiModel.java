package com.tp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class traloiModel {
    private int traloiId;
    private String noidung;
    private int binhluanId;
    private int taikhoanId;
    private int parent_traloiId;
}
