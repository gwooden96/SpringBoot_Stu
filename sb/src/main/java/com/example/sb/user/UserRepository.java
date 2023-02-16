package com.example.sb.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
	
	//select * from site_user where username = ?;
	Optional<SiteUser> findByUsername(String username);

}
