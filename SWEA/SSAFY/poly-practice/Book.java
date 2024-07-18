package com.ssafy.ws.step3;

/*
 * 도서 정보를 나타내는 클래스
 * 1. 멤버 변수 선언
 * 2. 기본 생성자, 파라미터 생성자 생성
 * 3. 각 멤버 변수에 대한 getter, setter 정의
 * 4. 책 정보를 출력해줄 toString 정의
 */
public class Book {
	String isbn; //책 시리얼넘버
	String title; //책 제목
	String author; //저자
	String publisher; //출판사
	int price; //가격
	String desc; //책 추가 정보
	
	//기본 생성자
	Book(){}
	
	//파라미터 생성자
	Book(String isbn, String title, String author, String publisher, int price, String desc){
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.desc = desc;
	}
	
	//각 멤버 변수에 대한 getter(값 리턴), setter(값 세팅) 생성
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	//책 1권 정보 출력
	public String toString() {	
		return "%s | %-7s\t | %s | %s\t | %d | %-7s\t".formatted(isbn, title, author, publisher, price, desc);
	}
}
