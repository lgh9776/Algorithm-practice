import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. words 입력 받기
 * 3. 양 끝 인덱스부터 같은지 검사
 * 	3-1. 다르면 0 출력
 * 	3-2. 같으면 1 출력
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			//2. words 입력 받기
			String[] words = br.readLine().trim().split("");
			
			//3. 양 끝 인덱스부터 같은지 검사
			boolean isSame = true;
			for(int index = 0; index < words.length; index++) {
				if(index > words.length-index-1) {
					break;
				}
				
				//3-1. 다르면 0 출력
				if(!words[index].equals(words[words.length-index-1])) {
					isSame = false;
					sb.append("0").append("\n");
					break;
				}
			}
			//3-2. 같으면 1 출력
			if(isSame) {
				sb.append("1").append("\n");
			}
		}
		System.out.print(sb);
	}
}
