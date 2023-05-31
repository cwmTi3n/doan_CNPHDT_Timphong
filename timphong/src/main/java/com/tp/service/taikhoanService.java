package com.tp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tp.entity.roleEnum;
import com.tp.entity.TaikhoanEntity;

public interface TaikhoanService {

	<S extends TaikhoanEntity> S SavedRequest(S entity);

	void delete(TaikhoanEntity taikhoanEntity);

	void deleteById(Integer id);

	TaikhoanEntity findById(Integer id);

	List<TaikhoanEntity> findAll();

	Page<TaikhoanEntity> findAllUser(int page, int size);

	Page<TaikhoanEntity> findAllSeller(int page, int size);

	Page<TaikhoanEntity> searchTaikhoan(int page, int size, roleEnum role, String keyword);
	
	TaikhoanEntity findByUsername(String username);

	int findByRole(roleEnum role);

}
