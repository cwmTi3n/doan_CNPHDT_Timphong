package com.tp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TinNhanModel {
    private int id;
    private String nguoigui;
    private String nguoinhan;
    private String noidung;
    private String thoigian;
}
