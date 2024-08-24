import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 최종 수 
 * -> 4를 만들어서 2
 * -> 3을 만들어서 +1 후 2
 * 
 * 1. 테스트 케이스 입력 받기
 * 2. 정수 num 입력 받기
 * 3. num을 제곱근 시켰을 때 정수이면
 * 	3-1. 제곱근 시키기
 * 	3-2. count 증가
 * 4. 정수가 아니면 제곱근 num이 정수가 될 때까지 +1 해주기
 * 	4-1. 제곱근 num에서 +1한 것의 제곱 구하기
 * 	4-2. 제곱한 수에서 현재 num을 빼서 count 증가
 * 	4-3. num에는 제곱한 수 저장
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			//2. 정수 num 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			long num = Long.parseLong(st.nextToken());
			
			int count = 0;
			while(num != 2) {
				//3. num을 제곱근 시켰을 때 정수이면
				long sqrtNum = (long) Math.sqrt(num);
				if(Math.sqrt(num) == sqrtNum) {
					//3-1. 제곱근 시키기
					num = sqrtNum;
					//3-2. count 증가
					count++;
				}
				//4. 정수가 아니면 제곱근 num이 정수가 될 때까지 +1 해주기
				else {
					//4-1. 제곱근 num에서 +1한 것의 제곱 구하기
					long doubleNum = (long) Math.pow(sqrtNum + 1, 2);
					
					//4-2. 제곱한 수에서 현재 num을 빼서 count 증가
					count += (doubleNum - num);
					
					//4-3. num에는 제곱한 수 저장
					num = doubleNum;
				}
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb);
	}

}
