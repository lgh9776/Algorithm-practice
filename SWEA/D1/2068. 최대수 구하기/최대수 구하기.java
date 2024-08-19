import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 10개의 수 입력 받으면서
 * 3. 현재 bestNum과 입력한 수 비교하여 갱신
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
			
			int bestNum = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int count = 0; count < 10; count++) {
				//2. 10개의 수 입력 받으면서
				int num = Integer.parseInt(st.nextToken());
				
				//3. 현재 bestNum과 입력한 수 비교하여 갱신
				bestNum = Math.max(bestNum, num);
			}
			sb.append(bestNum).append("\n");
		}
		System.out.print(sb);
	}
}
