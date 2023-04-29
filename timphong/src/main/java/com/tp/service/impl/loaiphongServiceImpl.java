package com.tp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.entity.loaiphongEntity;
import com.tp.repository.loaiphongRepository;
import com.tp.service.loaiphongService;

@Service
public class loaiphongServiceImpl implements loaiphongService{
	@Autowired
	loaiphongRepository lpRepository;
	@Override
	public List<loaiphongEntity> findAll(){
		return lpRepository.findAll();
	}
	
	@Override
	public loaiphongEntity findById(Integer id){
		Optional<loaiphongEntity> lpOptional = lpRepository.findById(id);
		if(lpOptional != null) {
			if(lpOptional.isPresent()) {
				return lpOptional.get();
			}
		}
		return null;
	}
	
	@Override
	public void deleteById(Integer id) {
		lpRepository.deleteById(id);
	}
	
	@Override
	public void delete(loaiphongEntity loaiphongEntity) {
		lpRepository.delete(loaiphongEntity);
	}
	
	@Override
	public <S extends loaiphongEntity> S SavedRequest(S entity) {
		return lpRepository.save(entity);
	}
}
