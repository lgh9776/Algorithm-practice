import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 숫자 2개 입력 받기
 * 3. 첫번째 숫자를 두번째 숫자로 나눈 몫, 나머지 출력
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
			
			//2. 숫자 2개 입력 받기
			st = new StringTokenizer(br.readLine());
			int firstNum = Integer.parseInt(st.nextToken());
			int secondNum = Integer.parseInt(st.nextToken());
			
			//3. 첫번째 숫자를 두번째 숫자로 나눈 몫, 나머지 출력
			sb.append(firstNum / secondNum).append(" ").append(firstNum % secondNum).append("\n");
		}
		System.out.print(sb);
	}

}