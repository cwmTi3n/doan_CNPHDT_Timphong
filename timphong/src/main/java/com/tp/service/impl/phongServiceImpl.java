package com.tp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tp.entity.LoaiphongEntity;
import com.tp.entity.PhongEntity;
import com.tp.entity.TaikhoanEntity;
import com.tp.repository.PhongRepository;
import com.tp.service.LoaiphongService;
import com.tp.service.PhongService;
import com.tp.service.TaikhoanService;

@Service
public class PhongServiceImpl implements PhongService {
	@Autowired
    PhongRepository phongRepository;
	
	@Autowired
    LoaiphongService loaiphongService;

	@Autowired
	TaikhoanService taikhoanService;

	@Override
	public Page<PhongEntity> findAll(boolean trangthai, int page, int size, String orderby){
		Pageable pageable = PageRequest.of(page, size, getSort(orderby));
		return phongRepository.findByTrangthai(trangthai, pageable);
	}
	
	@Override
	public PhongEntity findById(boolean trangthai, Integer id) {
		return phongRepository.findByTrangthaiAndPhongId(trangthai, id);
	}
	
	@Override
	public void deleteById(Integer id) {
		phongRepository.deleteById(id);
	}
	
	@Override
	public void delete(PhongEntity phongEntity) {
		phongRepository.delete(phongEntity);
	}
	
	@Override
	public <S extends PhongEntity> S SavedRequest(S entity) {
		return phongRepository.save(entity);
	}

	@Override
	public Page<PhongEntity> searchPhong(boolean trangthai, String keyword, String orderby, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(orderby));
		return phongRepository.searchPhong(trangthai, keyword, pageable);
	}

	@Override
	public Page<PhongEntity> findByLoaiphong(boolean trangthai, Integer loaiphongId, String orderby, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, getSort(orderby));
		LoaiphongEntity loaiphongEntity = loaiphongService.findById(loaiphongId);
		return phongRepository.findByLoaiphongAndTrangthai(loaiphongEntity, trangthai, pageable);
	}

	@Override
	public Page<PhongEntity> filterPhong(boolean trangthai, String keyword, Integer loaiphongId, String tinh, String huyen, String xa, String orderby,
										 int page, int size) {
		Pageable pageable = PageRequest.of(page, size, getSort(orderby));
		if(keyword.equals("") && loaiphongId == 0 && tinh.equals("")) {
			return phongRepository.findByTrangthai(trangthai, pageable);
		}
		if(loaiphongId == 0) {
			return phongRepository.filterPhongNoLoaiphong(trangthai, keyword, tinh, huyen, xa, pageable);
		}
		return phongRepository.filterPhong(trangthai, keyword, loaiphongId, tinh, huyen, xa, pageable);		
	}

	//seller
	@Override
	public Page<PhongEntity> findBySeller(Integer id, int page, int size, String orderby) {
		Pageable pageable = PageRequest.of(page, size, getSort(orderby));
		TaikhoanEntity taikhoanEntity = taikhoanService.findById(id);
		return phongRepository.findByTaikhoanAndTrangthai(taikhoanEntity, true, pageable);
	}

	private Sort getSort(String orderby) {
		Sort sort;
		if(orderby.contains("desc")) {
			orderby = orderby.substring(4, orderby.length());
			sort = Sort.by(orderby).descending();
		}
		else {
			sort = Sort.by(orderby);
		}
		return sort;
	}

	@Override
	public Page<PhongEntity> sellerSearchphong(Integer id, String keyword, int page, int size, String orderby) {
		Pageable pageable = PageRequest.of(page, size, getSort(orderby));
		return phongRepository.sellerSearchphong(id, keyword, pageable);
	}

	@Override
	public Page<PhongEntity> sellerFilterphong(Integer id, String keyword, Integer loaiphongId, String tinh,
											   String huyen, String xa, String orderby, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, getSort(orderby));
		if(keyword.equals("") && loaiphongId == 0 && tinh.equals("")) {
			TaikhoanEntity taikhoanEntity = taikhoanService.findById(id);
			return phongRepository.findByTaikhoanAndTrangthai(taikhoanEntity, true, pageable);
		}
		if(loaiphongId == 0) {
			return phongRepository.sellerFilterPhongNoLoaiphong(id, keyword, tinh, huyen, xa, pageable);
		}
		return phongRepository.sellerFilterPhong(id, keyword, loaiphongId, tinh, huyen, xa, pageable);		
	}

	@Override
	public int phongHien() {
		return phongRepository.findByTrangthai(true).size();
	}

	@Override
	public int phongAn() {
		return phongRepository.findByTrangthai(false).size();
	}

	@Override
	public void deletePhongExpire() {
		List<PhongEntity> phongs = phongRepository.findAll();
		long currentTimeMillis = new Date().getTime();
		long sevenDaysInMillis = 7 * 24 * 60 * 60 * 1000; 
		for(PhongEntity p : phongs) {
			if(currentTimeMillis - p.getNgaydang().getTime() > sevenDaysInMillis) {
				phongRepository.delete(p);
			}
		}
	}
}
