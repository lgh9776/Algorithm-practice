import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. playerA, playerB 숫자 입력 받기
 * 2. 승부 판단 (비기는 경우 없음)
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		//1. playerA, playerB 숫자 입력 받기
		int playerA = Integer.parseInt(st.nextToken());
		int playerB = Integer.parseInt(st.nextToken());
		
		//2. 승부 판단 (비기는 경우 없음)
		//가위(1), 바위(2), 보(3)
		if((playerA == 1 && playerB == 3) || (playerA == 2 && playerB == 1) || (playerA == 3 && playerB == 2))
			System.out.println("A");
		else
			System.out.println("B");
	}
}
