import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 숫자 (num) 입력 받기
 * 2. 숫자에 3, 6, 9가 있으면  - 출력
 * 3. 없으면 해당 숫자 출력
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 숫자 (num) 입력 받기
		int num = Integer.parseInt(st.nextToken());
		
		for(int count = 1; count <= num; count++) {
			//2. 숫자에 3, 6, 9가 있으면  - 출력
			int find = count;
			boolean is369 = false;
			while(find > 0) {
				if(find % 10 == 3 || find % 10 == 6 || find % 10 == 9) {
					sb.append("-");
					is369 = true;
				}
				find /= 10;
			}
			
			//3. 없으면 해당 숫자 출력
			if(!is369) {
				sb.append(count);
			}
			sb.append(" ");
		}
		
		System.out.print(sb);
		
	}
}
