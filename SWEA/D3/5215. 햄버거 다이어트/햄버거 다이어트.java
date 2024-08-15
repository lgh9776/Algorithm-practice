import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 1. 테스트 케이스 입력 받기
 * 2. 데이터 입력 받기
 * 	2-1. 재료 수, 제한 칼로리 입력 받기
 * 	2-2. 각 재료에 대한 맛 점수, 칼로리 입력 받기
 * 3. 모든 부분집합을 구할 재귀함수 호출
 * 4. (기재조건)
 *  4-1. 제한 칼로리를 넘긴 경우
 *  4-2. 모든 요소를 고려한 경우
 * 5. 해당 요소 사용 표시
 * 	5-1. 현재 점수, 칼로리 계산, best 점수 갱신
 * 	5-2. 다음 요소를 인자로 재귀 호출
 * 6. 해당 요소 사용 표시X
 * 	6-1. 현재 점수, 칼로리 계산 (재료가 추가되지 않아서 따로 점수 갱신x)
 * 	6-2. 다음 요소를 인자로 재귀 호출
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int[][] foodInfo;
	static boolean[] usedFood;
	
	static int foodCount;
	static int maxCal;
	
	static int bestScore = -1;
	static int nowScore = 0;
	static int nowCal = 0;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			//2. 데이터 입력 받기
			inputData();
			
			//3. 모든 부분집합을 구할 재귀함수 호출
			powerSet(0);
			
			sb.append(bestScore).append("\n");
			bestScore = 0;
		}
		System.out.print(sb);
	}
	
	static void inputData() throws Exception {
		//2-1. 재료 수, 제한 칼로리 입력 받기
		st = new StringTokenizer(br.readLine());
		foodCount = Integer.parseInt(st.nextToken());
		maxCal = Integer.parseInt(st.nextToken());
		
		foodInfo = new int[foodCount][2];
		usedFood = new boolean[foodCount];
		
		//2-2. 각 재료에 대한 맛 점수, 칼로리 입력 받기
		for(int index = 0; index < foodCount; index++) {
			st = new StringTokenizer(br.readLine());
			foodInfo[index][0] = Integer.parseInt(st.nextToken()); //맛 점수
			foodInfo[index][1] = Integer.parseInt(st.nextToken()); //칼로리
		}
	}
	
	static void powerSet(int selectIndex) {
		//4. (기재조건)
		//4-1. 제한 칼로리를 넘긴 경우
		if(nowCal > maxCal) {
			return;
		}
		
		//4-2. 모든 요소를 고려한 경우
		if(selectIndex == foodCount) {
			return;
		}
		
		//5. 해당 요소 사용 표시
		usedFood[selectIndex] = true;
		//5-1. 현재 점수, 칼로리 계산, best 점수 갱신
		nowScore += foodInfo[selectIndex][0];
		nowCal += foodInfo[selectIndex][1];
		
		if(maxCal >= nowCal) {
			bestScore = Math.max(nowScore, bestScore);
		}
		
		//5-3. 다음 요소를 인자로 재귀 호출
		powerSet(selectIndex + 1);

		//6. 해당 요소 사용 표시X (+ 원상복구)
		usedFood[selectIndex] = false;
		//6-1. 현재 점수, 칼로리 계산
		nowScore -= foodInfo[selectIndex][0];
		nowCal -= foodInfo[selectIndex][1];
		//6-2. 다음 요소를 인자로 재귀 호출
		powerSet(selectIndex + 1);
	}

}
