import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int solCnt;
	static long[] solution;
	static long result1, result2;
	static long minDiff;

	public static void main(String[] args) throws IOException {
		
		// 1. 용액 정보 입력 받기
		inputData();
		
		// 2. 모든 용액이 음수 or 양수인 경우 처리
		if(solution[solCnt-1] < 0) {
			System.out.println(solution[solCnt-2] + " " + solution[solCnt-1]);
			return;
		}
		else if(solution[0] > 0) {
			System.out.println(solution[0] + " " + solution[1]);
			return;
		}
		
		// 3. 1번 용액 고정 후 이분탐색으로 2번 용액과 합이 0에 가까운 것 갱신
		for (int i = 0; i < solCnt; i++) {
			binarySearch(i);
		}
		
		System.out.println(result1 + " " + result2);
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		solCnt = Integer.parseInt(st.nextToken());
		
		solution = new long[solCnt];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < solCnt; i++) {
			solution[i] = Integer.parseInt(st.nextToken());
		}
		
		minDiff = Integer.MAX_VALUE;
	}
	
	static void binarySearch(int idx) {
		int start = idx+1;
		int end = solCnt-1;
		long num1 = solution[idx];
		
		int mid = 0;
		while(start <= end) {
			mid = start + (end - start) / 2;
			
			long sum = num1 + solution[mid];
			if(minDiff >= Math.abs(sum)) {
				minDiff = Math.abs(sum);
				result1 = num1;
				result2 = solution[mid];
				
				if(sum == 0) {
					return;
				}
			}
			
			if(sum < 0) {
				start = mid + 1;
			}
			else if(sum > 0) {
				end = mid - 1;
			}
		}
	}
}