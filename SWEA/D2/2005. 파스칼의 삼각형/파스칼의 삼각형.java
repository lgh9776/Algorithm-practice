import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 삼각형 크기 입력 받기
 * 3. 삼각형 크기만큼의 2차원 배열 생성
 * 4. size만큼 반복하며 파스칼 계산
 * 	4-1. 삼각형 초기값 넣기 (무조건 0, 0은 1)
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		int[][] pascal;
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append("\n");
			
			//2. 삼각형 크기 입력 받기
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			
			//3. 삼각형 크기만큼의 2차원 배열 생성
			pascal = new int[size][size];

			//4. size만큼 반복하며 파스칼 계산
			//4-1. 삼각형 초기값 넣기 (무조건 0, 0은 1)
			pascal[0][0] = 1;
			for(int row = 1; row < size; row++) {
				for(int col = 0; col < size; col++) {
					if(col - 1 >= 0) //배열 범위 체크
						pascal[row][col] += pascal[row-1][col-1]; //왼쪽 위
					pascal[row][col] += pascal[row-1][col]; //오른쪽 위
				}
			}
			
			//출력
			for(int row = 0; row < size; row++) {
				for(int col = 0; col < size; col++) {
					if(pascal[row][col] > 0) {
						sb.append(pascal[row][col]).append(" ");
					}
				}
				sb.append("\n");
			}
			
		}
		System.out.print(sb);
	}
}
