package com.example.restapi;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter  
@Setter
public class Comm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer code; //댓글코드
	private String id; //작성자 아이디
	private String content; //댓글내용
	
	@JsonBackReference
	@ManyToOne
	private Board board; //댓글 단 게시물
	
	

}
