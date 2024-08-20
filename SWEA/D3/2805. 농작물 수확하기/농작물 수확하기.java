import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 농장 크기 입력 받기 (farmSize)
 * 3. 농장 가치 입력 받기 (farm)
 * 4. 마름모 영역의 가치 구하기
 * 	4-1. 농장의 사이즈만큼 반복
 * 	4-2. row번째 줄 start, end 부분만큼의 가치 계산
 * 	4-3. row가 농장의 사이즈/2보다 작은 경우, 마름모 증가
 * 	4-4. row가 농장의 사이즈/2보다 크거나 같은 경우, 마름모 감소
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int farmSize;
	static int[][] farm;
	static int totalValue;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			inputData();
			//4. 마름모 영역의 가치 구하기
			calValue();
			
			sb.append(totalValue).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. 농장 크기 입력 받기 (farmSize)
		st = new StringTokenizer(br.readLine().trim());
		farmSize = Integer.parseInt(st.nextToken());
		farm = new int[farmSize][farmSize];
		totalValue = 0;

		//3. 농장 가치 입력 받기 (farm)
		for(int row = 0; row < farmSize; row++) {
			String[] line = br.readLine().trim().split("");
			for(int col = 0; col < farmSize; col++) {
				farm[row][col] = Integer.parseInt(line[col]);
			}
		}
	}
	
	static void calValue() {
		//farmSize가 홀수라서 같은 값을 가짐 (중앙에서 시작)
		int start = farmSize / 2;
		int end = farmSize / 2;
		
		//4-1. 농장의 사이즈만큼 반복
		for(int row = 0; row < farmSize; row++) {
			//4-2. row번째 줄 start, end 부분만큼의 가치 계산
			for(int col = start; col <= end; col++) {
				if(col < farmSize) {
					totalValue += farm[row][col];
				}
			}
			//4-3. row가 농장의 사이즈/2보다 작은 경우, 마름모 증가
			if(row < (farmSize / 2)) {
				start--;
				end++;
			}
			//4-4. row가 농장의 사이즈/2보다 크거나 같은 경우, 마름모 감소
			else if(row >= (farmSize / 2)) {
				start++;
				end--;
			}
		}
	}
	
}
