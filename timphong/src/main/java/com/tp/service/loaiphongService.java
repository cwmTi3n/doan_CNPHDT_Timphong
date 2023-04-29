package com.tp.service;

import java.util.List;

import com.tp.entity.loaiphongEntity;

public interface loaiphongService {

	<S extends loaiphongEntity> S SavedRequest(S entity);

	void delete(loaiphongEntity loaiphongEntity);

	void deleteById(Integer id);

	loaiphongEntity findById(Integer id);

	List<loaiphongEntity> findAll();

}
