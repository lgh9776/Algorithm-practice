import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 정점, 간선의 개수 구하기
 * 3. 간선 정보로 인접정보 저장
 * 
 * (프림 알고리즘 사용)
 * 4. 트리 생성을 위해 시작점 우선순위 큐에 넣음
 * 5. 공백큐가 될 때까지 반복
 * 	5-1. 큐의 요소 poll
 * 	5-2. 선택한 요소를 방문한적 없으면 (트리에 포함되지 않았으면)
 * 	5-3. 뽑은 요소 방문 처리
 * 	5-4. 전체 cost에 해당 요소의 가중치 더하기
 * 	5-5. 뽑은 요소 기준 인접 정점이 방문을 안했으면
 * 	5-6. 인접한 정점의 정보(도착점, 가중치 가짐)를 모두 우선순위 큐에 넣음 
 * 6. 최소 비용 출력
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Edge implements Comparable<Edge>{
		int vertex, weight;

		public Edge(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight > 0 ? 1 : -1;
		}
	}
	
	static int vCnt, eCnt;
	static List<Edge>[] adj;
	static boolean[] isVisit;
	static long totalCost;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();
			
			//(프림 알고리즘 사용)
			prim();
			
			//6. 최소 비용 출력
			sb.append(totalCost).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. 정점, 간선의 개수 구하기
		st = new StringTokenizer(br.readLine().trim());
		vCnt = Integer.parseInt(st.nextToken());
		eCnt = Integer.parseInt(st.nextToken());
		isVisit = new boolean[vCnt+1];
		totalCost = 0;
		
		//3. 간선 정보로 인접정보 저장
		adj = new ArrayList[vCnt+1];
		for (int index = 0; index <= vCnt; index++) {
			adj[index] = new ArrayList<>();
		}
		
		for (int row = 0; row < eCnt; row++) {
			st = new StringTokenizer(br.readLine().trim());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adj[start].add(new Edge(end, weight));
			adj[end].add(new Edge(start, weight));
		}
	}
	
	static void prim() {
		//4. 트리 생성을 위해 시작점 우선순위 큐에 넣음
		PriorityQueue<Edge> nextVertex = new PriorityQueue<>();
		nextVertex.offer(new Edge(1, 0));
		
		//5. 공백큐가 될 때까지 반복
		while(!nextVertex.isEmpty()) {
			//5-1. 큐의 요소 poll
			Edge start = nextVertex.poll();
			
			//5-2. 선택한 요소를 방문한적 없으면 (트리에 포함되지 않았으면)
			if(!isVisit[start.vertex]) {
				//5-3. 뽑은 요소 방문 처리
				isVisit[start.vertex] = true;
				
				//5-4. 전체 cost에 해당 요소의 가중치 더하기
				totalCost += start.weight;
				
				for(Edge next : adj[start.vertex]) {
					//5-5. 뽑은 요소 기준 인접 정점이 방문을 안했으면
					if(!isVisit[next.vertex])
						//인접한 정점의 정보(도착점, 가중치 가짐)를 모두 우선순위 큐에 넣음 
						nextVertex.offer(new Edge(next.vertex, next.weight));
				}
			}
		}
	}
}