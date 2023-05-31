package com.tp.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tp.entity.BinhluanEntity;

public interface BinhluanRepository extends JpaRepository<BinhluanEntity, Integer>{
    @Query("select bl from BinhluanEntity bl where bl.phong.phongId = :id")
    List<BinhluanEntity> findByPhongId(int id, Sort sort);
}
