import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 숫자 입력 받기
 * 2. 숫자만큼 # 추가하기
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 숫자 입력 받기
		int count = Integer.parseInt(st.nextToken());
		
		//2. 숫자만큼 # 추가하기
		for(int add = 0; add < count; add++) {
			sb.append("#");
		}
		System.out.println(sb);
	}
}
