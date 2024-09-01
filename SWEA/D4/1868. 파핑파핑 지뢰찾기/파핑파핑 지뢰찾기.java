import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 문제 요약
 * 1. 지뢰가 있는 칸 : 게임 오버
 * 2. 지뢰가 없는 칸 : 본인 주변 8칸에 대해 지뢰가 있는지 수 만큼 표시
 * 3. 주변에도 지뢰가 없는 경우 : 주변 8칸에도 폭탄 개수 표시
 * -> 갈때마다 계산x 미리 계산해놓고 풀기!
 * 
 * 풀이
 * 1. 테스트 케이스 받기
 * 2. mapSize 입력 받기
 * 3. map 정보 입력 받기
 * 4. 모든 자리에 대해 폭탄 수 계산하기
 * 	4-1. 현재 칸이 폭탄이면 계산 필요 없음 
 * 	4-2. 현재 칸이 폭탄이 아니면 주변 8칸에 있는 폭탄 개수 저장
 * 5. 0인 곳부터 클릭하기 위해 bfs로 처리
 * 	5-1. 시작 칸 큐에 넣기
 * 	5-2. 공백큐가 될 때까지 반복
 * 	5-3. 해당 위치의 값이 0이면 8방 방문 처리
 * 	5-4. 8방에 0인 것이 있으면 큐에 넣고 방문 처리
 * 6. 방문하지 않은 나머지 처리
 * 
 */


public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static class Pair {
		int row, col;

		public Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int[] deltaRow = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] deltaCol = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	static int mapSize;
	static int[][] map;
	static boolean[][] isVisited;
	static int clickCnt;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			inputData();
			
			//5. 0인 곳부터 클릭하기 위해 bfs로 처리
			for (int row = 0; row < mapSize; row++) {
				for (int col = 0; col < mapSize; col++) {
					if(!isVisited[row][col] && map[row][col] == 0) {
						click(new Pair(row, col));
						clickCnt++;
					}
				}
			}
			
			//6. 방문하지 않은 나머지 처리
			for (int row = 0; row < mapSize; row++) {
				for (int col = 0; col < mapSize; col++) {
					if(!isVisited[row][col] && map[row][col] >= 1) {
						isVisited[row][col] = true;
						clickCnt++;
					}
				}
			}
			
			sb.append(clickCnt).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. mapSize 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		
		//3. map 정보 입력 받기
		char[][] tempMap = new char[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			tempMap[row] = br.readLine().trim().toCharArray();
		}
		
		//4. 모든 자리에 대해 폭탄 수 계산하기
		map = new int[mapSize][mapSize];
		isVisited = new boolean[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				//4-1. 현재 칸이 폭탄이면 계산 필요 없음 
				if(tempMap[row][col] == '*') {
					map[row][col] = -1;
				}
				//4-2. 현재 칸이 폭탄이 아니면 주변 8칸에 있는 폭탄 개수 저장
				else {
					int count = 0;
					for (int index = 0; index < deltaRow.length; index++) {
						int aroundRow = row + deltaRow[index];
						int aroundCol = col + deltaCol[index];
						
						if(aroundRow >= 0 && aroundRow < mapSize && aroundCol >= 0 && aroundCol < mapSize) {
							if(tempMap[aroundRow][aroundCol] == '*') {
								count++;
							}
						}
					}
					map[row][col] = count;
				}
			}
		}
		clickCnt = 0;
	}

	static void click(Pair start) {
		Queue<Pair> q = new ArrayDeque<>();
		
		//5-1. 시작 칸 큐에 넣기
		q.offer(start);
		isVisited[start.row][start.col] = true;
		
		//5-2. 공백큐가 될 때까지 반복
		while(!q.isEmpty()) {
			Pair now = q.poll();
			
			//5-3. 해당 위치의 값이 0이면 8방 방문 처리
			if(map[now.row][now.col] == 0) {
				for (int index = 0; index < deltaCol.length; index++) {
					int aroundRow = now.row + deltaRow[index];
					int aroundCol = now.col + deltaCol[index];
					
					if(aroundRow >= 0 && aroundRow < mapSize && aroundCol >= 0 && aroundCol < mapSize) {
						if(!isVisited[aroundRow][aroundCol] && map[aroundRow][aroundCol] == 0) {
							//5-4. 8방에 0인 것이 있으면 큐에 넣고 방문 처리
							q.offer(new Pair(aroundRow, aroundCol));
						}
						isVisited[aroundRow][aroundCol] = true;
					}
				}
			}
		}
	}
}