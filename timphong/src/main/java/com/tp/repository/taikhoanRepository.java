package com.tp.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp.entity.roleEnum;
import com.tp.entity.TaikhoanEntity;

public interface TaikhoanRepository extends JpaRepository<TaikhoanEntity, Integer>{
	Page<TaikhoanEntity> findAllByRole(roleEnum role, Pageable pageable);
	
	@Query("select t from TaikhoanEntity t where t.role = :role and (t.sdt like %:keyword% or t.username like %:keyword%)")
	Page<TaikhoanEntity> searchTaikhoan(roleEnum role, String keyword, Pageable pageable);

	TaikhoanEntity findByUsername(String username);

	List<TaikhoanEntity> findByRole(roleEnum role);
}
