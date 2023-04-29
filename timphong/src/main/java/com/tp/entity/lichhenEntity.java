package com.tp.entity;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lichhen")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class lichhenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lichhenId;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngay")
    private Date ngay;

    @Column(name = "gio")
    private LocalTime gio;

    @Enumerated(EnumType.STRING)
    private ttlichhenEnum trangthai;

    @ManyToOne()
    @JoinColumn(name = "taikhoanId")
    private taikhoanEntity taikhoan;

    @ManyToOne()
    @JoinColumn(name = "phongId")
    private phongEntity phong;
}
