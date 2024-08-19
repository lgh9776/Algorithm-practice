import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 10개의 수 입력 받아서 변수에 더하기
 * 3. 홀수만 더하기
 * 4. 출력하기
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			int sum = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int index = 0; index < 10; index++) {
				//2. 10개의 수 입력 받아서 
				int num = Integer.parseInt(st.nextToken());
				//3. 홀수만 더하기
				if(num % 2 != 0) {
					sum += num;
				}
			}
			//4. 출력하기
			sb.append(sum).append("\n");
			sum = 0;
		}
		System.out.print(sb);
	}
}