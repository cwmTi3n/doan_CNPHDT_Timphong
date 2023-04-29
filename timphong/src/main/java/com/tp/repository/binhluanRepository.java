package com.tp.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp.entity.binhluanEntity;

public interface binhluanRepository extends JpaRepository<binhluanEntity, Integer>{
    @Query("select bl from binhluanEntity bl where bl.phong.phongId = :id")
    List<binhluanEntity> findByPhongId(int id, Sort sort);
}
