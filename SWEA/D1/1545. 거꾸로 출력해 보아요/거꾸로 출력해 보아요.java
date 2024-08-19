import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 숫자 1개 (num) 입력 받기
 * 2. num을 1씩 줄이며 0이될 때까지 반복 (= 거꾸로 출력)
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
		
		//2. num을 1씩 줄이며 0이될 때까지 반복 (= 거꾸로 출력)
		for(int count = num; count >= 0; count--) {
			sb.append(count).append(" ");
		}
		System.out.print(sb);
	}
}
