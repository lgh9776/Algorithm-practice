import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 양의 초기값이 될 숫자 입력 받기
 * 3. 2씩 곱하면서
 * 	3-1. 각 자릿수 확인
 * 	3-2. boolean 배열을 만들어 각 자릿수의 값을 봤는지 체크
 * 	3-3. boolean 배열에 false가 없으면 출력
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
			sb.append("#").append(testCase).append(" ");
			
			//2. 양의 초기값이 될 숫자 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			int num = Integer.parseInt(st.nextToken());
			int mulNum = 1;
			boolean[] isCheckNum = new boolean[10];
			while(true) {
				//3. mulNum + 1씩 곱하면서
				//3-1. 각 자릿수 확인
				String[] numToken = (num * mulNum + "").split(""); //숫자를 문자열로 만든 후 char 배열로 변환
				
				//3-2. boolean 배열을 만들어 각 자릿수의 값을 봤는지 체크
				for (int index = 0; index < numToken.length; index++) {
					isCheckNum[Integer.parseInt(numToken[index])] = true;
				}
				
				//3-3. boolean 배열에 false가 없으면 출력
				boolean isAllTrue = true;
				for (int index = 0; index < isCheckNum.length; index++) {
					if(isCheckNum[index] == false) {
						isAllTrue = false;
					}
				}
				
				if(isAllTrue)
					break;
				
				mulNum++;
			}
			sb.append(mulNum * num).append("\n");
		}
		System.out.print(sb);
	}
}
