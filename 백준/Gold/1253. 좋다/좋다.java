import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 1. 개수, 수 입력 받기
 * 2. 서로 다른 수를 뽑는 조합 구하기
 * 3. (탈출조건) 현재 뽑을 인덱스가 2이면
 * 	3-1. 2개 수의 합이 배열에 존재하는지 이분탐색
 * 	3-2. 이분탐색으로 찾았을 때 
 * 		3-2-1. 방문되지 않은 것들 GOOD 처리
 * 4. 모든 수를 순회하도록 반복
 * 	4-1. 해당 수가 방문되지 않았으면 선택
 * 	4-2. 방문 처리
 * 	4-3. 다음 선택 인덱스, 순회를 시작할 인덱스 넣고 조합 재귀호출
 * 	4-4. 원상복구
 *  
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int[] selected;
	static boolean[] visited;
	static Set<Long> goodNum;
	
	static long[] originArr;
	static int size;
	static int goodCnt;
	
	public static void main(String[] args) throws IOException {
		
		// 1. 개수, 수 입력 받기
		inputData();
		
		// 2. 서로 다른 수를 뽑는 조합 구하기
		combination(0, 0);
		
		for (int i = 0; i < size; i++) {
			if(goodNum.contains(originArr[i])) {
				goodCnt++;
			}
		}
		
		System.out.println(goodCnt);
	}

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		originArr = new long[size];
		for (int i = 0; i < size; i++) {
			originArr[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(originArr);
		goodCnt = 0;
		selected = new int[2];
		visited = new boolean[size];
		goodNum = new HashSet<>();
	}
	
	static void combination(int selectedIndex, int elementIndex) {
		// 3. (탈출조건) 현재 뽑을 인덱스가 2이면
		if(selectedIndex == 2) {
			// 3-1. 2개 수의 합이 배열에 존재하는지 이분탐색
			binarySearch(originArr[selected[0]] + originArr[selected[1]]);
			return;
		}
		
		// 4. 모든 수를 순회하도록 반복
		for (int i = elementIndex; i < size; i++) {
			// 4-1. 해당 수가 방문되지 않았으면 선택
			if(!visited[i]) {
				selected[selectedIndex] = i;
				// 4-2. 방문 처리
				visited[i] = true;
				// 4-3. 다음 선택 인덱스, 순회를 시작할 인덱스 넣고 조합 재귀호출
				combination(selectedIndex+1, i+1);
				// 4-4. 원상복구
				visited[i] = false;
			}
		}	
	}
	
	static void binarySearch(long target) {
		int start = 0;
		int end = size-1;
		
		while(start <= end) {
			int mid = start + (end - start) / 2;
			
			// 3-2. 이분탐색으로 찾았을 때 
			if(originArr[mid] == target) {
				// 3-2-1. 방문되지 않은 것들 GOOD 처리
				for (int i = 0; i < size; i++) {
					if(originArr[i] > target) {
						break;
					}
					
					if(originArr[i] == target && !visited[i]) {
						goodNum.add(target);
						break;
					}
				}
				return;
			}
			else if(originArr[mid] > target) {
				end = mid - 1;
			}
			else if(originArr[mid] < target) {
				start = mid + 1;
			}
		}
	}
}