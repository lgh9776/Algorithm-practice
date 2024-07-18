package com.ssafy.ws.step3;

/**
 * BookManager 클래스를 이용하여 도서 객체 추가,삭제,조회하는 클래스
 */
public class BookTest {
	public static void main(String[] args) {
		//도서 정보를 배열로 관리해줄 매니저 객체 생성
		BookManager bookManager = new BookManager();
		
		//관리할 도서 추가
		bookManager.add(new Book("21424", "Java Pro", "김하나", "jaen.kr", 15000, "Java 기본 문법"));
		bookManager.add(new Book("21425", "Java Pro2", "김하나", "jaen.kr", 25000, "Java 응용"));
		bookManager.add(new Book("35355", "분석설계", "소나무", "jaen.kr", 30000, "SW 모델링"));
		bookManager.add(new Magazine("45678", "월간 알고리즘", "홍길동", "jaen.kr", 10000, "1월 알고리즘", 2021, 1));
		
		//도서 전체 목록 배열 가져옴
		Book[] bookList = bookManager.getList();
		for(int row = 0; bookList[row] != null; row++) //출력
			System.out.println(bookList[row].toString());
		
		//일반 도서 목록 배열 가져옴
		Book[] basicBookList = bookManager.getBooks();
		for(int row = 0; basicBookList[row] != null; row++) //출력
			System.out.println(basicBookList[row].toString());
		
		//일반 도서 목록 배열 가져옴
		Book[] magazineList = bookManager.getMagazine();
		for(int row = 0; magazineList[row] != null; row++) //출력
			System.out.println(magazineList[row].toString());
		
		//해당 단어가 포함된 타이틀을 가진 도서 목록 배열 가져옴
		Book[] findTitle = bookManager.searchByTitle("Java");
		for(int row = 0; findTitle[row] != null; row++) //출력
			System.out.println(findTitle[row].toString());
		
		//도서 가격 총합 가져와서 출력
		System.out.println("도서 가격 총합 : " + bookManager.getTotalPrice());
		
		//도서 가격 평균값 가져와서 출력
		System.out.println("도서 가격 평균 : " + bookManager.getPriceAvg());
	}
}
