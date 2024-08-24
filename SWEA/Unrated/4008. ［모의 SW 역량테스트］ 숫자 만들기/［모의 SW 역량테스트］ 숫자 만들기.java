import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 연산할 숫자 수 입력 받기
 * 	2-1. 재귀에 사용할 배열 초기화
 * 3. 연산자 개수 입력 받기
 * 4. 연산할 숫자 입력 받기
 * 5. 연산자의 중복 순열 구하기
 * 6. 1개의 순열을 완성하면
 * 	6-1. 각 숫자와 연산자를 이용해 값 구하기
 * 		6-1-1. 현재의 연산자 순서대로 값 계산
 * 		6-1-2. 최댓값, 최솟값 갱신
 * 7. 연산자 개수만큼 반복
 * 	7-1. 선택하지 않은 연산자가 있으면
 * 	7-2. 해당 연산자 선택
 * 	7-3. 다음 요소를 선택하기 위해 재귀 호출
 * 	7-4. 원상복구 
 * 8. 최댓값, 최솟값의 차이 출력
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	//연산할 요소들
	static int numCount;
	static int[] nums;
	static int[] operators; //+(0), -(1), *(2), /(3)
	
	//재귀에 사용할 배열
	static int[] selOperator;
	static int min;
	static int max;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for (int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			inputData();
			
			//5. 연산자의 중복 순열 구하기
			permutation(0);
			
			// 8. 최댓값, 최솟값의 차이 출력
			sb.append(max - min).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. 연산할 숫자 수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		numCount = Integer.parseInt(st.nextToken());
		nums = new int[numCount];
		operators = new int[4]; //연산자 4개의 개수를 입력받는 배열이라 4칸
		
		//2-1. 재귀에 사용할 배열 초기화
		selOperator = new int[numCount-1]; //총 연산자를 순서대로 저장해서 numCount-1칸
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		//3. 연산자 개수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		for (int operCnt = 0; operCnt < 4; operCnt++) {
			operators[operCnt] = Integer.parseInt(st.nextToken());
		}
		
		//4. 연산할 숫자 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		for (int index = 0; index < numCount; index++) {
			nums[index] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void permutation(int selectIndex) {
		//6. 1개의 순열을 완성하면
		if(selectIndex == numCount - 1) {
			//6-1. 각 숫자와 연산자를 이용해 값 구하기
			calResult();
			return;
		}
		
		//7. 연산자 개수만큼 반복
		for (int index = 0; index < operators.length; index++) {
			//7-1. 선택하지 않은 연산자가 있으면
			if(operators[index] != 0) {
				//7-2. 해당 연산자 선택
				selOperator[selectIndex] = index;
				operators[index]--;
				
				//7-3. 다음 요소를 선택하기 위해 재귀 호출
				permutation(selectIndex + 1);
				
				//7-4. 원상복구 
				operators[index]++;
			}
		}
	}
	
	static void calResult() {
		int currentResult = nums[0];
		
		//6-1-1. 현재의 연산자 순서대로 값 계산
		for (int operatorIndex = 0; operatorIndex < selOperator.length; operatorIndex++) {
			if(selOperator[operatorIndex] == 0)
				currentResult += nums[operatorIndex+1];
			
			else if(selOperator[operatorIndex] == 1)
				currentResult -= nums[operatorIndex+1];
			
			else if(selOperator[operatorIndex] == 2)
				currentResult *= nums[operatorIndex+1];
			
			else if(selOperator[operatorIndex] == 3)
				currentResult /= nums[operatorIndex+1];
		}
				
		//6-1-2. 최댓값, 최솟값 갱신
		min = Math.min(min, currentResult);
		max = Math.max(max, currentResult);
	}
}
