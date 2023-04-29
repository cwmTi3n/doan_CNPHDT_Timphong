package com.tp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quantam")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class quantamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quantamId;
    @ManyToOne()
    @JoinColumn(name = "taikhoanId")
    private taikhoanEntity taikhoan;

    @ManyToOne()
    @JoinColumn(name = "phongId")
    private phongEntity phong;
}
