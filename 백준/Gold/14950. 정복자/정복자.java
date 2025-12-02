import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1. 도시, 도로의 개수와 정복 시 증가 비용 입력 받기
 * 2. 도로 정보를 입력 받아 그래프 생성
 * 3. 1번 도시 점거
 * 4. 공백큐가 될 때까지 반복
 * 	4-1. 큐에서 비용이 가장 적은 것 선택
 * 	4-2. 해당 도시가 점거 됐으면 pass
 * 	4-3. 점거 표시 및 총 비용 갱신 (도로 비용 증가 계산)
 * 	4-4. 해당 도시에서 갈 수 있는 곳 큐에 넣기
 * 		4-4-1. 이미 점거한 도시면 pass
 * 5. 최소 비용 출력
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
	
	static int N, M, t;
	static List<Node>[] adjArr;
	static boolean[] isConquest;
	static PriorityQueue<Node> pq;
	static int conquestCnt;
	static int totalCost;

	public static void main(String[] args) throws IOException {
		inputData();
		
		// 3. 1번 도시 점거
		isConquest[1] = true;
		addAdjArr(1);
		
		// 4. 공백큐가 될 때까지 반복
		while(!pq.isEmpty()) {
			// 4-1. 큐에서 비용이 가장 적은 것 선택
			Node now = pq.poll();
			
			// 4-2. 해당 도시가 점거 됐으면 pass
			if(isConquest[now.e] == true) {
				continue;
			}
			
			// 4-3. 점거 표시 및 총 비용 갱신 (도로 비용 증가 계산)
			isConquest[now.e] = true;
			totalCost += now.c + (t * conquestCnt);
			conquestCnt++;
			
			if(conquestCnt + 1 >= N) { // conquestCnt에 1번 도시는 포함X이므로
				break;
			}
			
			// 4-4. 해당 도시에서 갈 수 있는 곳 큐에 넣기
			addAdjArr(now.e);
		}
		
		// 5. 최소 비용 출력
		System.out.println(totalCost);
	}
	
	static void addAdjArr(int x) {
		for(Node k : adjArr[x]) {			
			// 4-4-1. 이미 점거한 도시면 pass
			if(isConquest[k.e] == true) {
				continue;
			}
			pq.offer(k);
		}
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 도시, 도로의 개수와 정복 시 증가 비용 입력 받기
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		
		// 2. 도로 정보를 입력 받아 그래프 생성
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
			adjArr[e].add(new Node(s, c));			
		}
		
		isConquest = new boolean[N+1];
		pq = new PriorityQueue<>((a, b) -> a.c - b.c);
		conquestCnt = 0;
		totalCost = 0;
	}
}