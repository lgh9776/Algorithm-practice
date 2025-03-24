import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 빌딩 수, 높이들 입력 받기 (인덱스 1부터 시작)
 * 2. 모든 좌표 순회
 * 	2-1. 기준 빌딩의 오른쪽 비교
 * 		2-1-1. 각 꼭대기의 좌표로 1차 함수 구하기
 * 		2-1-2. 사이에 있는 빌딩의 꼭대기 좌표를 1차 함수에 넣어 가로막히지 않으면 +1
 * 	2-2. 기준 빌딩의 왼쪽 비교
 * 		2-2-1. 각 꼭대기의 좌표로 1차 함수 구하기
 * 		2-2-2. 사이에 있는 빌딩의 꼭대기 좌표를 1차 함수에 넣어 가로막히지 않으면 +1
 * 	2-3. 모두 비교가 끝났을 경우 max 갱신
 * 
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int cnt;
	static long[] lens;
	static int max;

	public static void main(String[] args) throws IOException {
		// 1. 빌딩 수, 높이들 입력 받기 (인덱스 1부터 시작)
		inputData();
	
		// 2. 모든 좌표 순회
		for (int i = 1; i <= cnt; i++) {
			int count = 0;
			
			// 2-1. 기준 빌딩의 오른쪽 비교
			for (int j = i+1; j <= cnt; j++) {
				// 2-1-1. 각 꼭대기의 좌표로 1차 함수 구하기
				double slope = (double)(lens[i] - lens[j]) / (i-j);
				double intercept = lens[i] - (slope * i);
				
				// 2-1-2. 사이에 있는 빌딩의 꼭대기 좌표를 1차 함수에 넣어 가로막히지 않으면 +1
				boolean isChange = false;
				for (int k = i+1; k < j ; k++) {
					double temp = slope * k + intercept;
					if(lens[k] >= temp) {
						isChange = true;
						break;
					}
				}
				
				if(!isChange) {
					count++;
				}
			}
			
			// 2-2. 기준 빌딩의 왼쪽 비교
			for (int j = i-1; j >= 1; j--) {
				// 2-2-1. 각 꼭대기의 좌표로 1차 함수 구하기
				double slope = (double)(lens[j] - lens[i]) / (j-i);
				double intercept = lens[i] - (slope * i);
				
				// 2-2-2. 사이에 있는 빌딩의 꼭대기 좌표를 1차 함수에 넣어 가로막히지 않으면 +1
				boolean isChange = false;
				for (int k = i-1; k > j ; k--) {
					double temp = slope * k + intercept;
					if(lens[k] >= temp) {
						isChange = true;
						break;
					}
				}
				
				if(!isChange) {
					count++;
				}
			}
			
			// 2-3. 모두 비교가 끝났을 경우 max 갱신
			max = max < count ? count : max;
		}
		System.out.println(max);
	}

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		cnt = Integer.parseInt(st.nextToken());
		lens = new long[cnt+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= cnt; i++) {
			lens[i] = Integer.parseInt(st.nextToken());
		}
		max = Integer.MIN_VALUE;
	}
}