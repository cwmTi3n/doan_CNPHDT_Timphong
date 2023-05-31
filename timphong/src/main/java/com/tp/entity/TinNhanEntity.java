package com.tp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tinnhan")
public class TinNhanEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tinnhanId")
    private int tinnhanId;

    @Column(name = "noidung", columnDefinition = "nvarchar(500)")
    private String noidung;

    @Temporal(TemporalType.DATE)
	@Column(name = "thoigian")
    private Date thoigian;

    @Column(name = "trangthai")
    private int trangthai;

    @ManyToOne()
    @JoinColumn(name = "nguoiguiId")
    @JsonIgnore
    private TaikhoanEntity nguoigui;

    @ManyToOne()
    @JoinColumn(name = "nguoinhanId")
    @JsonIgnore
    private TaikhoanEntity nguoinhan;
}
