import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 소인수분해 할 숫자 입력 받기
 * 3. 답을 구하기 위해 나머지가 있을때까지 나눠주기
 * 	3-1. 나머지가 있으면 해당 count를 저장
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		int[] devideNum = {2, 3, 5, 7, 11};
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			//2. 소인수분해 할 숫자 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			int num = Integer.parseInt(st.nextToken());
			
			//3. 답을 구하기 위해 나머지가 있을때까지 나눠주기
			for (int index = 0; index < devideNum.length; index++) {
				int count = 0; //지수를 세어줄 변수
				while(num % devideNum[index] == 0) {
					count++;
					num /= devideNum[index];
				}
				//3-1. 나머지가 있으면 해당 count를 저장
				sb.append(count).append(" ");
				count = 0;
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
