import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/*
 * 1. 지도의 크기, 정보 입력 받기
 * 2. 모든 칸을 시작점으로 선택
 * 	2-1. 집이 없거나 방문된 곳은 pass
 * 	2-2. 해당 좌표 큐에 넣고 방문 표시
 * 	2-3. BFS 호출
 * 3. 공백큐가 될 때까지 반복
 * 	3-1. 큐에서 좌표 뽑고 집의 개수 증가
 * 	3-2. 해당 좌표에서 갈 수 있는 곳 체크
 * 		3-2-1. map 범위 체크
 * 		3-2-2. 집이 없거나 방문된 곳은 pass
 * 		3-2-3. 큐에 넣고 방문 표시
 * 4. 단지의 개수 증가, 집의 수 리스트에 추가
 * 5. 단지와 집의 개수 출력
 */

public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static class Pos{
		int row, col;
		Pos(int r, int c){
			row = r;
			col = c;
		}
	}
	
	static int[] delta_x = {-1, 0, 1, 0};
	static int[] delta_y = {0, 1, 0, -1};
	
	static int size;
	static int[][] map;
	static boolean[][] isVisit;
	static Queue<Pos> q;
	static int totalCnt;
	static List<Integer> houseCnt;

	public static void main(String[] args) throws IOException {
		inputData();
		
		// 2. 모든 칸을 시작점으로 선택
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				// 2-1. 집이 없거나 방문된 곳은 pass
				if(map[i][j] != 1 || isVisit[i][j] == true) {
					continue;
				}
				
				// 2-2. 해당 좌표 큐에 넣고 방문 표시
				q.offer(new Pos(i, j));
				isVisit[i][j] = true;
				
				// 2-3. BFS 호출
				int hCnt = bfs();
				
				// 4. 단지의 개수 증가, 집의 수 리스트에 추가
				totalCnt++;
				houseCnt.add(hCnt);
			}
		}
		
		// 5. 단지와 집의 개수 출력
		sb.append(totalCnt).append("\n");
		
		Collections.sort(houseCnt);
		for(int n : houseCnt) {
			sb.append(n).append("\n");
		}
		
		System.out.println(sb);
	}

	static int bfs() {
		int cnt = 0;
		
		// 3. 공백큐가 될 때까지 반복
		while(!q.isEmpty()) {
			// 3-1. 큐에서 좌표 뽑고 집의 개수 증가
			Pos p = q.poll();
			cnt++;
			
			// 3-2. 해당 좌표에서 갈 수 있는 곳 체크
			for (int i = 0; i < 4; i++) {
				int nextRow = p.row + delta_x[i];
				int nextCol = p.col + delta_y[i];
				
				// 3-2-1. map 범위 체크
				if(nextRow >= size || nextRow < 0 || nextCol >= size || nextCol < 0) {
					continue;
				}
				
				// 3-2-2. 집이 없거나 방문된 곳은 pass
				if(map[nextRow][nextCol] != 1 || isVisit[nextRow][nextCol] == true) {
					continue;
				}
					
				// 3-2-3. 큐에 넣고 방문 표시
				q.add(new Pos(nextRow, nextCol));
				isVisit[nextRow][nextCol] = true;
			}
		}
		
		return cnt;
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 지도의 크기, 정보 입력 받기
		size = Integer.parseInt(br.readLine());
		
		map = new int[size][size];
		for (int i = 0; i < size; i++) {
			String[] c = br.readLine().split("");
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(c[j]);
			}
		}
		
		isVisit = new boolean[size][size];
		q = new ArrayDeque<>();
		totalCnt = 0;
		houseCnt = new ArrayList<>();
	}
}