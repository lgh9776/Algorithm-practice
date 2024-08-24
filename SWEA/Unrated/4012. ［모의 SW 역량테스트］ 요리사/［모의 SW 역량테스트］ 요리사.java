import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 식재료 수 입력 받기
 * 3. 각 재료의 시너지 입력 받기
 * 4. (기저조건) a손님에 대한 foodCnt/2개를 뽑으면
 * 	4-1. 각 손님의 식재료에 대한 시너지 차이 구하고 갱신
 * 		4-1-1. a손님 식재료 : 선택된 것들
 * 		4-1-2. b손님 식재료 : 선택 안된 것들
 * 		4-1-3. 각 손님의 재료에 대한 시너지 점수 구하기
 * 		4-1-4. 시너지 차이가 가장 적은 것 갱신
 * 5. 남은 요소가 뽑아야할 것보다 적으면 return
 * 6. 이전에 선택된 다음 인덱스부터 선택되지 않은 것 탐색 
 * 	6-1. 선택되지 않은게 있으면
 * 	6-2. 해당 요소 선택 후 다음 것 선택
 * 	6-3. 원상복구
 * 7.시너지 차이가 가장 적은 것 출력
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	//기본 정보 입력 받기
	static int foodCnt;
	static int[][] synergy;
	
	//재귀에 사용할 배열
	static boolean[] isSelect;
	static int result;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for (int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			inputData();
			combination(0, 0);
			
			// 7.시너지 차이가 가장 적은 것 출력
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. 식재료 수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		foodCnt = Integer.parseInt(st.nextToken());
		
		isSelect = new boolean[foodCnt];
		result = Integer.MAX_VALUE;
		
		//3. 각 재료의 시너지 입력 받기
		synergy = new int[foodCnt][foodCnt];
		for (int row = 0; row < foodCnt; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < foodCnt; col++) {
				synergy[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void combination(int selectIndex, int elementIndex) {
		//4. (기저조건) a손님에 대한 foodCnt/2개를 뽑으면
		if(selectIndex == foodCnt / 2) {
			//4-1. 각 손님의 식재료에 대한 시너지 차이 구하고 갱신
			calSynergy();
			return;
		}
		
		//5. 남은 요소가 뽑아야할 것보다 적으면 return
		if(foodCnt/2 - selectIndex - 1 > foodCnt - elementIndex - 1) {
			return;
		}
		
		//6. 이전에 선택된 다음 인덱스부터 선택되지 않은 것 탐색 
		for(int index = elementIndex; index < foodCnt; index++) {
			//6-1. 선택되지 않은게 있으면
			if(isSelect[index] == false) {
				//6-2. 해당 요소 선택 후 다음 것 선택
				isSelect[index] = true;
				combination(selectIndex + 1, index + 1);
				//6-3. 원상복구
				isSelect[index] = false;
			}
		}
	}
	
	static void calSynergy() {
		int[] foodA = new int[foodCnt/2];
		int[] foodB = new int[foodCnt/2];
		
		int indexA = 0, indexB = 0;
		for (int index = 0; index < foodCnt ; index++) {
			//4-1-1. a손님 식재료 : 선택된 것들
			if(isSelect[index] == true)
				foodA[indexA++] = index;
				
			//4-1-2. b손님 식재료 : 선택 안된 것들
			else if(isSelect[index] == false)
				foodB[indexB++] = index;
		}
		
		int synergyA = calSum(foodA);
		int synergyB = calSum(foodB);
		
		//4-1-4. 시너지 차이가 가장 적은 것 갱신
		result = Math.min(result, Math.abs(synergyA - synergyB));
	}
	
	static int calSum(int[] foods) {
		int sum = 0;
		//4-1-3. 각 손님의 재료에 대한 시너지 점수 구하기
		for (int one = 0; one < foods.length - 1; one++) {
			for (int two = one + 1; two < foods.length; two++) {
				sum += synergy[foods[one]][foods[two]];
				sum += synergy[foods[two]][foods[one]];
			}
		}
		return sum;
	}
}