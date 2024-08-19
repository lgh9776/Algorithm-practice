import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1. 문장 입력 받기 (words)
 * 2. 소문자만 대문자로 변경 후 출력
 */

public class Solution {
	static BufferedReader br;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 문장 입력 받기 (words)
		String words = br.readLine().trim();
		
		//2. 소문자만 대문자로 변경 후 출력
		System.out.print(words.toUpperCase());
	}
}