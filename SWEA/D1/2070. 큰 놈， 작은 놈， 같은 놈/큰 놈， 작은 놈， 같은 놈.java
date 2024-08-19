import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 2개의 수 입력 받기
 * 3. 2개의 수 크기 비교하여 부등호 출력
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
			
			//2. 2개의 수 입력 받기
			st = new StringTokenizer(br.readLine());
			int firstNum = Integer.parseInt(st.nextToken());
			int secondNum = Integer.parseInt(st.nextToken());
			
			//3. 2개의 수 크기 비교하여 부등호 출력
			if(firstNum > secondNum) {
				sb.append(">").append("\n");
			}
			else if(firstNum == secondNum) {
				sb.append("=").append("\n");
			}
			else if(firstNum < secondNum) {
				sb.append("<").append("\n");
			}
		}
		System.out.print(sb);
	}
}
