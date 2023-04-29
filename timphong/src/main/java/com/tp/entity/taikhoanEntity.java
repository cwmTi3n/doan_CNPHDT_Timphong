package com.tp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "taikhoan")
public class taikhoanEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taikhoanId")
	private int taikhoanId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
    @Enumerated(EnumType.STRING)
    private roleEnum role;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "sdt")
	private String sdt;
	
	@Column(name = "hoten", columnDefinition = "nvarchar(255)")
	private String hoten;
	
	@Column(name = "anhdd")
	private String anhdd;
	
	@Column(name = "trangthai")
	private boolean trangthai;
	
	@OneToMany(mappedBy = "taikhoan", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<phongEntity> phongs;
	
	@PreRemove
	private void proRemove() {
		phongs.forEach(i -> i.setLoaiphong(null));
	}
	@OneToMany(mappedBy = "taikhoan", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<binhluanEntity> binhluans;
	
	@OneToMany(mappedBy = "taikhoan", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<traloiEntity> tralois;

	@OneToMany(mappedBy = "taikhoan", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<lichhenEntity> lichhens;

	@OneToMany(mappedBy = "taikhoan", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<quantamEntity> quantams;
	
}
