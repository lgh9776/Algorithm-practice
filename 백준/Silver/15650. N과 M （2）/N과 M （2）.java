import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 데이터 입력 받기
 * 	1-1. elementCnt에 요소 수 입력 받기
 * 	1-2. len에 수열의 길이 입력 받기
 * 2. 요소에 대한 조합을 구하는 재귀함수 호출
 * 3. (기재조건) 더 이상 선택할 수 없으면 return (selectIndex == len)
 * 	3-1. select할 수 있는 요소가 부족할 경우 return
 * 4. (전처리) 이전 depth에서 선택하지 않은 곳부터 탐색
 * 	4-1. 요소를 선택할 수 있으면 조합에 추가
 * 	4-2. 해당 요소를 선택한 것을 표시
 * 5. (재귀호출) 추가한 요소 다음 것부터 탐색
 * 6. (후처리) 해당 요소를 선택 해제
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int elementCnt;
	static int len;
	static int[] select;
	static boolean[] visit;
	
	public static void main(String[] args) throws Exception {
		//1. 데이터 입력 받기
		inputData();
		
		//2. 요소에 대한 조합을 구하는 재귀함수 호출
		combination(0, 0);
		
		System.out.print(sb);
	}
	
	static void inputData() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1-1. elementCnt에 요소 수 입력 받기
		elementCnt = Integer.parseInt(st.nextToken());
		//1-2. len에 수열의 길이 입력 받기
		len = Integer.parseInt(st.nextToken());
		
		select = new int[len];
		visit = new boolean[elementCnt];
	}
	
	static void combination(int selectIndex, int elementIndex) {
		//3. (기재조건) 더 이상 선택할 수 없으면 return
		if(selectIndex == len) {
			for(int element : select) {
				sb.append(element).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		//3-1. select할 수 있는 요소가 부족할 경우 return
		if(len - (selectIndex + 1) - (elementCnt - elementIndex + 1) >= 0) {
			return;
		}
		
		//4. (전처리) 이전 depth에서 선택하지 않은 곳부터 탐색
		for(int index = elementIndex; index < elementCnt; index++) {
			if(visit[index] == false) {
				//4-1. 요소를 선택할 수 있으면 조합에 추가
				select[selectIndex] = index+1; 
				
				//4-2. 해당 요소를 선택한 것을 표시
				visit[index] = true;
				
				//5. (재귀호출) 추가한 요소 다음 것부터 탐색
				combination(selectIndex + 1, index);
				
				//6. (후처리) 해당 요소를 선택 해제
				visit[index] = false;
			}
		}
	}
}
