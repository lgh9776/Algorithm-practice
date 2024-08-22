import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 이용권 가격 입력 받기
 * 3. 월별 이용 계획 입력 받기
 * 4. 인자로 들어온 월이 12를 넘어가면 return (백트래킹)
 * 5. 현재 요금이 min보다 클 경우 return (백트래킹)
 * 6. 12와 같을 때 min요금 갱신 후 return (끝까지 찾은 경우)
 * 7. 각 월에 대해서 1일, 1개월, 3개월, 1년 요금 순으로 계산
 * 	7-1. 시작하는 월, 이용권에 따라 이번 달+a의 요금을 인자로 재귀 호출
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int minCharge;
	static int[] ticketCharge = new int[4];
	static int[] plan = new int[12];
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();		
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			inputData();
			calCharge(1, 0);
			
			sb.append(minCharge).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		minCharge = Integer.MAX_VALUE;
		
		//2. 이용권 가격 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		for(int ticket = 0; ticket < 4; ticket++) {
			ticketCharge[ticket] = Integer.parseInt(st.nextToken());
		}
		
		//3. 월별 이용 계획 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		for(int month = 0; month < 12; month++) {
			plan[month] = Integer.parseInt(st.nextToken());
		}
	}
	
	//인자로 들어오는 월이 현재 처리할 월
	static void calCharge(int month, int currentCharge) {
		//4. 인자로 들어온 월이 12를 넘어가면 return (백트래킹)
		if(month > 13) {
			return;
		}
		
		//5. 현재 요금이 min보다 크거나 같은 경우 return (백트래킹)
		if(currentCharge >= minCharge) {
			return;
		}
		
		//6. 13와 같을 때 (12월까지 처리했다는 뜻) min요금 갱신 후 return (끝까지 찾은 경우)
		if(month == 13) {
			minCharge = Math.min(minCharge, currentCharge);
			return;
		}
		
		//7. 각 월에 대해서 1일, 1개월, 3개월, 1년 요금 순으로 계산
		//7-1. 시작하는 월, 이용권에 따라 이번 달+a의 요금을 인자로 재귀 호출
		calCharge(month+1, currentCharge + ticketCharge[0] * plan[month-1]);
		calCharge(month+1, currentCharge + ticketCharge[1]);
		calCharge(month+3, currentCharge + ticketCharge[2]);
		calCharge(month+12, currentCharge + ticketCharge[3]);
	}
}