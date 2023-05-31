package com.tp.entity;

import javax.persistence.Column;
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
public class QuantamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quantamId")
    private int quantamId;

    @ManyToOne()
    @JoinColumn(name = "taikhoanId")
    private TaikhoanEntity taikhoan;

    @ManyToOne()
    @JoinColumn(name = "phongId")
    private PhongEntity phong;
}
