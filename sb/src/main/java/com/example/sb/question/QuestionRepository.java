package com.example.sb.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	Question findBySubject(String sub);
	Question findBySubjectAndContent(String sub, String con);
	List<Question> findBySubjectLike(String sub);
	Page<Question> findAll(Pageable pageable);
	
}
