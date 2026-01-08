import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1. 도시, 버스의 개수 입력 받기
 * 2. 버스 정보 입력 받기
 * 3. 출발점, 도착점 입력 받기
 * 4. 출발점 큐에 넣기
 * 5. 공백큐가 될 때까지 반복
 * 	5-1. 가장 비용이 적은 도로 선택
 * 	5-2. 기존 경로보다 비용이 크면 pass
 * 	5-3. 해당 도착지에서 갈 수 있는 곳 큐에 넣기
 * 6. 최소 비용 출력
 */

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int INF = Integer.MAX_VALUE / 2;
	
	static class Node{
		int e, c;
		
		Node(int e, int c){
			this.e = e;
			this.c = c;
		}
	}
	
	static int N, M, start, end;
	static List<Node>[] adj;
	static int[] dist;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		inputData();
		
		// 4. 출발점 큐에 넣기
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		
		// 5. 공백큐가 될 때까지 반복
		int count = 0;
		while(!pq.isEmpty()) {
			// 5-1. 가장 비용이 적은 도로 선택
			Node now = pq.poll();
			
			// 5-2. 기존 경로보다 비용이 크면 pass
			if(dist[now.e] < now.c) {
				continue;
			}
			
			count++;
			if(count >= N) {
				break;
			}
			
			// 5-3. 해당 도착지에서 갈 수 있는 곳 큐에 넣기
			addAdj(now.e);
		}
		
		// 6. 최소 비용 출력
		System.out.println(dist[end]);
	}

	static void addAdj(int x) {
		for(Node node : adj[x]) {
			int newDist = dist[x] + node.c;
			
			if(dist[node.e] <= newDist) {
				continue;
			}
			
			dist[node.e] = newDist;
			pq.offer(new Node(node.e, newDist));
		}
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));		
		
		// 1. 도시, 버스의 개수 입력 받기
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		
		// 2. 버스 정보 입력 받기
		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		int s, e, c;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adj[s].add(new Node(e, c));
		}
		
		// 3. 출발점, 도착점 입력 받기
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		pq = new PriorityQueue<Node>((a, b) -> Integer.compare(a.c, b.c));
	}
}