import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 숫자 2개 (firstNum, secondNum) 입력 받기
 * 2. 사칙연산 결과 출력
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		//1. 숫자 2개 (firstNum, secondNum) 입력 받기
		int firstNum = Integer.parseInt(st.nextToken());
		int secondNum = Integer.parseInt(st.nextToken());
		
		//2. 사칙연산 결과 출력
		System.out.println(firstNum + secondNum);
		System.out.println(firstNum - secondNum);
		System.out.println(firstNum * secondNum);
		System.out.println((int)(firstNum / secondNum));
	}
}
