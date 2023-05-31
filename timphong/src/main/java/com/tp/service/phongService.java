package com.tp.service;

import org.springframework.data.domain.Page;

import com.tp.entity.PhongEntity;

public interface PhongService {

	<S extends PhongEntity> S SavedRequest(S entity);

	void delete(PhongEntity phongEntity);

	void deleteById(Integer id);

	PhongEntity findById(boolean trangthai, Integer id);

	Page<PhongEntity> findAll(boolean trangthai, int page, int size, String orderby);

	Page<PhongEntity> searchPhong(boolean trangthai, String keyword, String orderby, int page, int size);

	Page<PhongEntity> findByLoaiphong(boolean trangthai, Integer loaiphongId, String orderby, int page, int size);

	Page<PhongEntity> filterPhong(boolean trangthai, String keyword , Integer loaiphongId, String tinh,
                                  String huyen,
                                  String xa,
                                  String orderby, int page, int size);
	Page<PhongEntity> findBySeller(Integer id, int page, int size, String orderby);

	Page<PhongEntity> sellerSearchphong(Integer id, String keyword, int page, int size, String orderby);

	Page<PhongEntity> sellerFilterphong(Integer id, String keyword , Integer loaiphongId, String tinh,
                                        String huyen,
                                        String xa,
                                        String orderby, int page, int size);
	int phongHien();
	int phongAn();
	void deletePhongExpire();
}
