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
		
		// 3. 용액이 정렬되어 입력되므로 배열의 시작과 끝에서 비교하여 0에 가까운 값 찾기
		int left = 0;
		int right = solCnt-1;
		while(left < right) {
			long sum = solution[left] + solution[right];
			
			if(Math.abs(sum) <= minDiff) {
				result1 = solution[left];
				result2 = solution[right];
				minDiff = Math.abs(sum);
				
				if(sum == 0) {
					break;
				}
			}
			
			if(sum < 0) {
				left++;
			}
			else if(sum > 0) {
				right--;
			}
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
}