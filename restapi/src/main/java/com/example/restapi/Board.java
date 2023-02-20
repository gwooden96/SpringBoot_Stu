package com.example.restapi;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Board {

	@Id //기본키 설정
	private Integer no;
	private String title;
	private String content;
	private String id;
	private Date postdate;
	private Integer visitcount;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<Comm> commentList;
	
}
