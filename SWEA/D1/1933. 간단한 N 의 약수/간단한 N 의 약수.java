import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 정수 1개 (num) 입력 받기
 * 2. n의 약수 구하기
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1.정수 1개 (num) 입력 받기
		int num = Integer.parseInt(st.nextToken());
		
		//2. n의 약수 구하기
		for(int count = 1; count <= num; count++) {
			if(num % count == 0) {
				sb.append(count).append(" ");
			}
		}
		System.out.print(sb);
	}
}