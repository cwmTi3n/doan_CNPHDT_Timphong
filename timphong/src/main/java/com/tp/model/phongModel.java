package com.tp.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.tp.entity.hienthiEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhongModel {
	private int phongId;
	private String ten;
	private MultipartFile anhchinh;
	private boolean trangthai;
	private float chieudai;
	private float chieurong;
	private int gia;
	private String diachi;
	private String diachict;
	private String mota;
	private Date ngaydang;
	private int loaiphongId;
	private int taikhoanId;
	private hienthiEnum hienthi;
}
