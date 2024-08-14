import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 재료 수, 제한 칼로리 입력 받기
 * 3. 각 재료에 대한 맛 점수, 칼로리 배열에 입력 받기
 * 4. 재귀함수로 재료에 대한 조합 구하기
 * 	4-1. (기재조건) 칼로리가 1000이 넘는 경우, 더 이상 선택할 수 없는 경우
 * 		4-1-1. 현재 맛 점수, 칼로리와 best 맛 점수, 칼로리 비교
 *  4-2. (전처리) 전달 받은 인덱스부터 재료수 만큼 반복
 *  	4-2-1. 선택 안한 재료 선택, 선택했다고 표시
 *  	4-2-2. 현재 점수, 칼로리 계산
 *  4-3. (재귀) 현재 selectIndex + 1, elementIndex + 1을 인자로 넘기기
 *  4-4. (후처리:원상복구) 현재 맛 점수, 칼로리 복구, 방문표시 해제
 *   
 */

public class Solution {	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int foodCount;
	static int maxCal;
	
	static int bestScore;
	static int currentScore;
	static int currentCal;
	
	static int[][] foodInfo; // 0: 맛 점수, 1: 칼로리
	static boolean[] visitFood;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine(), " ");
		int test_case = Integer.parseInt(st.nextToken());
		
		for(int cnt = 1; cnt <= test_case; cnt++) {
			sb.append("#").append(cnt).append(" ");
			
			inputData();
			combination(0, 0);
			
			//5. 재귀 후 해당 테스트 케이스의 결과 추가
			sb.append(bestScore).append("\n");
			bestScore = 0;
		}
		System.out.print(sb);
	}
	
	static void inputData() throws Exception {
		//2. 재료 수, 제한 칼로리 입력 받기
		st = new StringTokenizer(br.readLine(), " ");
		foodCount = Integer.parseInt(st.nextToken());
		maxCal = Integer.parseInt(st.nextToken());
		
		visitFood = new boolean[foodCount];
		
		//3. 각 재료에 대한 맛 점수, 칼로리 배열에 입력 받기
		foodInfo = new int[foodCount][2];
		for(int index = 0; index < foodCount; index++) {
			st = new StringTokenizer(br.readLine(), " ");
			foodInfo[index][0] = Integer.parseInt(st.nextToken());
			foodInfo[index][1] = Integer.parseInt(st.nextToken());
		}
	}
	
	//4. 재귀함수로 재료에 대한 조합 구하기
	static void combination(int selectIndex, int elementIndex) {
		
		//4-1. (기재조건) 칼로리가 max를 넘는 경우, 더 이상 선택할 수 없는 경우
		if(currentCal > maxCal || selectIndex == foodCount) {
			return;
		}
		
		//4-2. (전처리) 전달 받은 인덱스부터 재료수 만큼 반복
		for(int index = elementIndex; index < foodCount; index++) {
			//4-2-1. 선택 안한 재료 선택,
			if(visitFood[index] == true) {
				continue; 
			}
			
			//선택했다고 표시
			visitFood[index] = true;
			
			//4-2-2. 현재 점수, 칼로리 계산
			currentScore += foodInfo[index][0];
			currentCal += foodInfo[index][1];
			
			//4-3. best 맛 점수 갱신
			//현재 칼로리가 제한 칼로리보다 작을 때 갱신
			if (currentCal <= maxCal) {
				bestScore = Math.max(bestScore, currentScore);
			}
			
			//4-4. (재귀) 현재 selectIndex + 1, elementIndex + 1을 인자로 넘기기
			combination(selectIndex + 1, index + 1);
			
			//4-5. (후처리:원상복구) 현재 맛 점수, 칼로리 복구, 방문표시 해제
			currentScore -= foodInfo[index][0];
			currentCal -= foodInfo[index][1];
			visitFood[index] = false;
		}
	}
}