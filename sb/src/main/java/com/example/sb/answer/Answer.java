package com.example.sb.answer;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.sb.question.Question;
import com.example.sb.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	
	private LocalDateTime modifyDate; //수정날짜
	
	@ManyToMany //한 사람이 여러 게시글에 투표를 할 수 있다보니 ManyToMan를 사용
	Set<SiteUser> voter; //set 컬렉션으로 중복처리, 어떤 유저가 투표했는지 SiteUser 정보 가져오기
}
