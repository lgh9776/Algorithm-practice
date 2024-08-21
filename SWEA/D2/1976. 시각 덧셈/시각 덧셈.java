import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 2개의 시, 분 입력 받기
 * 3. 첫번째 분, 두번째 분 더하기
 * 	3-1. 60분 이상이면 시에 +1 해주고
 * 	3-2. %60 으로 분 구하기
 * 4. 첫번째 시, 두번째 시 더하기
 * 	4-1. %12에 +1해서 시간 구하기
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			//2. 2개의 시, 분 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			int firstHour = Integer.parseInt(st.nextToken());
			int firstMin = Integer.parseInt(st.nextToken());
			int secondHour = Integer.parseInt(st.nextToken());
			int secondMin = Integer.parseInt(st.nextToken());
			
			//3. 첫번째 분, 두번째 분 더하기
			int min = firstMin + secondMin;
			//4. 첫번째 시, 두번째 시 더하기
			int hour = firstHour + secondHour;
			
			if(min >= 60) {
				//3-1. 60분 이상이면 시에 +1 해주고
				hour += 1;
				//3-2. %60 으로 분 구하기
				min %= 60;
			}
			
			//4-1. %12에 +1해서 시간 구하기
			hour = hour % 12 == 0 ? 12 : hour % 12;
			
			sb.append(hour).append(" ").append(min).append("\n");
		}
		System.out.print(sb);
	}
}
