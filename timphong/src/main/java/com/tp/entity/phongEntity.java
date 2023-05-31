package com.tp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

@Table(name = "phong")
public class PhongEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "phongId")
	private int phongId;
	
	@Column(name = "ten" , columnDefinition = "nvarchar(255)")
	private String ten;
	
	@Column(name = "anhchinh")
	private String anhchinh;
	
	@Column(name = "trangthai")
	private boolean trangthai;
	
	@Column(name = "chieudai")
	private float chieudai;
	
	@Column(name = "chieurong")
	private float chieurong;

	@Enumerated(EnumType.STRING)
	private hienthiEnum hienthi;
	
	@Column(name = "gia")
	private int gia;
	
	@Column(name = "diachi" , columnDefinition = "nvarchar(255)")
	private String diachi;
	
	@Column(name = "diachict", columnDefinition = "nvarchar(255)")
	private String diachict;
	
	@Column(name = "mota" , columnDefinition = "nvarchar(500)")
	private String mota;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ngaydang")
	private Date ngaydang;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loaiphongId")
	@JsonIgnore
	private LoaiphongEntity loaiphong;
	
	@ManyToOne()
	@JsonManagedReference
	@JoinColumn(name = "taikhoanId")
	private TaikhoanEntity taikhoan;

	@OneToMany(mappedBy = "phong", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BinhluanEntity> binhluans;

	@OneToMany(mappedBy = "phong", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<LichhenEntity> lichhens;

	@OneToMany(mappedBy = "phong", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<QuantamEntity> quantams;

	@OneToMany(mappedBy = "phong", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<HinhanhEntity> hinhanhs;
}
