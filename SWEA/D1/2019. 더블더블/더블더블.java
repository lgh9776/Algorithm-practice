import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 숫자 1개 (num) 입력 받기
 * 2. num번 반복할 때 *2한 값 각각 출력
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 숫자 1개 (num) 입력 받기
		int num = Integer.parseInt(st.nextToken());
		
		//2. num번 반복할 때 *2한 값 각각 출력
		int mul = 1;
		sb.append(mul).append(" ");
		for(int count = 0; count < num; count++) {
			mul *= 2;
			sb.append(mul).append(" ");
		}
		System.out.print(sb);
	}
}
