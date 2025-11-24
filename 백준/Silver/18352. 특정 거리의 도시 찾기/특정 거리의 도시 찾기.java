import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 기본 정보 입력 받기
 * 2. 도로를 입력 받아 그래프 생성
 * 3. 출발 도시 지정
 * 4. 공백큐가 될 때까지 반복
 * 	4-1. 각 도시에서 갈 수 있는 곳 선택
 * 	4-2. 비용이 dist의 거리보다 크면 pass
 * 	4-3. 거리 갱신 후 큐에 넣기
 * 5. K거리를 갖는 도시 출력
 */

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int INF = Integer.MAX_VALUE / 2;
	
	static int N, M, K, X;
	static List<Integer>[] adjArr;
	static int[] dist;
	static Queue<Integer> q;
	
	public static void main(String[] args) throws IOException {
		InputData();
		
		// 3. 출발 도시 지정
		q.offer(X);
		dist[X] = 0;
		
		// 4. 공백큐가 될 때까지 반복
		while(!q.isEmpty()) {
			int now = q.poll();
			
			// 4-1. 각 도시에서 갈 수 있는 곳 선택
			for(int b : adjArr[now]) {
				// 4-2. 비용이 dist의 거리보다 크면 pass
				if(dist[b] < dist[now] + 1) {
					continue;
				}
				
				// 4-3. 거리 갱신 후 큐에 넣기
				dist[b] = dist[now] + 1;
				q.offer(b);
			}
		}
		
		// 5. K거리를 갖는 도시 출력
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if(dist[i] == K) {
				sb.append(i).append("\n");
				cnt++;
			}
		}
		
		if(cnt == 0) {
			System.out.println(-1);
		}
		else {
			System.out.println(sb);
		}
	}

	static void InputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 기본 정보 입력 받기
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		// 2. 도로를 입력 받아 그래프 생성
		adjArr = new ArrayList[N+1];
		for (int i = 1; i <= N ; i++) {
			adjArr[i] = new ArrayList<>();
		}
		
		int s, e;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			adjArr[s].add(e);
		}
		
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		q = new ArrayDeque<Integer>();
	}
	
}