package com.tp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hinhanh")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HinhanhEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hinhanhId")
    private int hinhanhId;

    @Column(name = "url")
    private String url;

    @ManyToOne()
    @JoinColumn(name = "phongId")
    @JsonIgnore
    private PhongEntity phong;
}
