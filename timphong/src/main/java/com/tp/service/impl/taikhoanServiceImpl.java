package com.tp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tp.entity.roleEnum;
import com.tp.entity.taikhoanEntity;
import com.tp.repository.taikhoanRepository;
import com.tp.service.taikhoanService;

@Service
public class taikhoanServiceImpl implements taikhoanService{
	@Autowired
	taikhoanRepository taikhoanRepository;
	
	@Override
	public List<taikhoanEntity> findAll(){
		return taikhoanRepository.findAll();
	}
	
	@Override
	public taikhoanEntity findById(Integer id) {
		Optional<taikhoanEntity> optional = taikhoanRepository.findById(id);
		if(optional != null) {
			if(optional.isPresent()) {
				return optional.get();
			}
		}
		return null;
	}
	
	@Override
	public void deleteById(Integer id) {
		taikhoanRepository.deleteById(id);
	}
	
	@Override
	public void delete(taikhoanEntity taikhoanEntity) {
		taikhoanRepository.delete(taikhoanEntity);
	}
	
	@Override
	public <S extends taikhoanEntity> S SavedRequest(S entity) {
		return taikhoanRepository.save(entity);
	}
	
	@Override
	public Page<taikhoanEntity> findAllSeller(int page, int size){
		Pageable pageable = PageRequest.of(page, size, Sort.by("username"));
		return taikhoanRepository.findAllByRole(roleEnum.SELLER, pageable);
	}
	
	@Override
	public Page<taikhoanEntity> findAllUser(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("username"));
		return taikhoanRepository.findAllByRole(roleEnum.USER, pageable);
	}

	@Override
	public Page<taikhoanEntity> searchTaikhoan(int page, int size, roleEnum role, String keyword) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("username"));
		return taikhoanRepository.searchTaikhoan(role, keyword, pageable);
	}

	@Override
	public taikhoanEntity findByUsername(String username) {
		return taikhoanRepository.findByUsername(username);
	}

	@Override
	public int findByRole(roleEnum role) {
		return taikhoanRepository.findByRole(role).size();
	}

}
