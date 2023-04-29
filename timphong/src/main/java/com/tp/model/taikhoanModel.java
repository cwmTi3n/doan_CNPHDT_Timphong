package com.tp.model;

import org.springframework.web.multipart.MultipartFile;

import com.tp.entity.roleEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class taikhoanModel {
	private int taikhoanId;
	private String username;
	private String password;
	private String sdt;
	private String email;
	private roleEnum role;
	private String hoten;
	private MultipartFile anhdd;
	private boolean trangthai;
}
