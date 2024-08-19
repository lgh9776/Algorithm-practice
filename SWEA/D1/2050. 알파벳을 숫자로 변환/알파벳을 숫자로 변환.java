import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 알파벳 입력 받기 (Alphabet)
 * 2. 아스키코드를 이용해서 숫자 변환 후 출력
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 알파벳 입력 받기 (Alphabet)
		char[] alphabet = st.nextToken().toCharArray();
		
		//2. 아스키코드를 이용해서 숫자 변환 후 출력
		for(int index = 0; index < alphabet.length; index++) {
			int wordNum = alphabet[index] - 'A' + 1;
			sb.append(wordNum).append(" ");
		}
		System.out.print(sb);
	}
}
