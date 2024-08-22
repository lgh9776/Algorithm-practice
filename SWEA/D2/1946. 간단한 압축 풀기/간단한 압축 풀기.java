import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 압축된 문서의 알파벳, 숫자 쌍 개수 입력 받기
 * 3. 압축 정보 입력 받기
 * 4. 압축 정보에 따라 알파벳 출력에 넣어주기
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append("\n");
			
			//2. 압축된 문서의 알파벳, 숫자 쌍 개수 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			int dataNum = Integer.parseInt(st.nextToken());
			
			//3. 압축 정보 입력 받기
			char[] alphabet = new char[dataNum];
			int[] count = new int[dataNum];
			for (int index = 0; index < dataNum; index++) {
				st = new StringTokenizer(br.readLine().trim());
				alphabet[index] = st.nextToken().charAt(0);
				count[index] = Integer.parseInt(st.nextToken());
			}
			
			//4. 압축 정보에 따라 알파벳 출력에 넣어주기
			int printCnt = 0;
			for (int index = 0; index < dataNum; index++) {
				for (int printCount = 0; printCount < count[index]; printCount++) {
					sb.append(alphabet[index]);
					printCnt++;
					//10개마다 줄바꿈
					if(printCnt % 10 == 0) {
						sb.append("\n");
					}
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
