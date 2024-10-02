import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 수열의 길이 입력 받기
 * 3. 수열 입력 받기
 * 4. 최장 증가 부분 수열 구하기 (dp 배열 채우기)
 * 	4-1. dp의 첫 인덱스에 1로 초기화
 * 	4-2. 수열의 길이만큼 반복
 * 	4-3. 0번째부터 현재 인덱스(수열)까지 반복
 * 	4-4. 현재 수열의 값이 비교 대상(현재 수열의 값보다 앞에 있는 것들)보다 크면
 * 		4-4-1. 현재 dp값과 비교 대상의 dp+1 값 중 큰 것을 현재 dp 값에 저장
 * 	4-5. 작으면 1로 초기화
 * 5. dp 값 중 가장 큰 것이 최장 길이
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int[] dp; //각 수열의 원소들 순서 저장
	static long[] nums; //수열 저장
	static int numsLen;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();
			
			//4. 최장 증가 부분 수열 구하기 (dp 배열 채우기)
			lis();
			
			int result = -1;
			for (int index = 0; index < numsLen; index++) {
				//5. dp 값 중 가장 큰 것이 최장 길이
				if(result < dp[index]) {
					result = dp[index];
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	static void inputData() throws IOException {
		//2. 수열의 길이 입력 받기
		st = new StringTokenizer(br.readLine());
		numsLen = Integer.parseInt(st.nextToken());
		
		nums = new long[numsLen];
		dp = new int[numsLen];
		
		//3. 수열 입력 받기
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < numsLen; index++) {
			nums[index] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void lis() {
		//4-1. dp의 첫 인덱스에 1로 초기화
		dp[0] = 1;
		
		//4-2. 수열의 길이만큼 반복
		for (int dpIndex = 1; dpIndex < numsLen; dpIndex++) {
			dp[dpIndex] = 1;
			
			//4-3. 0번째부터 현재 인덱스(수열)까지 반복
			for (int elementIndex = 0; elementIndex < dpIndex; elementIndex++) {
				//4-4. 현재 수열의 값이 비교 대상(현재 수열의 값보다 앞에 있는 것들)보다 크면
 				if(nums[dpIndex] > nums[elementIndex]) {
					//4-4-1. 현재 dp값과 비교 대상의 dp+1 값 중 큰 것을 현재 dp 값에 저장
					dp[dpIndex] = Math.max(dp[dpIndex], dp[elementIndex]+1);
				}
			}
		}
	}
}