package com.ssafy.ws.step3;

/**
 * 도서리스트를 배열로 유지하며 관리하는 클래스
 * 1. add : 최대 수량을 넘지 않으면 도서정보 추가
 * 2. remove : 원하는 도서 제거, 제거된 빈 부분 없애기
 * 3. getList : 도서정보가 담긴 배열 반환
 * 4. searchByIsbn : isbn으로 도서 찾기 (일치한 도서 정보 반환)
 */
public class BookManager {
	int MAX_SIZE = 100; //도서정보 저장 최대 수량
	Book[] books = new Book[MAX_SIZE]; //도서 정보를 넣을 배열
	int size = 0; //현재 배열의 size
	
	Book[] basicBookList; //일반도서만 담을 배열
	Book[] findByTitle; //타이틀이 포함된 도서 정보를 담은 배열
	Magazine[] magazineList; //잡지만 담을 배열
	
	//책 정보가 담긴 객체를 받아 배열에 추가
	public void add(Book book) {
		if(size < 100) { //최대 수량 넘지 않으면 저장
			books[size] = book;
			size++;
		}
	}
	
	//파라미터 값과 같은 시리얼 넘버를 가진 책 삭제
	public void remove(String isbn) {
		System.out.println("*********************도서삭제:" + isbn + "*********************");
		int isFindIsbn = 0;
		
		for(int row = 0; row < size - 1; row++) {
			if(books[row].isbn.equals(isbn) || isFindIsbn == 1) { 
				books[row] = books[row + 1]; //중간에 삭제하기 위해 1개씩 옮기기
				isFindIsbn = 1; //같은 Isbn 찾음
			}
		}
		books[size - 2] = books[size - 1]; //배열의 범위를 넘어갈 수 있으므로 따로 처리
		size--; //사이즈 감소
	}
	
	//모든 책 정보가 담긴 배열 리턴
	public Book[] getList() {
		System.out.println("*********************도서 전체 목록*********************");
		return books;
	}
	
	//일반도서 정보만 모은 배열 리턴
	public Book[] getBooks() {
		System.out.println("*********************일반 도서 목록*********************");
		basicBookList = new Book[size];
		int basicBookCnt = 0;
		
		for(int row = 0; row < size; row++) {
			if(books[row] instanceof Magazine)
				continue; //매거진 제외
			basicBookList[basicBookCnt] = books[row];
			basicBookCnt++;
		}
		return basicBookList;
	}
	
	//매거진 정보만 모은 배열 리턴
	public Magazine[] getMagazine() {
		System.out.println("*********************잡지 목록*********************");
		magazineList = new Magazine[size];
		int magazineCnt = 0;
		
		for(int row = 0; row < size; row++) {
			if(books[row] instanceof Magazine) { //매거진만 포함
				magazineList[magazineCnt] = (Magazine) books[row];
				magazineCnt++;
			}
		}
		return magazineList;
	}
	
	//시리얼 넘버를 파라미터로 받고 같은 시리얼 넘버를 가진 책 리턴
	public Book searchByIsbn(String isbn) {
		System.out.println("*********************도서조회:" + isbn + "*********************");
		
		for(int row = 0; row < size; row++)
			if(books[row].isbn.equals(isbn))
				return books[row]; //같은 isbn 도서 정보 찾으면 반환
		return null; //못 찾으면 null
	}
	
	//단어를 파라미터로 받고 단어가 포함된 제목을 가진 책 정보 배열에 넣어 반환 
	public Book[] searchByTitle(String title) {
		System.out.println("*********************도서 제목 포함검색:"+ title + "*********************");
		Book[] findByTitle = new Book[size];
		int findIndex = 0;
		
		for(int row = 0; row < size; row++) {
			//books의 각 객체 title에 파라미터로 들어온 title 값이 포함되어 있으면 배열에 추가
			if(books[row].title.contains(title)) {
				findByTitle[findIndex] = books[row];
				findIndex++;
			}
		}
		return findByTitle;
	}
	
	//도서리스트의 가격의 총합을 반환
	public int getTotalPrice() {
		int totalPrice = 0;
		
		for(int row = 0; row < size; row++) {
			totalPrice += books[row].price;
		}
		return totalPrice;
	}
	
	//도서가격의 평균을 반환
	public double getPriceAvg() {
		//가격의 총합을 구하는 부분은 getTotalPrice를 사용해서 코드 중복 제거
		return getTotalPrice() / size;
	}
	
	
}
