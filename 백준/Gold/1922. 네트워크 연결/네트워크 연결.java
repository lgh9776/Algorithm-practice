import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1. 컴퓨터, 선의 개수 입력 받기
 * 2. 간선의 비용 입력 받기
 * 3. 1번 컴퓨터를 기준으로 선택
 * 4. 1번 컴퓨터와 연결 가능한 컴퓨터들 간선 우선순위 큐에 추가
 * 5. 비용이 가장 적고 아직 연결되지 않은 컴퓨터 연결
 * 	5-1. 그룹에 포함, 총 비용 증가
 * 6. 해당 컴퓨터와 연결 가능한 컴퓨터들 간선 우선순위 큐에 추가
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
	
	static List<Node>[] adj;
	static boolean[] isConnect;
	static PriorityQueue<Node> pq;
	static int V, E;
	static long totalCost;
	
	public static void main(String[] args) throws IOException {
		inputData();
		
		// 3. 1번 컴퓨터를 기준으로 선택
		isConnect[1] = true;
		
		// 4. 1번 컴퓨터와 연결 가능한 컴퓨터들 간선 우선순위 큐에 추가
		addAdjNode(1);
		
		int connectCnt = 0;
		Node n;
		while(true) {
			// 5. 비용이 가장 적고 아직 연결되지 않은 컴퓨터 연결
			n = pq.poll();
			if(isConnect[n.e] == true) {
				continue;
			}
			
			// 5-1. 그룹에 포함, 총 비용 증가
			isConnect[n.e] = true;
			totalCost += n.c;
			connectCnt++;
			
			if(connectCnt == V-1) {
				break;
			}
			
			// 6. 해당 컴퓨터와 연결 가능한 컴퓨터들 간선 우선순위 큐에 추가
			addAdjNode(n.e);
		}
		
		System.out.println(totalCost);
	}

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 컴퓨터, 선의 개수 입력 받기
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		E = Integer.parseInt(st.nextToken());
		
		// 2. 간선의 비용 입력 받기
		adj = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}
		
		int n1, n2, cost;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			adj[n1].add(new Node(n2, cost));
			adj[n2].add(new Node(n1, cost));
		}
		
		isConnect = new boolean[V+1];
		pq = new PriorityQueue<>((a, b) -> a.c - b.c);
		totalCost = 0;
	}
	
	static void addAdjNode(int x) {
		for(Node n : adj[x]) {
			if(isConnect[n.e] == false) {
				pq.add(n);
			}
		}
	}
	
}