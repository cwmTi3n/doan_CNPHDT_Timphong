package com.tp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tp.entity.loaiphongEntity;
import com.tp.entity.phongEntity;
import com.tp.entity.taikhoanEntity;
import com.tp.repository.phongRepository;
import com.tp.service.loaiphongService;
import com.tp.service.phongService;
import com.tp.service.taikhoanService;

@Service
public class phongServiceImpl implements phongService{
	@Autowired
	phongRepository phongRepository;
	
	@Autowired
	loaiphongService loaiphongService;

	@Autowired
	taikhoanService taikhoanService;

	@Override
	public Page<phongEntity> findAll(boolean trangthai, int page, int size, String orderby){
		Pageable pageable = PageRequest.of(page, size, getSort(orderby));
		return phongRepository.findByTrangthai(trangthai, pageable);
	}
	
	@Override
	public phongEntity findById(boolean trangthai, Integer id) {
		return phongRepository.findByTrangthaiAndPhongId(trangthai, id);
	}
	
	@Override
	public void deleteById(Integer id) {
		phongRepository.deleteById(id);
	}
	
	@Override
	public void delete(phongEntity phongEntity) {
		phongRepository.delete(phongEntity);
	}
	
	@Override
	public <S extends phongEntity> S SavedRequest(S entity) {
		return phongRepository.save(entity);
	}

	@Override
	public Page<phongEntity> searchPhong(boolean trangthai, String keyword, String orderby, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(orderby));
		return phongRepository.searchPhong(trangthai, keyword, pageable);
	}

	@Override
	public Page<phongEntity> findByLoaiphong(boolean trangthai, Integer loaiphongId, String orderby, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, getSort(orderby));
		loaiphongEntity loaiphongEntity = loaiphongService.findById(loaiphongId);
		return phongRepository.findByLoaiphongAndTrangthai(loaiphongEntity, trangthai, pageable);
	}

	@Override
	public Page<phongEntity> filterPhong(boolean trangthai, String keyword, Integer loaiphongId, String tinh, String huyen, String xa, String orderby,
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
	public Page<phongEntity> findBySeller(Integer id, int page, int size, String orderby) {
		Pageable pageable = PageRequest.of(page, size, getSort(orderby));
		taikhoanEntity taikhoanEntity = taikhoanService.findById(id);
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
	public Page<phongEntity> sellerSearchphong(Integer id, String keyword, int page, int size, String orderby) {
		Pageable pageable = PageRequest.of(page, size, getSort(orderby));
		return phongRepository.sellerSearchphong(id, keyword, pageable);
	}

	@Override
	public Page<phongEntity> sellerFilterphong(Integer id, String keyword, Integer loaiphongId, String tinh,
			String huyen, String xa, String orderby, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, getSort(orderby));
		if(keyword.equals("") && loaiphongId == 0 && tinh.equals("")) {
			taikhoanEntity taikhoanEntity = taikhoanService.findById(id);
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
		List<phongEntity> phongs = phongRepository.findAll();
		long currentTimeMillis = new Date().getTime();
		long sevenDaysInMillis = 7 * 24 * 60 * 60 * 1000; 
		for(phongEntity p : phongs) {
			if(currentTimeMillis - p.getNgaydang().getTime() > sevenDaysInMillis) {
				phongRepository.delete(p);
			}
		}
	}
}
