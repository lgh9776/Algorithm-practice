import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. mapSize 입력 받기
 * 3. map 정보 입력 받기
 * 	3-1. 최대로 맛있는 정도 구하기
 * 4. 최대 맛있는 정도만큼 반복
 * 5. 해당 날짜에 맞는 값을 가진 것에 방문 표시
 * 6. visit배열 복사본 생성
 * 7. 모든 칸을 돌며 방문되지 않은 것들의 덩어리 구하기
 * 8. bfs로 방문되지 않은 인접한 것들 방문 표시
 * 	8-1. 첫 요소 큐에 넣기
 * 	8-2. 공백큐가 될 때까지 반복
 * 	8-3. 인접한 것 중 방문하지 않은 것 큐에 넣기
 * 9. 해당 day의 덩어리 수 증가
 * 10. 최대 치즈 덩어리 수 갱신
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	//상하좌우
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
	static class Pair{
		int row, col;

		public Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int maxCheese;
	static int maxDay;
	static int mapSize;
	static int[][] map;
	static boolean[][] isVisited;
	static boolean[][] tempVisit;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();
			
			//4. 최대 맛있는 정도만큼 반복
			for (int day = 0; day <= maxDay; day++) {
				//5. 해당 날짜에 맞는 값을 가진 것에 방문 표시
				for (int row = 0; row < mapSize; row++) {
					for (int col = 0; col < mapSize; col++) {
						if(map[row][col] == day) {
							isVisited[row][col] = true;
						}
					}
				}
				
				//6. visit배열 복사본 생성
				tempVisit = new boolean[mapSize][mapSize];
				for (int row = 0; row < mapSize; row++) {
					for (int col = 0; col < mapSize; col++) {
						tempVisit[row][col] = isVisited[row][col];
					}
				}
				
				//7. 모든 칸을 돌며 방문되지 않은 것들의 덩어리 구하기
				int restCheeseCnt = 0;
				for (int row = 0; row < mapSize; row++) {
					for (int col = 0; col < mapSize; col++) {
						if(tempVisit[row][col] == false) {
							//8. bfs로 방문되지 않은 인접한 것들 방문 표시
							bfs(row, col); 
							
							//9. 해당 day의 덩어리 수 증가
							restCheeseCnt++;
						}
					}
				}
				
				//10. 최대 치즈 덩어리 수 갱신
				maxCheese = Math.max(maxCheese, restCheeseCnt);
			}
			sb.append(maxCheese).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. mapSize 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		maxDay = Integer.MIN_VALUE;
		maxCheese = Integer.MIN_VALUE;
		
		//3. map 정보 입력 받기
		map = new int[mapSize][mapSize];
		isVisited = new boolean[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				//3-1. 최대로 맛있는 정도 구하기
				maxDay = Math.max(maxDay, map[row][col]);
			}
		}
	}

	static void bfs(int row, int col) {
		Deque<Pair> q = new ArrayDeque<>();
		
		//8-1. 첫 요소 큐에 넣기
		q.offer(new Pair(row, col));
		tempVisit[row][col] = true;
		
		Pair element;
		while(!q.isEmpty()) { //8-2. 공백큐가 될 때까지 반복
			element = q.poll();
			
			//8-3. 인접한 것 중 방문하지 않은 것 큐에 넣기
			for (int index = 0; index < deltaRow.length; index++) {
				int nextRow = element.row + deltaRow[index];
				int nextCol = element.col + deltaCol[index];
				
				if(nextRow >= 0 && nextRow < mapSize && nextCol >= 0 && nextCol < mapSize && tempVisit[nextRow][nextCol] == false) {
					tempVisit[nextRow][nextCol] = true;
					q.offer(new Pair(nextRow, nextCol));
				}
			}
		}
	}
}