import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 정해진 카드 순서 입력 받기
 * 3. 정해진 카드를 제외한 나머지 카드 배열 생성하기
 * 4. 나머지 카드들의 경우의 수 생성
 * 	4-1. 각 경우에 따라 정해진 카드와 승패 판단
 * 5. 최종 결과 출력하기
 * 
 */


public class Solution {
	static final int TOTAL_CARD_SIZE = 18;
	static final int PLAYER_CARD_SIZE = 9;
	
	static int gyuWin = 0;
	static int gyuLose = 0;
	
	static int[] selectElements = new int[9];
	static boolean[] isVisitElements = new boolean[9];
	
	static int[] inCards = new int[9]; //인영이 카드
	static int[] gyuCards = new int[9]; //규영이 카드
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	//입력 받는 메소드
	static void inputElement() throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		
		//2. 정해진 카드 순서 입력 받기
		int[] findInCards = new int[19];
		for(int gyuCardIndex = 0; gyuCardIndex < PLAYER_CARD_SIZE; gyuCardIndex++) {
			gyuCards[gyuCardIndex] = Integer.parseInt(st.nextToken());
			findInCards[gyuCards[gyuCardIndex]]++; //선택된 카드 표시
		}

		//3. 정해진 카드를 제외하고 나머지 카드 배열 생성하기
		int inCardIndex = 0;
		for(int index = 1; index <= TOTAL_CARD_SIZE; index++) {
			if(findInCards[index] == 0) {
				inCards[inCardIndex] = index;
				inCardIndex++;
			}
		}
	}
	
	//순열 재귀 메소드
	static void permutaion(int selectedIndex) {
		//기저 조건
		if(selectedIndex == PLAYER_CARD_SIZE) {
			calWinLose();
			return;
		}
		
		for(int inCardSelectIndex = 0; inCardSelectIndex < PLAYER_CARD_SIZE; inCardSelectIndex++)
		{
			//전처리
			if(isVisitElements[inCardSelectIndex] == true) {
				continue;
			}
			selectElements[selectedIndex] = inCards[inCardSelectIndex];
			isVisitElements[inCardSelectIndex] = true;
			
			//재귀
			permutaion(selectedIndex + 1);
			
			//후처리
			isVisitElements[inCardSelectIndex] = false;
		}
	}
	
	//승패 판단 메소드
	static void calWinLose() {
		int scoreGyu = 0;
		int scoreIn = 0;
		
		for(int compareIndex = 0; compareIndex < PLAYER_CARD_SIZE; compareIndex++) {
			if(gyuCards[compareIndex] > selectElements[compareIndex]) {
				scoreGyu += gyuCards[compareIndex] + selectElements[compareIndex];
			}
			else {
				scoreIn += gyuCards[compareIndex] + selectElements[compareIndex];
			}
		}
		
		if(scoreGyu > scoreIn) {
			gyuWin++;
		}
		else {
			gyuLose++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		//1. 테스트 케이스 입력 받기
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int totalTestCase = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= totalTestCase; test_case++) {
			inputElement();
			permutaion(0);
			sb.append("#").append(test_case).append(" ").append(gyuWin).append(" ").append(gyuLose).append("\n");
			gyuWin = 0;
			gyuLose = 0;
		}
		System.out.println(sb);
	}

}
