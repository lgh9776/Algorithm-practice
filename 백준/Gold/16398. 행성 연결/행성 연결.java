import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. 행성의 수 입력 받기
 * 2. 플로우 관리 비용 입력 받기
 * 3. 비용을 오름차순으로 정렬하기
 * 4. 비용이 작은 플로우부터 설치
 * 	4-1. 설치됐으면 연결 행성 수, 비용 증가
 * 	4-2. 모든 행성이 연결되면 반복문 탈출
 * 5. 총 비용 출력
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
	
	static int N;
	static int[] p;
	static List<Node> nodeList;
	static long totalCost;
	static int nodeCnt;
	
	public static void main(String[] args) throws IOException {
		inputData();
		
		// 4. 비용이 작은 플로우부터 설치
		boolean isUnion;
		for(Node now : nodeList) {
			// 4-1. 설치됐으면 연결 행성 수, 비용 증가
			isUnion = union(now.s, now.e);
			if(isUnion) {
				totalCost += now.c;
				nodeCnt++;
			}
			
			// 4-2. 모든 행성이 연결되면 반복문 탈출
			if(nodeCnt == N) {
				break;
			}
		}
		
		// 5. 총 비용 출력
		System.out.println(totalCost);
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		// 1. 행성의 수 입력 받기
		N = Integer.parseInt(st.nextToken());
	
		// 2. 플로우 관리 비용 입력 받기
		nodeList = new ArrayList<Node>();
		int c;
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N+1; j++) {
				if(i == j) {
					st.nextToken();
					continue;
				}
				c = Integer.parseInt(st.nextToken());
				nodeList.add(new Node(i, j, c));
			}
		}
		
		// 3. 비용을 오름차순으로 정렬하기
		nodeList.sort((a, b) -> a.c - b.c);
		
		p = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			p[i] = -1;
		}
		totalCost = 0;
		nodeCnt = 1; // 기본 행성 (첫 연결 시 루트)
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
}