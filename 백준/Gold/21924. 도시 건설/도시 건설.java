import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1. 건물, 도로의 개수 입력 받기
 * 2. 도로 정보로 그래프 생성
 * 	2-1. 총 비용 계산 
 * 3. 1번 건물 큐에 넣기
 * 4. 공백큐가 될 때까지 반복
 * 	4-1. 비용이 가장 작은 도로 poll
 * 	4-2. 이미 포함된 도시면 pass
 * 	4-3. 해당 도로로 갈 수 있는 도시 그룹에 포함 (비용 증가, 연결 수 증가)
 * 	4-4. 모든 도시가 연결됐으면 break
 * 	4-5. 새로 연결된 도시에서 이용할 수 있는 도로 큐에 추가
 * 5. 절약할 수 있는 예산 출력
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
	
	static int N, M;
	static List<Node>[] adjArr;
	static PriorityQueue<Node> pq;
	static boolean[] isVisit;
	static long totalCost;

	public static void main(String[] args) throws IOException {
		inputData();
		
		// 3. 1번 건물 큐에 넣기
		pq.offer(new Node(1, 0));
		
		// 4. 공백큐가 될 때까지 반복
		long cost = 0, groupCnt = 0;
		while(!pq.isEmpty()) {
			// 4-1. 비용이 가장 작은 도로 poll
			Node now = pq.poll();
			
			// 4-2. 이미 포함된 도시면 pass
			if(isVisit[now.e] == true) {
				continue;
			}
			
			// 4-3. 해당 도로로 갈 수 있는 도시 그룹에 포함 (비용 증가, 연결 수 증가)
			isVisit[now.e] = true;
			cost += now.c;
			groupCnt++;
			
			// 4-4. 모든 도시가 연결됐으면 break
			if(groupCnt == N) {
				break;
			}
			
			// 4-5. 새로 연결된 도시에서 이용할 수 있는 도로 큐에 추가
			addAdj(now.e);
		}
		
		// 5. 절약할 수 있는 예산 출력
		if(groupCnt != N) {
			System.out.println(-1);
		}
		else {
			System.out.println(totalCost - cost);
		}
	}
	
	static void addAdj(int x) {
		for(Node node : adjArr[x]) {
			if(isVisit[node.e] == true) {
				continue;
			}
			pq.offer(node);
		}		
	}

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 건물, 도로의 개수 입력 받기
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 2. 도로 정보로 그래프 생성
		adjArr = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adjArr[i] = new ArrayList<>();
		}
		
		int s, e, c;
		totalCost = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			adjArr[s].add(new Node(e, c));
			adjArr[e].add(new Node(s, c));
			
			// 2-1. 총 비용 계산 
			totalCost += c;
		}
		
		pq = new PriorityQueue<>((a, b) -> a.c - b.c);
		isVisit = new boolean[N+1];
	}
}