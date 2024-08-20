import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 숫자 1개 (num) 입력 받기
 * 3. 1~num까지의 숫자 중 
 * 	3-1. 홀수는 더하기
 * 	3-2. 짝수는 빼기 
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
			
			//2. 숫자 1개 (num) 입력 받기
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			//3. 1~num까지의 숫자 중
			int sum = 0;
			for(int count = 1; count <= num; count++) {
				//3-1. 홀수는 더하기
				if(count % 2 == 1) {
					sum += count;
				}
				//3-2. 짝수는 빼기 
				else if(count % 2 == 0) {
					sum -= count;
				}
			}
			sb.append(sum).append("\n");
		}
		System.out.print(sb);
	}
}