import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 숫자 10개 (num) 입력 받기
 * 3. 최대 수, 최소 수 갱신
 * 4. 입력 받은 숫자의 합 구하기
 * 5. 합에서 최대 수, 최소 수를 빼고 평균 구하기
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
			
			int sum = 0;
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int count = 0; count < 10; count++) {
				//2. 숫자 10개 (num) 입력 받기
				int num = Integer.parseInt(st.nextToken());
				
				//3. 최대 수, 최소 수 갱신
				min = Math.min(min, num);
				max = Math.max(max, num);
				
				//4. 입력 받은 숫자의 합 구하기
				sum += num;
			}
			//5. 합에서 최대 수, 최소 수를 빼고 평균 구하기
			int result = (int)Math.round((sum - min - max) / 8.0);
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
}
