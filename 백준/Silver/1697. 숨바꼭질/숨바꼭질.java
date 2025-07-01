import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 큐에 위치와 시간을 함께 담아서 계산
 * 
 * 1. 위치 입력 받기
 * 2. N에서 K까지 BFS 탐색
 * 	2-1. 현재 위치가 K와 같을 경우 return
 * 	2-2. +1, -1로 걷는 경우, 순간이동 하는 경우가 방문되지 않았으면 큐에 넣기
 * 	2-3. 시간 계산
 * 3. 시간 출력
 * 
 */

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int N, K;
	static int time;
	static boolean[] isVisited;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		// 1. 위치 입력 받기
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		time = 0;
		isVisited = new boolean[100001];

		// 2. N에서 K까지 BFS 탐색
		findK();
		
		// 3. 시간 출력
		System.out.println(time);
	}
	
	static void findK() {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(N);
		isVisited[N] = true;
		
		while(true) {
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				Integer now = q.poll();
				
				// 2-1. 현재 위치가 K와 같을 경우 return
				if(now == K) {
					return;
				}
				
				// 2-2. +1, -1로 걷는 경우, 순간이동 하는 경우가 방문되지 않았으면 큐에 넣기
				if(now + 1 <= 100000 && !isVisited[now + 1]) {
					isVisited[now + 1] = true;
					q.offer(now + 1);
				}
				
				if(now - 1 >= 0 && !isVisited[now - 1]) {
					isVisited[now - 1] = true;
					q.offer(now - 1);
				}
				
				if(now * 2 >= 0 && now * 2 <= 100000 && !isVisited[now * 2]) {
					isVisited[now * 2] = true;
					q.offer(now * 2);
				}
			}
			
			// 2-3. 시간 계산
			time++;
		}
	}
}