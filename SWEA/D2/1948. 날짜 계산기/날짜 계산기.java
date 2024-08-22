import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 날짜 2개 입력 받기
 * 3. 월 계산, 해당 월의 일수만큼 더해주기
 * 4. 일 계산
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		int[] monthData = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			//2. 날짜 2개 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			int month1 = Integer.parseInt(st.nextToken());
			int day1 = Integer.parseInt(st.nextToken());
			int month2 = Integer.parseInt(st.nextToken());
			int day2 = Integer.parseInt(st.nextToken());
			
			//3. 월 계산, 해당 월의 일수만큼 더해주기
			int totalDay = 0;
			for(int index = month1; index < month2; index++) {
				totalDay += monthData[index];
			}
			
			//4. 일 계산
			totalDay = totalDay - day1 + 1; //해당 일이 포함 안돼서 +1
			totalDay += day2;
			
			sb.append(totalDay).append("\n");
		}
		System.out.print(sb);
	}
}
