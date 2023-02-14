package com.example.sb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@AllArgsConstructor //모든 매개변수를 받아주는 생성자
@NoArgsConstructor //기본 생성자

@Data //웬만한 객체에 필요한 각종 어노테니션 모두 포함된 어노테이션
public class TestLombok {
	
	private String str;
	private int num;
	
	

	public static void main(String[] args) {
		
		TestLombok tl = new TestLombok();
		TestLombok t2 = new TestLombok("hello", 20);
		
		tl.setStr("hi");
		tl.setNum(50);
		

		System.out.println("===========@AllArgsConstructorr============");
		System.out.println(tl.getStr());
		System.out.println(tl.getNum());
		
		System.out.println("===========@NoArgsConstructor============");
		System.out.println(t2.getStr());
		System.out.println(t2.getNum());

	}

}
