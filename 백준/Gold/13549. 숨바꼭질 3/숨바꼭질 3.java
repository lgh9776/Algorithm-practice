import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1. 위치 입력 받기
 * 2. 출발점 큐에 넣고 시간 갱신 (수빈이 점)
 * 3. 공백큐가 될 때까지 반복
 * 	3-1. 큐에서 가장 시간이 적은 것 뽑기
 * 	3-2. 해당 시간이 time배열에 있는 시간보다 크면 pass
 * 	3-3. 해당 지점에서 갈 수 있는 곳 각각이 time배열에 있는 시간보다 작으면 갱신 후 큐에 넣기
 * 4. 시간 출력
 */

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int INF = Integer.MAX_VALUE / 2;
	
	static class Node{
		int e, t;
		Node(int e, int t){
			this.e = e;
			this.t = t;
		}
	}
	
	static int N, K;
	static int[] time;
	static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		// 1. 위치 입력 받기
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		time = new int[100001];
		Arrays.fill(time, INF);
		pq = new PriorityQueue<>((a, b) -> a.t - b.t);
		
		// 2. 출발점 큐에 넣기 (수빈이 점)
		pq.offer(new Node(N, 0));
		time[N] = 0;
		
		// 3. 공백큐가 될 때까지 반복
		while(!pq.isEmpty()) {
			// 3-1. 큐에서 가장 시간이 적은 것 뽑기
			Node now = pq.poll();
			
			// 3-2. 해당 시간이 time배열에 있는 시간보다 크면 pass
			if(now.t > time[now.e]) {
				continue;
			}
			
			// 3-3. 해당 지점에서 갈 수 있는 곳이 time배열에 있는 시간보다 작으면 갱신 후 큐에 넣기
			if(now.e+1 <= 100000 && now.t + 1 < time[now.e+1]) { // X+1로 이동
				time[now.e+1] = now.t + 1;
				pq.offer(new Node(now.e+1, now.t+1));
			}
			
			if(now.e-1 >= 0 && now.t + 1 < time[now.e-1]) { // X-1로 이동
				time[now.e-1] = now.t + 1;
				pq.offer(new Node(now.e-1, now.t+1));
			}
			
			if(now.e*2 <= 100000 && now.t < time[now.e*2]) { // 2X로 이동
				time[now.e*2] = now.t;
				pq.offer(new Node(now.e*2, now.t));
			}
		}
		
		// 4. 시간 출력
		System.out.println(time[K]);
	}
}