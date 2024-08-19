import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 비밀번호, 주어진 번호 입력 받기
 * 2. 주어진 번호가 비밀번호와 같아질 때까지 반복
 * 3. 마지막 비교 횟수 증가
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 비밀번호, 주어진 번호 입력 받기
		int password = Integer.parseInt(st.nextToken());
		int num = Integer.parseInt(st.nextToken());
		
		//2. 주어진 번호가 비밀번호와 같아질 때까지 반복
		int count = 0;
		while(true) {
			if(password == (num + count)) {
				break;
			}
			if((num + count) == 1000) {
				num = 0;
			}
			count++;
		}
		//3. 마지막 비교 횟수 증가
		count++;
		System.out.println(count);
	}
}
