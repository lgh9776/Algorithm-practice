import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 자연수 num 입력 받기
 * 2. 수열의 길이 len 입력 받기
 * 3. 순열 재귀함수 호출
 * 4. (기재조건) depth가 len과 같으면 return
 * 5. (전처리) 선택될 수 있는 요소의 개수만큼 반복 (num)
 * 	5-1. 선택되지 않은 element 찾기
 * 	5-2. 해당 element를 select 배열에 넣기
 * 	5-3. 해당 element가 선택됨을 표시
 * 	5-4. (재귀 호출) 다음 depth로 넘어가기 위해 재귀함수 호출
 * 6. (후처리) 해당 depth에서 선택했던 element 선택 해제 
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int[] select;
	static boolean[] visit;
	
	static int num;
	static int len;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ");
		sb = new StringBuilder();
		
		//1. 자연수 num 입력 받기
		num = Integer.parseInt(st.nextToken());
		//2. 수열의 길이 len 입력 받기
		len = Integer.parseInt(st.nextToken());
		
		select = new int[len]; //수열 개수만큼 담아야함
		visit = new boolean[num]; //요소 개수만큼 담아야함
		
		//3. 순열 재귀함수 호출
		permutation(0);
		System.out.print(sb);
	}
	
	static void permutation(int selectIndex) {
		//4. (기재조건) selectIndex가 len과 같으면 return -> 더 뽑을 수 없다는 의미
		if(selectIndex == len) {
			for(int element : select) {
				sb.append(element).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		//5. (전처리) 선택될 수 있는 요소의 개수만큼 반복 (num)
		for(int index = 0; index < num; index++) {
			//5-1. 선택되지 않은 element 찾기
			if(visit[index] == false) {
				//5-2. 해당 element를 select 배열에 넣기
				select[selectIndex] = index + 1;
				//5-3. 해당 element가 선택됨을 표시
				visit[index] = true;
				//5-4. (재귀 호출) 다음 depth로 넘어가기 위해 재귀함수 호출
				permutation(selectIndex + 1);
				
				//6. (후처리) 해당 depth에서 선택했던 element 선택 해제 
				visit[index] = false;
			}	
		}
	}
}
