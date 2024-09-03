import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. mapSize 입력 받기
 * 2. map 정보 입력 받기
 * 3. start, end 좌표 저장
 * 4. 다익스트라 알고리즘을 사용해 최소 비용 구하기
 * 	4-1. 시작점에서 해당 칸까지의 최소비용을 저장할 배열 초기화
 * 	4-2. 시작점 최소비용 = 시작점 값 (해당 인덱스 포함해서 계산), 우선순위 큐에 시작점 노드 넣기
 * 	4-3. 공백큐가 될 때까지 반복
 * 		4-3-1. 이미 방문된 상태면  pass
 * 		4-3-2. 방문되지 않았으면 방문 처리
 * 		4-3-3. 도착점에 도착하면 해당 cost 반환
 * 		4-3-4. 인접한 칸 방문 (상하좌우가 인접한 칸)
 * 		4-3-5. 배열 범위를 벗어나면 pass
 * 		4-3-6. 방문하지 않은 인접 칸이 현재 칸을 경유해서 갈 때 비용이 더 적으면 갱신 후 큐에 추가
 *  
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int INF = Integer.MAX_VALUE;
	
	static class Node{
		int row, col, cost;

		public Node(int row, int col, int cost) {
			this.row = row;
			this.col = col;
			this.cost = cost;
		}
	}
	
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
	static Node start;
	static Node end;
	
	static int mapSize = -1;
	static int[][] map;
	static boolean[][] isVisit;
	static int[][] minCost;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int test_case = 1;
		while(true) {
			//1. mapSize 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			mapSize = Integer.parseInt(st.nextToken());
			
			if(mapSize == 0)
				break;
			
			inputData();
			
			//4. 다익스트라 알고리즘을 사용해 최소 비용 구하기
			int result = dijkstra();
			sb.append("Problem ").append(test_case).append(": ").append(result).append("\n");
			test_case++;
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. map 정보 입력 받기
		map = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		//3. start, end 좌표 저장
		start = new Node(0, 0, map[0][0]); //시작하는 곳도 더해줘야해서 시작점 값 저장
		end = new Node(mapSize-1, mapSize-1, 0);
		
		isVisit = new boolean[mapSize][mapSize];
		minCost = new int[mapSize][mapSize];
	}
	
	static int dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		
		//4-1. 시작점에서 해당 칸까지의 최소비용을 저장할 배열 초기화
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				minCost[row][col] = INF;
			}
		}
		
		//4-2. 시작점 최소비용 = 시작점 값 (해당 인덱스 포함해서 계산), 우선순위 큐에 시작점 노드 넣기
		minCost[start.row][start.col] = map[0][0];
		pq.offer(new Node(start.row, start.col, minCost[start.row][start.col]));
		
		//4-3. 공백큐가 될 때까지 반복
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			//4-3-1. 이미 방문된 상태면  pass
			if(isVisit[now.row][now.col])
				continue;
			
			//4-3-2. 방문되지 않았으면 방문 처리
			isVisit[now.row][now.col] = true;
			
			//4-3-3. 도착점에 도착하면 해당 cost 반환
			if(now.row == end.row && now.col == end.col)
				return now.cost;
			
			//4-3-4. 인접한 칸 방문 (상하좌우가 인접한 칸)
			for (int dir = 0; dir < deltaRow.length; dir++) {
				int nextRow = now.row + deltaRow[dir];
				int nextCol = now.col + deltaCol[dir];
				
				//4-3-5. 배열 범위를 벗어나면 pass
				if(nextRow < 0 || nextRow >= mapSize || nextCol < 0 || nextCol >= mapSize)
					continue;
				
				//4-3-6. 방문하지 않은 인접 칸이 현재 칸을 경유해서 갈 때 비용이 더 적으면 갱신 후 큐에 추가
				if(!isVisit[nextRow][nextCol] && minCost[nextRow][nextCol] > now.cost + map[nextRow][nextCol]) {
					minCost[nextRow][nextCol] = now.cost + map[nextRow][nextCol];
					pq.offer(new Node(nextRow, nextCol, minCost[nextRow][nextCol]));
				}
			}
		}		
		return -1;
	}
	
}