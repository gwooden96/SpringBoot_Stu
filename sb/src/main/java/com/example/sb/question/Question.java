package com.example.sb.question;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.sb.answer.Answer;
import com.example.sb.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Question {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //질문코드
	
	@Column(length = 200) //varchar(200)
	private String subject;
	
	@Column(columnDefinition = "TEXT") //varchar
	private String content;
	
	private LocalDateTime createDate; //질문 작성일
	
	//질문이 지워지면 답변들도 같이 지워지게
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	@ManyToOne
	private SiteUser author; //질문을 작성한 질문자 정보
	
	private LocalDateTime modifyDate; //수정날짜
	
	@ManyToMany //한 사람이 여러 게시글에 투표를 할 수 있다보니 ManyToMan를 사용
	Set<SiteUser> voter; //set 컬렉션으로 중복처리, 어떤 유저가 투표했는지 SiteUser 정보 가져오기
}
