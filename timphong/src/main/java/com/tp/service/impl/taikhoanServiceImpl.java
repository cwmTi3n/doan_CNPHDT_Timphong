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
import com.tp.entity.TaikhoanEntity;
import com.tp.repository.TaikhoanRepository;
import com.tp.service.TaikhoanService;

@Service
public class TaikhoanServiceImpl implements TaikhoanService {
	@Autowired
    TaikhoanRepository taikhoanRepository;
	
	@Override
	public List<TaikhoanEntity> findAll(){
		return taikhoanRepository.findAll();
	}
	
	@Override
	public TaikhoanEntity findById(Integer id) {
		Optional<TaikhoanEntity> optional = taikhoanRepository.findById(id);
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
	public void delete(TaikhoanEntity taikhoanEntity) {
		taikhoanRepository.delete(taikhoanEntity);
	}
	
	@Override
	public <S extends TaikhoanEntity> S SavedRequest(S entity) {
		return taikhoanRepository.save(entity);
	}
	
	@Override
	public Page<TaikhoanEntity> findAllSeller(int page, int size){
		Pageable pageable = PageRequest.of(page, size, Sort.by("username"));
		return taikhoanRepository.findAllByRole(roleEnum.SELLER, pageable);
	}
	
	@Override
	public Page<TaikhoanEntity> findAllUser(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("username"));
		return taikhoanRepository.findAllByRole(roleEnum.USER, pageable);
	}

	@Override
	public Page<TaikhoanEntity> searchTaikhoan(int page, int size, roleEnum role, String keyword) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("username"));
		return taikhoanRepository.searchTaikhoan(role, keyword, pageable);
	}

	@Override
	public TaikhoanEntity findByUsername(String username) {
		return taikhoanRepository.findByUsername(username);
	}

	@Override
	public int findByRole(roleEnum role) {
		return taikhoanRepository.findByRole(role).size();
	}

}
