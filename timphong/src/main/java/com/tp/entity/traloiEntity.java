package com.tp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "traloi")
public class TraloiEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "traloiId")
    private int traloiId;

    @Column(name = "noidung", columnDefinition = "nvarchar(500)")
    private String noidung;

    @Temporal(TemporalType.DATE)
    @Column(name = "thoigian")
    private Date thoigian;

    @ManyToOne()
    @JoinColumn(name = "binhluanId")
    @JsonBackReference
    private BinhluanEntity binhluan;

    @ManyToOne()
    @JoinColumn(name = "taikhoanId")
    @JsonManagedReference
    private TaikhoanEntity taikhoan;

    @ManyToOne
    @JoinColumn(name = "parent_traloiId")
    @JsonBackReference
    private TraloiEntity traloi;

    @OneToMany(mappedBy = "traloi", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TraloiEntity> tralois;
}
