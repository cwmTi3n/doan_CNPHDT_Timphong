package com.tp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "binhluan")
public class BinhluanEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "binhluanId")
    private int binhluanId;

    @Column(name = "noidung", columnDefinition = "nvarchar(500)")
    private String noidung;

	@Temporal(TemporalType.DATE)
	@Column(name = "thoigian")
    private Date thoigian;

    @ManyToOne()
    @JoinColumn(name = "taikhoanId")
    @JsonManagedReference
    private TaikhoanEntity taikhoan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phongId")
    @JsonIgnore
    private PhongEntity phong;

	@OneToMany(mappedBy = "binhluan", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TraloiEntity> tralois;

}
