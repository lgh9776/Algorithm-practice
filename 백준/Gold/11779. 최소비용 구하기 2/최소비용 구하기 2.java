import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1. 도시, 버스의 개수 입력 받기
 * 2. 인접리스트로 정보 입력 받기
 * 3. 출발점에서 갈 수 있는 노드 우선순위 큐에 넣기
 * 4. 공백큐가 될 때까지 반복
 * 	4-1. 비용이 가장 작은 버스 선택
 * 	4-2. 이전 지점까지 비용 + 현재 비용이 기존의 비용보다 크면 pass
 * 	4-3. 해당 지점에서 갈 수 있는 노드 우선순위 큐에 넣기
 * 		4-3-1. 해당 지점을 경유해서 가는 비용이 더 크거나 같으면 pass
 * 		4-3-2. 비용, 이전 지점 갱신
 * 		4-3-3. 새로운 경로 큐에 넣기 (새로운 경로 확정을 위해서)
 * 5. 비용 출력, 경로 복원으로 도시 개수, 경로 출력
 * 
 * addAdjNode(갱신 단계) : 여기에서는 비용이 크거나 같을 경우 스킵
 * while(확정 단계) : 갱신 단계에서 dist를 업데이트 하고 큐에 넣기 때문에 같은 값을 스킵하면 안됨
 */

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Node {
		int e, c;
		Node(int e, int c){
			this.e = e;
			this.c = c;
		}
	}
	
	static int INF = Integer.MAX_VALUE / 2;
	
	static int n, m, start, end;
	static List<Node>[] adjArr;
	static int[] dist;
	static int[] route;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		inputData();
		
		dist[start] = 0;
		route[start] = -1;
		
		// 3. 출발점에서 갈 수 있는 노드 우선순위 큐에 넣기
		addAdjNode(start);

		// 4. 공백큐가 될 때까지 반복
		while(!pq.isEmpty()) {
			// 4-1. 비용이 가장 작은 버스 선택
			Node now = pq.poll();
			
			// 4-2. 현재 경로 비용이 기존 경로 비용보다 크면 pass
			if(dist[now.e] < now.c) {
				continue;
			}

			// 4-3. 해당 지점에서 갈 수 있는 노드 우선순위 큐에 넣기
			addAdjNode(now.e);
		}
		
		// 5. 비용 출력, 경로 복원으로 도시 개수, 경로 출력
		sb.append(dist[end]).append("\n");
		
		int i = end;
		List<Integer> routes = new ArrayList<Integer>();
		while(i != -1) {
			routes.add(i);
			i = route[i];
		}
		
		sb.append(routes.size()).append("\n");
		
		for (int j = routes.size()-1; j >= 0 ; j--) {
			sb.append(routes.get(j)).append(" ");
		}
		
		System.out.println(sb);
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 도시, 버스의 개수 입력 받기
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		
		// 2. 인접리스트로 정보 입력 받기
		adjArr = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			adjArr[i] = new ArrayList<>();
		}
		
		int s, e, c;
		boolean isMin;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			isMin = false;
			
			for (int j = 0; j < adjArr[s].size(); j++) {
				if(adjArr[s].get(j).e == e && adjArr[s].get(j).c > c) {
					adjArr[s].get(j).c = c;
					isMin = true;
				}
			}
			
			if(isMin == false) {
				adjArr[s].add(new Node(e, c));
			}
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dist = new int[n+1];
		for (int i = 1; i <= n; i++) {
			dist[i] = INF;
		}
		route = new int[n+1];
		pq = new PriorityQueue<>((a, b) -> a.c - b.c);
	}

	static void addAdjNode(int x) {
		for(Node n : adjArr[x]) {		
			// 4-3-1. 해당 지점을 경유해서 가는 비용이 더 크거나 같으면 pass
			if(dist[n.e] <= dist[x] + n.c) {
				continue;
			}
			
			// 4-3-2. 비용, 이전 지점 갱신
			dist[n.e] = dist[x] + n.c;
			route[n.e] = x;
			
			// 4-3-3. 새로운 경로 큐에 넣기 (새로운 경로 확정을 위해서)
			pq.offer(new Node(n.e, dist[x] + n.c)); 
		}
	}
}