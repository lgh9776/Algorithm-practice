import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 점원 수, 선반 높이 입력 받기 (staffCnt, height)
 * 3. 점원들의 키 입력 받기
 * 4. 점원들의 부분집합으로 탑의 높이 구하기
 * 5. 선반의 높이를 넘은 경우 return
 * 	5-1. 현재 점원들 키의 합과 선반 높이 차이로 높이 차 갱신
 * 6. 더 뽑을 수 없으면 return (선반의 높이를 넘은건 5번에서 처리)
 * 7. 현재 요소 선택, 다음 요소를 인자로 재귀
 * 8. 현재 요소 선택X, 다음 요소를 인자로 재귀
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int staffCnt;
	static int height;
	static int[] staffHeight;
	static int minResult;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			//2. 점원 수, 선반 높이 입력 받기 (staffCnt, height)
			st = new StringTokenizer(br.readLine());
			staffCnt = Integer.parseInt(st.nextToken());
			height = Integer.parseInt(st.nextToken());
			minResult = Integer.MAX_VALUE;
			
			//3. 점원들의 키 입력 받기
			staffHeight = new int[staffCnt];
			st = new StringTokenizer(br.readLine());
			for(int index = 0; index < staffCnt; index++) {
				staffHeight[index] = Integer.parseInt(st.nextToken());
			}

			//4. 점원들의 부분집합으로 탑의 높이 구하기
			powerSet(0, 0);
			
			sb.append(minResult).append("\n");
			
		}
		System.out.print(sb);
	}
	
	static void powerSet(int selectIndex, int sumHeight) {
		//5. 선반의 높이를 넘거나 같은 경우 return
		if(sumHeight >= height) {
			//5-1. 현재 점원들 키의 합과 선반 높이 차이로 높이 차 갱신
			minResult = Math.min(minResult, sumHeight-height);
			return;
		}
		
		//6. 더 뽑을 수 없으면 return (선반의 높이를 넘은건 5번에서 처리)
		if(selectIndex == staffCnt) {
			return;
		}
		
		//7. 현재 요소 선택, 다음 요소를 인자로 재귀
		powerSet(selectIndex + 1, sumHeight + staffHeight[selectIndex]);
		
		//8. 현재 요소 선택X, 다음 요소를 인자로 재귀
		powerSet(selectIndex + 1, sumHeight);
	}

}
