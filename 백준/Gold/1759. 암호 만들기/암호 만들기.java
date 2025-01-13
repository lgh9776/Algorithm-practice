
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 정렬된 문자열 선호 -> 조합
 * 
 * 1. 암호 길이, 알파벳 개수 입력 받기
 * 2. 알파벳 입력 받기
 * 3. 알파벳 정렬
 * 4. 조합으로 암호 길이만큼 알파벳 선택하기
 * 5. (탈출조건) 암호 길이만큼 알바벳을 선택했을 때
 * 	5-1. 최소 1개의 모음과 2개의 자음이 있으면 출력
 * 6. 알파벳 개수만큼 반복
 * 	6-1. 방문되지 않은 칸이면 방문 표시 후 선택
 * 	6-2. 다음 선택을 위한 재귀함수 호출
 * 	6-3. 방문 표시 해제, 선택 해제
 * 
 */
public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int passLen;
	static int alphaCnt;
	static char[] alphas;
	static boolean[] isVisited;
	static char[] pass;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		input();
		
		//4. 조합으로 암호 길이만큼 알파벳 선택하기
		combination(0, 0);

		System.out.println(sb);
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		//1. 암호 길이, 알파벳 개수 입력 받기
		passLen = Integer.parseInt(st.nextToken());
		alphaCnt = Integer.parseInt(st.nextToken());
		
		//2. 알파벳 입력 받기
		alphas = new char[alphaCnt];
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < alphaCnt; index++) {
			alphas[index] = st.nextToken().charAt(0);
		}
		
		//3. 알파벳 정렬
		Arrays.sort(alphas);
		
		isVisited = new boolean[alphaCnt];
		pass = new char[passLen];
	}

	static void combination(int selectIndex, int elementIndex) {
		//5. (탈출조건) 암호 길이만큼 알바벳을 선택했을 때
		if(selectIndex >= passLen) {
			int vowel = 0; //모음
			int consonant = 0; //자음
			
			//5-1. 최소 1개의 모음과 2개의 자음이 있으면 출력
			for (int i = 0; i < passLen; i++) {
				if(pass[i] == 'a' || pass[i] == 'e' || pass[i] == 'i'
					|| pass[i] == 'o' || pass[i] == 'u') {
					vowel++;
				}
				else {
					consonant++;
				}
				
				if(vowel >= 1 && consonant >= 2){
					sb.append(pass).append("\n");
					break;
				}
			}
			return;
		}
		
		//6. 알파벳 개수만큼 반복
		for (int i = elementIndex; i < alphaCnt; i++) {
			//6-1. 방문되지 않은 칸이면 방문 표시 후 선택
			if(!isVisited[i]) {
				isVisited[i] = true;
				pass[selectIndex] = alphas[i];
			}
			
			//6-2. 다음 선택을 위한 재귀함수 호출
			combination(selectIndex + 1, i+1);
			
			//6-3. 방문 표시 해제, 선택 해제
			isVisited[i] = false;
		}
	}
}