package com.tp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.entity.LoaiphongEntity;
import com.tp.repository.LoaiphongRepository;
import com.tp.service.LoaiphongService;

@Service
public class LoaiphongServiceImpl implements LoaiphongService {
	@Autowired
    LoaiphongRepository lpRepository;
	@Override
	public List<LoaiphongEntity> findAll(){
		return lpRepository.findAll();
	}
	
	@Override
	public LoaiphongEntity findById(Integer id){
		Optional<LoaiphongEntity> lpOptional = lpRepository.findById(id);
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
	public void delete(LoaiphongEntity loaiphongEntity) {
		lpRepository.delete(loaiphongEntity);
	}
	
	@Override
	public <S extends LoaiphongEntity> S SavedRequest(S entity) {
		return lpRepository.save(entity);
	}
}
