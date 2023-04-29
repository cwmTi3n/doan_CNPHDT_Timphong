package com.tp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tp.entity.roleEnum;
import com.tp.entity.taikhoanEntity;

public interface taikhoanService {

	<S extends taikhoanEntity> S SavedRequest(S entity);

	void delete(taikhoanEntity taikhoanEntity);

	void deleteById(Integer id);

	taikhoanEntity findById(Integer id);

	List<taikhoanEntity> findAll();

	Page<taikhoanEntity> findAllUser(int page, int size);

	Page<taikhoanEntity> findAllSeller(int page, int size);

	Page<taikhoanEntity> searchTaikhoan(int page, int size, roleEnum role, String keyword);
	
	taikhoanEntity findByUsername(String username);
}
