package com.tp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loaiphong")
public class LoaiphongEntity implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loaiphongId")
	private int loaiphongId;
	
	@Column(name = "name" , columnDefinition = "nvarchar(255)")
	private String name;
	
	@OneToMany(mappedBy = "loaiphong", fetch = FetchType.LAZY)
	@JsonIgnore	
	private List<PhongEntity> phongs;
	
	@PreRemove
	private void proRemove() {
		phongs.forEach(i -> i.setLoaiphong(null));
	}
}
