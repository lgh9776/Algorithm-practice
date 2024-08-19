import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 날짜 입력 받기 
 * 3. 년/월/일 따로 변수에 저장
 * 4. 월/일 정보로 유효성 검사
 * 5. 가능 여부에 따라 출력
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
			
			//2. 날짜 입력 받기 
			st = new StringTokenizer(br.readLine());
			String[] dateData = st.nextToken().split("");
			
			//3. 년/월/일 따로 변수에 저장
			String year = dateData[0] + dateData[1] + dateData[2] + dateData[3];
			int month = Integer.parseInt(dateData[4] + dateData[5]);
			int day = Integer.parseInt(dateData[6] + dateData[7]);
			
			//4. 월/일 정보로 유효성 검사
			boolean isValid = false;
			if(month == 2) {
				if(day >= 1 && day <= 28) {
					isValid = true;
				}
			}
			else if((month >= 1 && month <= 7 && month % 2 == 1) || (month >= 8 && month <= 12 && month % 2 == 0)) {
				if(day >= 1 && day <= 31) {
					isValid = true;
				}
			}
			else if((month <= 6 && month >= 1 && month % 2 == 0) || (month >= 9 && month <= 12 && month % 2 == 1)) {
				if(day >= 1 && day <= 30) {
					isValid = true;
				}
			}
			
			//5. 가능 여부에 따라 출력
			if(isValid) {
				sb.append(year).append("/");
				if(dateData[4].equals("0")) {
					sb.append(0).append(month).append("/");
				}
				else {
					sb.append(month).append("/");
				}
				
				if(dateData[6].equals("0")) {
					sb.append(0).append(day).append("\n");
				}
				else {
					sb.append(day).append("\n");
				}
			}
			else {
				sb.append(-1).append("\n");
			}
		}
		System.out.print(sb);
	}
}
