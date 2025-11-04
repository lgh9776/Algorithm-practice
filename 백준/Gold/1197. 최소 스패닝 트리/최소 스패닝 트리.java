import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 정점, 간선의 개수 입력 받기
 * 2. 간선 정보 입력 받기 (배열의 리스트)
 * 3. 임의의 정점을 1개를 트리에 추가
 * 4. 트리에 없고 해당 정점에서 갈 수 있는 정점을 연결하는 간선 우선순위 큐에 추가
 * 5. 우선순위 큐에서 비용이 가장 적은 간선 선택
 * 6. 해당 정점이 트리에 없으면 추가, 비용 증가
 * 7. 간선 수가 V-1개이면 종료
 * 8. 트리에 없고 해당 정점에서 갈 수 있는 정점을 연결하는 간선 우선순위 큐에 추가
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
	
	static List<Node>[] adjArr;
	static boolean[] isTree;
	static PriorityQueue<Node> pq;
	static int V, E;
	static long totalCost;

	public static void main(String[] args) throws IOException {
		inputData();
		
		// 3. 임의의 정점을 1개를 트리에 추가
		isTree[1] = true;
		
		// 4. 트리에 없고 해당 정점에서 갈 수 있는 정점을 연결하는 간선 우선순위 큐에 추가
		addAdjNode(1);
		
		int vCnt = 0;
		while(true) {
			// 5. 우선순위 큐에서 비용이 가장 적은 간선 선택
			Node n = pq.poll();
			
			// 6. 해당 정점이 트리에 없으면 추가, 비용, 간선 수 증가
			if(isTree[n.e] == true) {
				continue;
			}
			
			isTree[n.e] = true;
			totalCost += n.c;
			vCnt++;
			
			// 7. 간선 수가 V-1개이면 종료
			if(vCnt == V - 1) {
				break;
			}
			
			// 8. 트리에 없고 해당 정점에서 갈 수 있는 정점을 연결하는 간선 우선순위 큐에 추가
			addAdjNode(n.e);
		}
		
		System.out.println(totalCost);
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		// 1. 정점, 간선의 개수 입력 받기
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		// 2. 간선 정보 입력 받기 (배열의 리스트)
		adjArr = new ArrayList[V+1];
		for (int i = 1; i <= V; i++) {
			adjArr[i] = new ArrayList<>();
		}
		
		int n1, n2, c;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			adjArr[n1].add(new Node(n2, c));
			adjArr[n2].add(new Node(n1, c));
		}
		
		pq = new PriorityQueue<>((a, b) -> a.c - b.c);
		isTree = new boolean[V+1];
		totalCost = 0;
	}

	static void addAdjNode(int x) {
		for(Node n : adjArr[x]) {
			if(isTree[n.e] == false) {
				pq.add(n);
			}
		}
	}
	
}