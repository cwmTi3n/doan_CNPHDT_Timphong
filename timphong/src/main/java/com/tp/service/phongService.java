package com.tp.service;

import org.springframework.data.domain.Page;

import com.tp.entity.phongEntity;

public interface phongService {

	<S extends phongEntity> S SavedRequest(S entity);

	void delete(phongEntity phongEntity);

	void deleteById(Integer id);

	phongEntity findById(boolean trangthai, Integer id);

	Page<phongEntity> findAll(boolean trangthai, int page, int size, String orderby);

	Page<phongEntity> searchPhong(boolean trangthai, String keyword, String orderby, int page, int size);

	Page<phongEntity> findByLoaiphong(boolean trangthai, Integer loaiphongId, String orderby, int page, int size);

	Page<phongEntity> filterPhong(boolean trangthai, String keyword ,Integer loaiphongId, String tinh,
													String huyen,
													String xa,
													String orderby, int page, int size);
	Page<phongEntity> findBySeller(Integer id, int page, int size, String orderby);

	Page<phongEntity> sellerSearchphong(Integer id, String keyword, int page, int size, String orderby);

	Page<phongEntity> sellerFilterphong(Integer id, String keyword ,Integer loaiphongId, String tinh,
															String huyen,
															String xa,
															String orderby, int page, int size);
	int phongHien();
	int phongAn();
	void deletePhongExpire();
}
