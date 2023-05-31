package com.tp.service;

import java.util.List;

import com.tp.entity.LoaiphongEntity;

public interface LoaiphongService {

	<S extends LoaiphongEntity> S SavedRequest(S entity);

	void delete(LoaiphongEntity loaiphongEntity);

	void deleteById(Integer id);

	LoaiphongEntity findById(Integer id);

	List<LoaiphongEntity> findAll();

}
