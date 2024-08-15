import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 1. 테스트 케이스만큼 반복하기
 * 2. 문자열 길이 입력 받기
 * 3. 문자열 입력 받기
 * 	3-1. 여는 괄호면 stack 넣기
 * 	3-2. 닫는 괄호면 stack의 top을 pop
 * 	3-3. 현재 요소와 다르면 break, 0출력
 *  3-4. stack에 괄호가 남아있으면 0
 * 	3-5. 유효성 검증 결과 출력
 */
public class Solution {
	static final int TEST_CASE = 10;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		//1. 테스트 케이스만큼 반복하기 (10으로 고정)
		for(int testCase = 1; testCase <= TEST_CASE; testCase++) {
			sb.append("#").append(testCase).append(" ");
			int isValid = 1; //유효성 변수
			
			//2. 문자열 길이 입력 받기
			int len = Integer.parseInt(br.readLine().trim());
			
			//3. 문자열 입력 받기
			String str = br.readLine().trim();
			Stack<Character> openToken = new Stack<>();
			
			for(int index = 0; index < len; index++) {
				char token = str.charAt(index);
				
				//3-1. 여는 괄호면 stack 넣기
				if(token == '(' || token == '{' || token == '[' || token == '<') {
					openToken.push(token);
				}
				
				//3-2. 닫는 괄호면 stack의 top을 pop
				else if(token == ')' || token == '}' || token == ']' || token == '>') {
					//3-3. 요소가 없거나 현재 요소를 여는 괄호가 아니면 break, 0출력
					if(openToken.isEmpty() == true
						|| (token == ')' && openToken.pop() != '(')
						|| (token == '}' && openToken.pop() != '{')
						|| (token == ']' && openToken.pop() != '[')
						|| (token == '>' && openToken.pop() != '<')) {
						isValid = 0;
						break;
					}
				}
			}
			
			//3-4. stack에 괄호가 남아있으면 0
			if(openToken.isEmpty() == false) {
				isValid = 0;
			}
			
			//3-5. 유효성 검증 결과 출력
			sb.append(isValid).append("\n");
		}
		System.out.print(sb);
	}
}