import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 각 이용권 요금 입력 	받기
 * 3. 각 월의 이용계획 입력 받기
 * 4. 12월까지 항상 해당 월까지 최적의 값을 구해주기
 * 	4-1. 3월 전까지 1일, 1개월 이용권 고려
 * 	4-2. 3월부터 3개월 이용권까지 고려
 * 5. 12월달에 저장된 값은 12월을 고려한 값 -> 1년 이용권과 비교
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int oneDayPay;
	static int oneMonthPay;
	static int threeMonthPay;
	static int oneYearPay;
	
	static int[] plan;
	static int[] charge;
			
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();

			//4. 12월까지 항상 해당 월까지 최적의 값을 구해주기
			for (int month = 1; month <= 12; month++) {
				//4-1. 3월 전까지 1일, 1개월 이용권 고려  + 이전 달까지의 최적값
				if(month < 3) {
					charge[month] = Math.min(plan[month] * oneDayPay, oneMonthPay) + charge[month-1];
				}
				//4-2. 3월부터 3개월 이용권까지 고려 
				//1일, 1개월 이용권은 이전 달까지 최적합 더해주기
				//3개월 이용권은 3달 전까지의 최적합 더해주기
				else {
					charge[month] = Math.min(plan[month] * oneDayPay, oneMonthPay) + charge[month-1];
					charge[month] = Math.min(charge[month], threeMonthPay + charge[month-3]);
				}
			}
			//5. 12월달에 저장된 값은 12월을 고려한 값 -> 1년 이용권과 비교 후 min 출력
			sb.append(Math.min(charge[12], oneYearPay)).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. 각 이용권 요금 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		oneDayPay = Integer.parseInt(st.nextToken());
		oneMonthPay = Integer.parseInt(st.nextToken());
		threeMonthPay = Integer.parseInt(st.nextToken());
		oneYearPay = Integer.parseInt(st.nextToken());
		
		//3. 각 월의 이용계획 입력 받기
		plan = new int[13];
		st = new StringTokenizer(br.readLine().trim());
		for (int month = 1; month <= 12; month++) {
			plan[month] = Integer.parseInt(st.nextToken());
		}
		charge = new int[13];
	}
}