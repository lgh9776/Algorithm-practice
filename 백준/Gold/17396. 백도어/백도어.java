import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1. 분기점 수, 길의 수 입력 받기
 * 2. 각 분기점의 시야 정보 입력 받기 (넥서스 제외)
 * 3. 길의 정보로 인접리스트 생성
 * 4. 출발점 설정 (0번)
 * 5. (다익스트라) 공백큐가 될 때까지 반복
 * 	5-1. 소요 시간이 가장 적은 것 poll
 * 	5-2. time 배열에 있는 값보다 크면 pass
 * 	5-3. end가 N-1일 경우 break
 * 	5-4. end에서 갈 수 있는 분기점 큐에 넣기
 * 		5-4-1. N-1 분기점 제외 시야 체크
 * 6. N-1까지 가는 최소 시간 출력
 */

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static long INF = Long.MAX_VALUE / 2;

	static class Node{
		int e;
		long t;
		Node(int e, long t){
			this.e = e;
			this.t = t;
		}
	}
	
	static List<Node>[] adjArr;
	static long[] time;
	static int[] isLight;
	static int N, M;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		inputData();
		
		// 4. 출발점 설정 (0번)
		pq.offer(new Node(0, 0));
		time[0] = 0;
		
		// 5. (다익스트라) 공백큐가 될 때까지 반복
		while(!pq.isEmpty()) {
			// 5-1. 소요 시간이 가장 적은 것 poll
			Node now = pq.poll();
			
			// 5-2. time 배열에 있는 값보다 크면 pass
			if(time[now.e] < now.t) {
				continue;
			}
			
			// 5-3. end가 N-1일 경우 break
			if(now.e == N-1) {
				break;
			}
			
			// 5-4. end에서 갈 수 있는 분기점 큐에 넣기
			addAdjNode(now.e);
		}
		
		// 6. N-1까지 가는 최소 시간 출력
		if(time[N-1] == INF) {
			System.out.println(-1);
		}
		else {
			System.out.println(time[N-1]);
		}
		
	}
	
	static void addAdjNode(int x) {
		for(Node node : adjArr[x]) {
			// 5-4-1. N-1 분기점 제외 시야 체크
			if(node.e != N-1 && isLight[node.e] == 1) {
				continue;
			}
			
			long byX = time[x] + node.t;
			if(byX < time[node.e]) {
				time[node.e] = byX;
				pq.offer(new Node(node.e, byX));
			}
		}
	}

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 분기점 수, 길의 수 입력 받기
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isLight = new int[N];
		
		// 2. 각 분기점의 시야 정보 입력 받기 (넥서스 제외)
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			isLight[i] = Integer.parseInt(st.nextToken());
		}
		
		// 3. 길의 정보로 인접리스트 생성
		adjArr = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjArr[i] = new ArrayList<>();
		}
		
		int s, e, t;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			adjArr[s].add(new Node(e, t));
			adjArr[e].add(new Node(s, t));
		}
		
		time = new long[N];
		Arrays.fill(time, INF);
		
		pq = new PriorityQueue<>((a, b) -> Long.compare(a.t, b.t));
	}
}