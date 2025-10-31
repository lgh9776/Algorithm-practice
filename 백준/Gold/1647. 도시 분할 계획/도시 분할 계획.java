import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. 집, 길의 개수 입력 받기
 * 2. 길 정보(시작, 끝, 비용) 입력 받기
 * 3. 비용이 작은순으로 정렬
 * 4. 그룹에 속한 집의 수가 전체 집 수보다 1개 작을 때 멈춤 -> 집이 2개 있는 경우 연결X
 * 5. 비용이 작은 길부터 마을에 포함
 * 6. 포함되지 않은 집이면 추가
 * 7. 모든 집을 포함하면 비용 출력
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static class Node{
		int s, e, cost;
		Node(int s, int e, int c) {
			this.s = s;
			this.e = e;
			cost = c;
		}
	}
	
	static int N, M;
	static int[] p;
	static List<Node> roads;
	static int totalCost, houseCnt;
	
	public static void main(String[] args) throws IOException {
		inputData();
		
		for (int i = 0; i < roads.size(); i++) {
			// 4. 그룹에 속한 집의 수가 전체 집 수보다 1개 작을 때 멈춤 -> 집이 2개 있는 경우 연결X
			if(houseCnt == N - 1) {
				break;
			}
			
			// 5. 비용이 작은 길부터 마을에 포함
			Node now = roads.get(i);
		
			// 6. 포함되지 않은 집이면 추가
			int sp = find(now.s);
			int ep = find(now.e);
			
			if(sp == ep) {
				continue;
			}
			
			union(sp, ep);
			totalCost += now.cost;
			houseCnt++;	
		}

		// 7. 모든 집을 포함하면 비용 출력
		System.out.println(totalCost);
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		// 1. 집, 길의 개수 입력 받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 2. 길 정보(시작, 끝, 비용) 입력 받기
		roads = new ArrayList<Node>();
		int s, e, c;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			roads.add(new Node(s, e, c));
		}
		
		// 3. 비용이 작은순으로 정렬
		roads.sort((a, b) -> a.cost - b.cost);
		
		p = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			p[i] = -1;
		}
		totalCost = 0;
		houseCnt = 1; // 루트집 미리 포함
	}

	static void union(int a, int b) {
		int ap = find(a);
		int bp = find(b);
		
		if(ap == bp) {
			return;
		}
		p[bp] = ap;
	}
	
	static int find(int x) {
		if(p[x] == -1) {
			return x;
		}
		return p[x] = find(p[x]);
	}
	
}