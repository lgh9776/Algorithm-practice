import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 재료 수(foodCnt), 제한 칼로리(maxCal) 입력 받기
 * 3. 각 재료 정보(foodInfo) 입력 받기
 * 4. next permutation 활용하여 조합 생성
 * 5. 선택된 인덱스로 foodInfo에 접근해 맛 점수, 칼로리 계산
 * 6. 칼로리 조건 만족 시 맛 점수 갱신
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int foodCnt;
	static int maxCal;
	static int[][] foodInfo;
	static int[] isSelected;
	static int bestScore = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
        
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			inputData();
			
			//4. next permutation 활용
			for(int selectCnt = 0; selectCnt <= foodCnt; selectCnt++) {
				//n개 중 selectCnt개를 뽑는 조합 (계속 증가하며 모든 조합을 봐야함)
				for(int index = 0; index < selectCnt; index++) {
					isSelected[index] = 1;
				}
				Arrays.sort(isSelected); //오름차순 정렬
//				System.out.println(Arrays.toString(isSelected));
				
				while(true) {
					int currentScore = 0; //현재 조합의 맛점수
					int currentCal = 0; //현재 조합의 칼로리
					for(int index = 0; index < foodCnt; index++) {
						if(isSelected[index] == 0) { //선택 안된 요소
							continue;
						}
						//5. 선택된 인덱스로 foodInfo에 접근해 맛 점수, 칼로리 계산
						currentScore += foodInfo[index][0];
						currentCal += foodInfo[index][1];
					}
					//6. 칼로리 조건 만족 시 맛 점수 갱신
					if(currentCal <= maxCal) {
						bestScore = Math.max(bestScore, currentScore);
					}
					
					//고려할 순열(조합)이 없으면 다음 뽑을 개수로 넘어감
					if(!nextPermutation()) {
						break;
					}
				}			
			}
			sb.append(bestScore).append("\n");
		}
		System.out.print(sb);
	}

	static void inputData() throws IOException {
		//2. 재료 수(foodCnt), 제한 칼로리(maxCal) 입력 받기
		st = new StringTokenizer(br.readLine());
		foodCnt = Integer.parseInt(st.nextToken());
		maxCal = Integer.parseInt(st.nextToken());
		bestScore = Integer.MIN_VALUE;
		
		//3. 각 재료 정보(foodInfo) 입력 받기
		foodInfo = new int[foodCnt][2]; //0:맛 점수, 1: 칼로리
		isSelected = new int[foodCnt];
		for(int index = 0; index < foodCnt; index++) {
			st = new StringTokenizer(br.readLine());
			foodInfo[index][0] = Integer.parseInt(st.nextToken()); //맛 점수
			foodInfo[index][1] = Integer.parseInt(st.nextToken()); //칼로리
		}
	}
	
	static boolean nextPermutation() {
		int i = foodCnt - 1;
		int j = i;
		int k = i;
		
		//뒤에서부터 앞칸보다 큰게 있는지 확인
		while(i > 0 && isSelected[i-1] >= isSelected[i]) {
			i--;
		}
		
		//뒤에서부터 앞과 뒤를 계속 비교해서 끝까지 갈 경우 -> 내림차순 정렬
		//가장 큰 순열까지 옴 -> 다음 순열 없음
		if(i == 0) {
			return false; //다음 순열이 없음을 나타냄
		}
		
		//자리를 바꾼 곳까지 해서 가장 큰 수를 만들었을 때는 그 앞칸 비교
		//뒤에서 가장 먼저 뒷칸보다 작은 앞칸과 맨 뒤에서 부터의 칸을 비교
		//뒤에 있는 칸이 더 크면 탈출
		while(isSelected[i-1] >= isSelected[j]) {
			j--;
		}
		
		//뒤에 있는 것과 비교하는 범위의 첫번째 칸을 스왑
		swap(i-1, j);
		
		//비교하는 범위의 앞(i)과 뒤(k)를 바꿔주기
		//앞쪽에 큰 수가 있기 때문에 오름차순으로 바꿔주기
		while(i < k) {
			swap(i++, k--);
		}
		return true; //조합할 수 있는 순열이 있음
		
		/* 
		 * 여기서 만들어진 순열은 isSeleted를 변경해주는 것
		 * isSelected의 1이 있는 곳에 따라 food 요소 선택됨
		 * isSelected의 순서가 food의 조합이 되는 것
		*/
	}
	
	static void swap(int changeIndex, int targetIndex) {
		int sawpData = isSelected[changeIndex];
		isSelected[changeIndex] = isSelected[targetIndex];
		isSelected[targetIndex] = sawpData;
	}
	
	
}
