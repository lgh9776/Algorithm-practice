import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 동굴의 길이와 높이 입력 받기
 * 2. 석순과 종유석의 크기에 따라 카운팅
 * 3. 누적합으로 각 층에서 지나는 장애물 수로 변경
 * 4. 각 층에서 지나는 장애물 개수를 구하며 최소인 것 갱신
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 1. 동굴의 길이와 높이 입력 받기
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		// 2. 석순과 종유석의 크기에 따라 카운팅
		int[] bottom = new int[H+1];
		int[] top = new int[H+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());;
			
			// 석순
			if(i % 2 == 0) {
				bottom[size]++;
			}
			// 종유석
			else {
				top[size]++;
			}
		}
		
		// 3. 누적합으로 각 층에서 지나는 장애물 수로 변경
		for (int i = H; i >= 2 ; i--) {
			bottom[i-1] += bottom[i];
		}
		
		for (int i = H; i >= 2 ; i--) {
			top[i-1] += top[i];
		}
		
		// 4. 각 층에서 지나는 장애물 개수를 구하며 최소인 것 갱신
		int min = Integer.MAX_VALUE;
		int minCnt = 0;
		for (int i = 1; i <= H; i++) {
			int cnt = bottom[H-i+1] + top[i];
			
			if(cnt < min) {
				min = cnt;
				minCnt = 1;
			}
			else if(cnt == min) {
				minCnt++;
			}
		}
		
		System.out.println(min + " " + minCnt);
	}
}