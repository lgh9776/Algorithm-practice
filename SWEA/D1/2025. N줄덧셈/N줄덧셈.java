import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 숫자 1개 (num) 입력 받기
 * 2. num만큼 반복문을 돌며 총 합 구하기
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		//1. 숫자 1개 (num) 입력 받기
		int num = Integer.parseInt(st.nextToken());
		
		//2. num만큼 반복문을 돌며 총 합 구하기
		int sum = 0;
		for(int count = 1; count <= num; count++) {
			sum += count;
		}
		
		System.out.print(sum);
	}
}