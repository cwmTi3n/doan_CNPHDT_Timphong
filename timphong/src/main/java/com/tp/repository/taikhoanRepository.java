package com.tp.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp.entity.roleEnum;
import com.tp.entity.taikhoanEntity;

public interface taikhoanRepository extends JpaRepository<taikhoanEntity, Integer>{
	Page<taikhoanEntity> findAllByRole(roleEnum role, Pageable pageable);
	
	@Query("select t from taikhoanEntity t where t.role = :role and (t.sdt like %:keyword% or t.username like %:keyword%)")
	Page<taikhoanEntity> searchTaikhoan(roleEnum role, String keyword, Pageable pageable);

	taikhoanEntity findByUsername(String username);

	List<taikhoanEntity> findByRole(roleEnum role);
}
