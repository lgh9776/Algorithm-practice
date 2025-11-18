import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. 건물, 도로의 개수 입력 받기
 * 2. 도로 정보 입력 받기
 * 3. 최적의 경로 찾기 - 비용 내림차순 정렬
 * 	3-1. 입구-1번 도로 체크
 * 	3-2. 리스트 순서대로 도로 선택
 * 4. 최악의 경로 찾기 - 비용 오름차순 정렬
 * 	4-1. 입구-1번 도로 체크
 * 	4-2. 리스트 순서대로 도로 선택
 * 5. 최악의 피로도 - 최적의 피로도 출력
 */

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	
	static class Node{
		int s, e, c;
		Node(int s, int e, int c){
			this.s = s;
			this.e = e;
			this.c = c;
		}
	}
	
	static int N, M;
	static int totalCnt;
	static List<Node> roads;
	static Node enter;
	static int[] p;
	
	public static void main(String[] args) throws IOException {
		inputData();

		// 3. 최적의 경로 찾기 - 비용 내림차순 정렬
		roads.sort((a, b) -> b.c - a.c);
		int goodUpCnt = findRoute();

		// 4. 최악의 경로 찾기 - 비용 오름차순 정렬
		roads.sort((a, b) -> a.c - b.c);
		int badUpCnt = findRoute();
		
		// 5. 최악의 피로도 - 최적의 피로도 출력
		System.out.println(badUpCnt * badUpCnt - goodUpCnt * goodUpCnt);
	}
	
	static int findRoute() {
		p = new int[N+1];
		Arrays.fill(p, -1);

		// -1. 입구-1번 도로 체크
		int cnt = enter.c == 1 ? 0 : 1;
		totalCnt = 1;
		
		// -2. 리스트 순서대로 도로 선택
		for(Node now : roads) {
			// 시작, 끝 건물이 같은 집합이면 pass
			if(union(now.s, now.e) == false) {
				continue;
			}
			
			// 오르막 판단, 연결된 건물 수 증가
			if(now.c == 0) {
				cnt++;
			}
			totalCnt++;
			
			// 모든 건물이 연결되면 break
			if(totalCnt == M) {
				break;
			}
		}
		
		return cnt;
	}
	
	static boolean union(int a, int b) {
		int ap = find(a);
		int bp = find(b);
		
		if(ap == bp) {
			return false;
		}
	
		p[bp] = ap;
		return true;
	}
	
	static int find(int x) {
		if(p[x] == -1) {
			return x;
		}
		return p[x] = find(p[x]);
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		// 1. 건물, 도로의 개수 입력 받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 2. 도로 정보 입력 받기
		int s, e, c;
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		enter = new Node(s, e, c);
		
		roads = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			roads.add(new Node(s, e, c));
		}
	}
}