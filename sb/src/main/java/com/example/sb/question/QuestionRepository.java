package com.example.sb.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

	Question findBySubject(String sub);
	Question findBySubjectAndContent(String sub, String con);
	List<Question> findBySubjectLike(String sub);
	Page<Question> findAll(Pageable pageable);
	Page<Question> findAll(Specification<Question> spec, Pageable pageable);
	
	/* JPA없이 익숙한 쿼리문으로 구축시 아래처럼 구현을 할 수 있다 (참고용)*/
//	@Query("select "
//			+ "distinct q "
//			+ "from Question q "
//			+ "left outer join SiteUser u1 on q.author = u1 "
//			+ "left outer join Answer a on a.question = q "
//			+ "left outer join SiteUser u2 on a.author = u2"
//			+ 	"where"
//			+		"q.subject like %:kw%"
//			+		"or q.content like %:kw%"
//			+		"or u1.username like %:kw%"
//			+		"or a.content like %:kw%"
//			+		"or u2.username like %:kw%"
//		)
//	Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
}
