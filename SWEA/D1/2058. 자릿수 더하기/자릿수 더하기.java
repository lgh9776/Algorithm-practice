import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. 자연수(num) 입력 받기
 * 2. num이 0이 될 때까지 반복하기
 * 3. 10으로 나머지 연산한 값 더하기
 * 4. 10으로 나눠서 다음 자리수 구할 수 있도록 하기
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		//1. 자연수(num) 입력 받기
		int num = Integer.parseInt(st.nextToken());
		
		
		int sum = 0;
		//2. num이 0이 될 때까지 반복하기
		while(num != 0) {
			//3. 10으로 나머지 연산한 값 더하기
			sum += (num % 10);
			//4. 10으로 나눠서 다음 자리수 구할 수 있도록 하기
			num /= 10;
		}
		System.out.println(sum);
	}
}
