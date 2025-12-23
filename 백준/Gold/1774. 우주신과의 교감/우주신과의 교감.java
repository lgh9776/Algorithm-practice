import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1. 우주신, 통로의 개수 입력 받기
 * 2. 각각의 좌표 입력 받기
 * 3. 이미 연결된 통로 입력 받아 pq에 넣기
 * 4. 각각을 시작과 끝으로 거리를 계산해서 pq에 넣기
 * 5. 공백큐가 될 때까지 반복
 * 	5-1. 큐에서 가장 작은 값 선택
 * 	5-2. 시작과 끝이 같은 그룹이면 pass
 * 	5-3. 같은 그룹에 포함시키고 거리 합산
 * 	5-4. 연결(간선) 카운팅
 * 	5-5. 연결된 수가 우주신 개수-1과 같으면 break
 * 6. 만들어야 할 최소 통로 길이 출력
 */

public class Main {
	
	static class Node{
		int s, e;
		long c;
		Node(int s, int e, long c){
			this.s = s;
			this.e = e;
			this.c = c;
		}
	}
	
	static class Pos{
		int x, y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int godCnt, pathCnt;
	static Pos[] locate;
	static int[] parent;
	static PriorityQueue<Node> pq;
	static double totalDist;
	static int connectCnt;

	public static void main(String[] args) throws IOException {
		inputData();
		
		// 5. 공백큐가 될 때까지 반복
		while(!pq.isEmpty()) {
			// 5-1. 큐에서 가장 작은 값 선택
			Node now = pq.poll();
			
			// 5-2. 시작과 끝이 같은 그룹이면 pass
			if(find(now.s) == find(now.e)) {
				continue;
			}
			
			// 5-3. 같은 그룹에 포함시키고 거리 합산
			union(now.s, now.e);
			double dist = Math.sqrt(now.c);
			totalDist += dist;
			
			// 5-4. 연결(간선) 카운팅
			connectCnt++;
			
			// 5-5. 연결된 수가 우주신 개수-1과 같으면 break
			if(connectCnt == godCnt-1) {
				break;
			}
		}
		
		// 6. 만들어야 할 최소 통로 길이 출력
		System.out.printf("%.2f", totalDist);
	}
	
	static int find(int x) {
		if(parent[x] == -1) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px == py) {
			return;
		}
		parent[py] = px;
	}

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 우주신, 통로의 개수 입력 받기
		st = new StringTokenizer(br.readLine());
		godCnt = Integer.parseInt(st.nextToken());
		pathCnt = Integer.parseInt(st.nextToken());
		
		// 2. 각각의 좌표 입력 받기
		locate = new Pos[godCnt+1];
		int posX, posY;
		for (int i = 1; i <= godCnt; i++) {
			st = new StringTokenizer(br.readLine());
			posX = Integer.parseInt(st.nextToken());
			posY = Integer.parseInt(st.nextToken());
			locate[i] = new Pos(posX, posY);
		}
		
		pq = new PriorityQueue<>((a, b) -> Long.compare(a.c, b.c));
		
		// 3. 이미 연결된 통로 입력 받아 pq에 넣기
		for (int i = 0; i < pathCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			pq.offer(new Node(start, end, 0));
		}
		
		// 4. 각각을 시작과 끝으로 거리를 계산해서 pq에 넣기
		for (int i = 1; i <= godCnt; i++) {
			for (int j = i+1; j <= godCnt; j++) {
				int calX = locate[i].x - locate[j].x;
				int calY = locate[i].y - locate[j].y;
				long dist = (long) (Math.pow(calX, 2) + Math.pow(calY, 2));
				pq.offer(new Node(i, j, dist));
			}
		}
		
		parent = new int[godCnt+1];
		Arrays.fill(parent, -1);
		totalDist = 0;
		connectCnt = 0;
	}
}