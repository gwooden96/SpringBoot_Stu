package com.example.restapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor //매개변수를 다 받는 생성자
@NoArgsConstructor
public class UserVO {
	
	private Integer id;
	private String name;
	private String pw;

}
