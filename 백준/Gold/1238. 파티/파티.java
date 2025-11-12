import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1. 학생, 도로, 장소 입력 받기
 * 2. 인접리스트에 도로 입력 받기
 * 3. 각 마을에서 이동하는 최소 비용 구하기
 * 	3-1. idx를 시작점으로 설정
 * 	3-2. 공백큐가 될 때까지 반복
 * 	3-3. dist에 있는 비용보다 현재 노드의 비용이 더 크면 pass
 * 	3-4. 현재 노드의 end를 기준으로 갈 수 있는 노드 추가
 * 4. 학생별 오고 가는 비용 중 최대 구하기
 */


public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static class Node{
		int e, c;
		Node(int e, int c){
			this.e = e;
			this.c = c;
		}
	}
	
	static int INF = Integer.MAX_VALUE / 2;
	
	static int N, M, X;
	static List<Node>[] adjArr;
	static int[][] dist;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		inputData();

		// 3. 각 마을에서 이동하는 최소 비용 구하기
		for (int i = 1; i <= N; i++) {
			calDist(i);
		}

		// 4. 학생별 오고 가는 비용 중 최대 구하기
		int max = Integer.MIN_VALUE;
		int cost;
		for (int i = 1; i <= N; i++) {
			if(X == i) {
				continue;
			}
			
			cost = dist[i][X] + dist[X][i];
			
			if(max < cost) {
				max = cost;
			}
		}
		
		System.out.println(max);
	}
	
	static void calDist(int idx) {
		pq = new PriorityQueue<>((a, b) -> a.c - b.c);
		
		// 3-1. idx를 시작점으로 설정
		pq.offer(new Node(idx, 0));
		dist[idx][idx] = 0;
		
		// 3-2. 공백큐가 될 때까지 반복
		while(!pq.isEmpty()) {
			Node n = pq.poll();

			// 3-3. dist에 있는 비용보다 현재 노드의 비용이 더 크면 pass
			if(dist[idx][n.e] < n.c) {
				continue;
			}

			// 3-4. 현재 노드의 end를 기준으로 갈 수 있는 노드 추가
			addAdjNode(idx, n.e);
		}
	}
	
	static void addAdjNode(int distIdx, int x) {
		for(Node n : adjArr[x]) {
			int newRouteDist = dist[distIdx][x] + n.c;
			if(dist[distIdx][n.e] <= newRouteDist) {
				continue;
			}
			dist[distIdx][n.e] = newRouteDist;
			pq.offer(new Node(n.e, newRouteDist));
		}
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		// 1. 학생, 도로, 장소 입력 받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		// 2. 인접리스트에 도로 입력 받기
		adjArr = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adjArr[i] = new ArrayList<>();
		}
		
		int s, e, c;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adjArr[s].add(new Node(e, c));
		}
		
		dist = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dist[i][j] = INF;
			}
		}
	}
}