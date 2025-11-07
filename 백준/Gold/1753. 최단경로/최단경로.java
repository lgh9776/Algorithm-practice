import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 정점, 간선 개수 입력 받기
 * 2. 시작 정점 번호 받기
 * 3. 간선 정보 입력 받아서 인접리스트 생성
 * 4. 방문 여부, 최단 경로값 입력 저장할 배열 생성
 * 5. 시작점부터 인접한 정점 모두 순회
 * 	5-1. 인접한데 방문되지 않은 정점 중 (출발점 + 경유지 ~ 인접한 정점)까지의 가중치 값이 저장된 값보다 더 작으면 갱신
 * 	5-2. 방문한 정점이면 고려하지 않음
 * 	5-3. 해당 정점과 인접한 모든 정점 순회
 * 	5-4. 경유한 가중치가 그 전 가중치보다 작을 경우 갱신
 * 	5-5. 해당 정점값, 가중치를 edge로 큐에 추가 -> 방문되었는지 판단은 후에 큐에서 뽑을 때
 * 6. 모든 정점을 방문했으면 결과 출력
 * 
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static class Edge implements Comparable<Edge>{
		int end, weight;

		public Edge(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	static int vCnt, eCnt;
	static int startVetex;
	static List<Edge>[] adj;
	static boolean[] isVisit;
	static int[] shortest;
	
	public static void main(String[] args) throws IOException {
		inputData();
		
		//5. 시작점부터 인접한 정점 모두 순회
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(startVetex, 0));
		
		//5-1. 인접한데 방문되지 않은 정점 중 (출발점 + 경유지 ~ 인접한 정점)까지의 가중치 값이 저장된 값보다 더 작으면 갱신
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			//5-2. 방문한 정점이면 고려하지 않음
			if(isVisit[now.end])
				continue;
			isVisit[now.end] = true;
			
			//5-3. 해당 정점과 인접한 모든 정점 순회
			for(Edge edge : adj[now.end]) {
				//5-4. 경유한 가중치가 그 전 가중치보다 작을 경우 갱신
				if(shortest[now.end] + edge.weight < shortest[edge.end]) {
					shortest[edge.end] = shortest[now.end] + edge.weight;
					
					//5-5. 해당 정점값, 가중치를 edge로 큐에 추가 -> 방문되었는지 판단은 후에 큐에서 뽑을 때
					pq.offer(new Edge(edge.end, shortest[edge.end]));
				}
			}
		}

		//6. 모든 정점을 방문했으면 결과 출력
		for (int index = 1; index <= vCnt; index++) {
			if(shortest[index] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(shortest[index]);
		}

	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		//1. 정점, 간선 개수 입력 받기
		vCnt = Integer.parseInt(st.nextToken());
		eCnt = Integer.parseInt(st.nextToken());
		
		//2. 시작 정점 번호 받기
		st = new StringTokenizer(br.readLine().trim());
		startVetex = Integer.parseInt(st.nextToken());
		
		//3. 간선 정보 입력 받아서 인접리스트 생성
		adj = new ArrayList[vCnt+1];
		for (int index = 0; index <= vCnt; index++) {
			adj[index] = new ArrayList<>();
		}
		
		for (int index = 0; index < eCnt; index++) {
			st = new StringTokenizer(br.readLine().trim());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adj[start].add(new Edge(end, weight));
		}
		
		//4. 방문 여부, 최단 경로값 입력 저장할 배열 생성
		isVisit = new boolean[vCnt+1];
		shortest = new int[vCnt+1];
		for (int index = 0; index <= vCnt; index++) {
			shortest[index] = Integer.MAX_VALUE;
		}
		shortest[startVetex] = 0;
	}
}
