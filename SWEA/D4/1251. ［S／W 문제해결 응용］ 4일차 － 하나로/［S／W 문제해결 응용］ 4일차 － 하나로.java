import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 섬의 개수 입력 받기 (vCnt)
 * 3. 섬의 정보 입력 받기 (좌표)
 * 4. 환경 부담 세율 입력 받기
 * 5. 아무 섬 우선순위 큐에 추가하기
 * 6. 공백 큐가 될 때까지 반복
 * 	6-1. 큐에서 섬 꺼내기 (가중치가 가장 낮은 것 꺼내짐)
 * 	6-2. 선택한 섬에 방문한적 없으면
 * 	6-3. 해당 섬 방문 처리
 * 	6-4. 총 세금에 해당 섬까지의 세금 더하기
 * 	6-5. 선택된 섬에서 방문하지 않은 다른 섬으로 갈 수 있게 우선순위 큐에 넣기
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static class Locate{
		int locate_x, locate_y;
		
		public Locate(int x) {
			locate_x = x;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int vertex;
		double weight;

		public Edge(int vertex, double weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	static int vCnt;
	static double tax;
	static Locate[] islands;
	static boolean[] isVisited;
	static double minTotalTax;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			inputDate();
			findMinTax();
			sb.append(Math.round(minTotalTax)).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputDate() throws IOException {
		//2. 섬의 개수 입력 받기 (vCnt)
		st = new StringTokenizer(br.readLine().trim());
		vCnt = Integer.parseInt(st.nextToken());
		isVisited = new boolean[vCnt];
		minTotalTax = 0;
		
		//3. 섬의 정보 입력 받기 (좌표)
		islands = new Locate[vCnt];
		st = new StringTokenizer(br.readLine().trim());
		for (int index = 0; index < vCnt; index++) { //x좌표
			islands[index] = new Locate(Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine().trim());
		for (int index = 0; index < vCnt; index++) { //y좌표
			islands[index].locate_y = Integer.parseInt(st.nextToken());
		}
		
		//4. 환경 부담 세율 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		tax = Double.parseDouble(st.nextToken());
	}
	
	static void findMinTax() {
		//5. 아무 섬 우선순위 큐에 추가하기
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0, 0));
		
		//6. 공백 큐가 될 때까지 반복
		while(!pq.isEmpty()) {
			//6-1. 큐에서 섬 꺼내기 (가중치가 가장 낮은 것 꺼내짐)
			Edge pickIsland = pq.poll();
			
			//6-2. 선택한 섬에 방문한적 없으면
			if(!isVisited[pickIsland.vertex]) {
				//6-3. 해당 섬 방문 처리
				isVisited[pickIsland.vertex] = true;
				
				//6-4. 총 세금에 해당 섬까지의 세금 더하기
				minTotalTax += pickIsland.weight;
				
				//6-5. 선택된 섬에서 방문하지 않은 다른 섬으로 갈 수 있게 우선순위 큐에 넣기
				for (int index = 0; index < vCnt; index++) {
					if(!isVisited[index]) {
						double xLen =  Math.pow(islands[pickIsland.vertex].locate_x - islands[index].locate_x, 2);
						double yLen =  Math.pow(islands[pickIsland.vertex].locate_y - islands[index].locate_y, 2);
						pq.offer(new Edge(index, (xLen + yLen) * tax));	
					}
				}	
			}
		}
	}
}