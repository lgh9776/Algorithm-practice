import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 거스름돈 입력 받기
 * 3. 돈에 포함될 수 있는 가장 큰 단위부터 뺌
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
			sb.append("#").append(testCase).append("\n");
			
			//2. 거스름돈 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			int restMoney = Integer.parseInt(st.nextToken());
			int[] moneyCount = new int[8]; //50000~10원
			int[] money = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
			
			//3. 돈에 포함될 수 있는 가장 큰 단위부터 뺌
			for(int index = 0; index < 8; index++) {
				moneyCount[index] += restMoney / money[index];
				restMoney %= money[index];
			}
			
			//출력
			for(int index = 0; index < 8; index++) {
				sb.append(moneyCount[index]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}
