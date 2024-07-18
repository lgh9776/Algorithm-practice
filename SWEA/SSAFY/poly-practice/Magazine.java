package com.ssafy.ws.step3;

/*
 * Book을 상속 받는 Magazine 클래스
 * 1. 데이터 은닉을 위한 캡슐화 -> 멤버 변수 선언
 * 2. 기본 생성자, 파라미터 생성자 작성
 * 3. 데이터 보호를 위해 getter, setter 정의
 * 4. toString() 재정의
 */

public class Magazine extends Book {
	private int year;
	private int month;
	
	//기본 생성자
	Magazine() {}
	
	//파라미터 생성자
	Magazine(String isbn, String title, String author, String publisher, int price, String desc, int year, int month) {
		//super를 사용하여 중복 코드 제거
		super(isbn, title, author, publisher, price, desc);
		this.year = year;
		this.month = month;
	}
	
	//각 멤버 변수에 대한 getter, setter 정의
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public String toString() {
		//super를 사용하여 중복 코드 제거
		return super.toString() + " | %d | %d".formatted(year, month);
	}
}
