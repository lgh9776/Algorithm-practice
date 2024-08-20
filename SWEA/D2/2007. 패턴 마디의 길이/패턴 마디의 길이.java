import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 문자열 입력 받기 (words)
 * 3. substring을 사용해서 반복되는 문자열 찾기
 * 4. 일치하는게 최초로 등장하면 그것이 마디 => 길이 출력
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(br.readLine().trim());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			//2. 문자열 입력 받기 (words)
			String words = br.readLine().trim();
			
			//3. substring을 사용해서 반복되는 문자열 찾기
			//substring(start, end) = 해당 문자열의 start부터 end-1까지 return
			for(int index = 1; index < words.length(); index++) {
				String first = words.substring(0, index);
				String second = words.substring(index, index * 2);
				
				if(first.equals(second)) {
					sb.append(first.length()).append("\n");
					break;
				}
			}
		}
		System.out.print(sb);
	}
}
