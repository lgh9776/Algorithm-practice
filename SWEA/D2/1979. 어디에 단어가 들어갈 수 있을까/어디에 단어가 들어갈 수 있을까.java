import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 퍼즐 사이즈, 단어 길이 입력 받기 (size, wordLen)
 * 3. puzzle 정로 입력받기
 * 4. 각 행마다 연속되는 1이 몇개인지 찾기
 * 5. 한 열마다 연속되는 1이 몇개인지 찾기
 * 6. wordLen의 사이즈와 같으면 count 증가
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			//2. 퍼즐 사이즈, 단어 길이 입력 받기 (size, wordLen)
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			int wordLen = Integer.parseInt(st.nextToken());
			
			//3. puzzle 정로 입력받기
			int[][] puzzle = new int[size][size];
			for(int row = 0; row < size; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col = 0; col < size; col++) {
					puzzle[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = 0;
			for(int row = 0; row < size; row++) {
				for(int col = 0; col < size; col++) {
					int oneCount = 0;
					//4. 한 행마다 연속되는 1이 몇개인지 찾기
					while(col < size && puzzle[row][col] == 1) {
						oneCount++;
						col++;
					}
					
					//6. wordLen의 사이즈와 같으면 result 증가
					if(oneCount == wordLen)
						result++;
				}
			}
			
			for(int col = 0; col < size; col++) {
				for(int row = 0; row < size; row++) {
					int oneCount = 0;
					//5. 한 열마다 연속되는 1이 몇개인지 찾기
					while(row < size && puzzle[row][col] == 1) {
						oneCount++;
						row++;
					}
					
					//6. wordLen의 사이즈와 같으면 result 증가
					if(oneCount == wordLen)
						result++;
				}
			}
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
}
