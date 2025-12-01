import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 용액의 수, 특성값 입력 받기
 * 2. 특성값 오름차순 정렬
 * 3. 첫번째 용액부터 0에 가까운 용액 이분탐색으로 찾기
 * 	3-1. 기준 용액보다 mid가 작아지면 return
 * 	3-2. 특성값이 0일 경우 return
 * 	3-3. 현재 특성값보다 0에 가까우면 갱신 (절댓값 비교)
 * 	3-4. 0보다 크면 더 작은 범위로, 작으면 더 큰 범위로
 * 4. 두 용액 출력
 */

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int solCnt;
	static long[] val;
	static long fixSol;
	static long r1, r2;
	static boolean isFind;

	public static void main(String[] args) throws IOException {
		inputData();
		
		// 3. 첫번째 용액부터 0에 가까운 용액 이분탐색으로 찾기
		boolean isFind = false;
		for (int i = 0; i < solCnt; i++) {
			fixSol = val[i];
			isFind = false;
			binarySearch(i+1, solCnt-1);
			if(isFind == true) {
				break;
			}
		}
		
		// 4. 두 용액 출력
		System.out.println(r1 + " " + r2);
	}
	
	static void binarySearch(int s, int e) {
		if(isFind == true) {
			return;
		}
		
		int mid = (s + e) / 2;
		
		// 3-1. mid 범위를 벗어나면 return
		if(mid > e || mid < s) {
			return;
		}
		
		// 3-2. 특성값이 0일 경우 return
		if(fixSol + val[mid] == 0) {
			r1 = fixSol;
			r2 = val[mid];
			isFind = true;
			return;
		}
		
		// 3-3. 현재 특성값보다 0에 가까우면 갱신 (절댓값 비교)
		if(Math.abs(r1 + r2) > Math.abs(fixSol + val[mid])) {
			r1 = fixSol;
			r2 = val[mid];
		}
		
		// 3-4. 0보다 크면 더 작은 범위로, 작으면 더 큰 범위로
		if(fixSol + val[mid] > 0) {
			binarySearch(s, mid - 1);
		}
		else {
			binarySearch(mid + 1, e);
		}
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 용액의 수, 특성값 입력 받기
		st = new StringTokenizer(br.readLine());
		solCnt = Integer.parseInt(st.nextToken());
		
		val = new long[solCnt];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < solCnt; i++) {
			val[i] = Long.parseLong(st.nextToken());
		}
		
		// 2. 특성값 오름차순 정렬
		Arrays.sort(val);

		r1 = 2000000000;
		r2 = 2000000000;
	}
}