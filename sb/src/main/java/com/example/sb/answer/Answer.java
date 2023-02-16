package com.example.sb.answer;

import java.time.LocalDateTime;

import com.example.sb.question.Question;
import com.example.sb.user.SiteUser;

import jakarta.persistence.Column;
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
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //답변 코드
	
	@Column(columnDefinition = "TEXT")
	private String content; //답변 내용
	
	private LocalDateTime createDate; //답변 작성일
	
	
	@ManyToOne
	private Question question; //답변을 달아준 질문

	
	@ManyToOne
	private SiteUser author; //답변을 작성한 작성자 정보
	
	private LocalDateTime modifyDate;
}
