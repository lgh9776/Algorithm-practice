import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1. 집, 길의 수 입력 받기
 * 2. 길의 정보 입력 받기
 * 3. 시작 집 큐에 넣기
 * 4. 공백큐가 될 때까지 반복
 * 	4-1. 비용이 가장 적은 도로 선택 (poll)
 * 	4-2. 트리에 포함된 집이면 pass
 * 	4-3. 트리에 포함 => 개수, 비용 증가
 * 	4-4. 트리에 포함된 집의 수가 총 집의 수와 같으면 break
 * 	4-5. 해당 집에서 갈 수 있는 노드 큐에 추가
 * 5. 전체 비용에서 최소 비용 뺀 것 출력
 */

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Node{
		int e, c;
		Node(int e, int c){
			this.e = e;
			this.c = c;
		}
	}
	
	static List<Node>[] adjArr;
	static boolean[] isVisit;
	static PriorityQueue<Node> pq;
	static int m, n;
	static int totalCost;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		while(inputData()) {
			// 3. 시작 집 큐에 넣기
			pq.offer(new Node(0, 0));
			
			// 4. 공백큐가 될 때까지 반복
			int cnt = 0, cost = 0;
			while(!pq.isEmpty()) {
				// 4-1. 비용이 가장 적은 도로 선택 (poll)
				Node now = pq.poll();
				
				// 4-2. 트리에 포함된 집이면 pass
				if(isVisit[now.e] == true) {
					continue;
				}
				
				// 4-3. 트리에 포함 => 개수, 비용 증가
				cnt++;
				cost += now.c;
				isVisit[now.e] = true;
				
				// 4-4. 트리에 포함된 집의 수가 총 집의 수와 같으면 break
				if(cnt == m) {
					break;
				}
				
				// 4-5. 해당 집에서 갈 수 있는 노드 큐에 추가
				addAdjNode(now.e);
			}
			
			// 5. 전체 비용에서 최소 비용 뺀 것 출력
			sb.append(totalCost - cost).append("\n");
		}

		System.out.println(sb);
	}
	
	static boolean inputData() throws IOException {
		// 1. 집, 길의 수 입력 받기
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		if(m == 0 && n == 0) {
			return false;
		}
		
		// 2. 길의 정보 입력 받기
		adjArr = new ArrayList[m];
		for (int i = 0; i < m; i++) {
			adjArr[i] = new ArrayList<>();
		}
		
		int s, e, c;
		totalCost = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adjArr[s].add(new Node(e, c));
			adjArr[e].add(new Node(s, c));
			totalCost += c;
		}
		
		isVisit = new boolean[m];
		pq = new PriorityQueue<>((a, b) -> a.c - b.c);
		return true;
	}
	
	static void addAdjNode(int x) {
		for(Node n : adjArr[x]) {
			if(isVisit[n.e] == true) {
				continue;
			}
			pq.offer(n);
		}
	}
}