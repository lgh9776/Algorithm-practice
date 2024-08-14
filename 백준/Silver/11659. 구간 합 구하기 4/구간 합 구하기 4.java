import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 숫자 개수 입력 받기
 * 2. 합을 구해야하는 횟수 입력 받기
 * 3. n개의 숫자 입력 받기
 * 4. 합을 구하는 횟수만큼 반복
 * 	4-1. i, j 입력 받기
 *  4-2. j누적합 - i누적합 = i ~ j까지의 합
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine(), " ");
		
		//1. 숫자 개수 입력 받기
		int numCount = Integer.parseInt(st.nextToken());
		//2. 합을 구해야하는 횟수 입력 받기
		int sumCount = Integer.parseInt(st.nextToken());

		//3. n개의 숫자 입력 받으면서 누적합으로 배열에 저장해주기
		int sum = 0;
		int[] sumArr = new int[numCount+1];
		st = new StringTokenizer(br.readLine(), " ");
		for(int idx = 1; idx <= numCount; idx++) {
			sum += Integer.parseInt(st.nextToken());
			sumArr[idx] = sum;
		}
		
		//4. 합을 구하는 횟수만큼 반복하여 누적합으로 합 구하기
		for(int idx = 0; idx < sumCount; idx++) {
			st = new StringTokenizer(br.readLine(), " ");
			//4-1. i, j 입력 받기
			int startNum = Integer.parseInt(st.nextToken());
			int endNum = Integer.parseInt(st.nextToken());
			
			//4-2. j누적합 - (i-1)누적합 = i ~ j까지의 합
			//누적합으로 구하려면 j번째까지 합에서 i-1번째까지 합을 빼줘야함
			//=> i번째와 j번째를 포함한 것을 구할 수 있음
			sb.append(sumArr[endNum]-sumArr[startNum - 1]).append("\n");
		}
		System.out.print(sb);
	}
}
