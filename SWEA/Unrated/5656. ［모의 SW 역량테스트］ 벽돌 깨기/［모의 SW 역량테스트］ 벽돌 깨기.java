import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
 * 1. 테스트 케이스 입력 받기
 * 2. 구슬 개수, 맵 크기 입력 받기
 * 3. 맵 정보 입력 받기
 * 4. mapCol 중 ballCnt만큼 뽑는 순열 구하기
 * 5. (기저조건) ballCnt만큼 뽑은 경우 
 * 	5-1. 뽑은 순서대로 해당 열에 구슬 쏘기
 * 	5-2. 처음으로 부숴질 벽돌이 1이면 그냥 부숨
 * 		5-2-1. 처음으로 부숴질 벽돌 좌표 큐에 넣기
 * 	5-3. 공백큐가 될 때까지 반복
 * 	5-4. 위력이 1이면 해당 벽돌만 부숨 (부순 벽돌 수 증가)
 * 	5-5. 위력이 1보다 크면 해당 벽돌의 위력만큼 4방으로 부수기 (부순 벽돌 수 증가)
 * 	5-6. 부수는 중 위력이 1보다 큰 벽이 있으면 큐에 넣기
 * 	5-7. 구슬 1개에 대한 결과를 얻으면 빈 공간 채우기
 * 		5-7-1. 마지막 행부터 남아있는 벽돌의 위력을 큐에 저장
 * 		5-7-2. 맵의 마지막 행부터 차례대로 큐에 있는 벽돌 저장 
 * 	5-8. 모든 구슬을 쏘면 많이 부숴진 벽돌 개수 갱신
 * 6. mapCol만큼 반복
 * 	6-1. 해당 인덱스 리스트에 추가 (구슬을 쏠 열의 인덱스)
 * 	6-2. 재귀호출
 * 	6-3. (원상복구) 마지막에 넣은 값 삭제
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Pos{
		int row, col;

		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
	static int totalWall;
	static int ballCnt;
	static int mapRow, mapCol;
	static int[][] map;
	static int[][] simulMap;
	static List<Integer> targetIndex;
	static int maxBreakCnt;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();
			
			//4. mapCol 중 ballCnt만큼 뽑는 순열 구하기
			findResult();
			
			//원래 총 벽돌에서 제일 많이 부순 케이스의 수를 빼면 가장 적게 남은 벽돌 수 출력 가능
			sb.append(totalWall - maxBreakCnt).append("\n");
		}
		System.out.print(sb);
	}

	static void inputData() throws IOException {
		//2. 구슬 개수, 맵 크기 입력 받기
		st = new StringTokenizer(br.readLine());
		ballCnt = Integer.parseInt(st.nextToken());
		mapCol = Integer.parseInt(st.nextToken());
		mapRow = Integer.parseInt(st.nextToken());
		
		//3. 맵 정보 입력 받기
		map = new int[mapRow][mapCol];
		simulMap = new int[mapRow][mapCol];
		totalWall = 0;
		for (int row = 0; row < mapRow; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapCol; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				
				if(map[row][col] >= 1) {
					totalWall++;
				}
			}
		}
		
		targetIndex = new ArrayList<>();
		maxBreakCnt = Integer.MIN_VALUE;
	}

	static void findResult() {
		//5. (기저조건) ballCnt만큼 뽑은 경우 
		if(targetIndex.size() == ballCnt) {
			//시뮬레이션 돌릴 맵
			for (int row = 0; row < mapRow; row++) {
				for (int col = 0; col < mapCol; col++) {
					simulMap[row][col] = map[row][col];
				}
			}
			shoot();
			return;
		}
		
		//6. mapCol만큼 반복
		for (int colIndex = 0; colIndex < mapCol; colIndex++) {
			//6-1. 해당 인덱스 리스트에 추가 (구슬을 쏠 열의 인덱스)
			targetIndex.add(colIndex);
			
			//6-2. 재귀호출
			findResult();
			
			//6-3. (원상복구) 마지막에 넣은 값 삭제
			targetIndex.remove(targetIndex.size()-1);
		}
	}
	
	static void shoot() {
		Queue<Pos> breakPos = new ArrayDeque<>();
		int breakCnt = 0;
		
		//5-1. 뽑은 순서대로 해당 열에 구슬 쏘기
		for (int index = 0; index < ballCnt; index++) {
			int target = targetIndex.get(index);
			for (int rowIndex = 0; rowIndex < mapRow; rowIndex++) {
				//5-2. 처음으로 부숴질 벽돌이 1이면 그냥 부숨
				if(simulMap[rowIndex][target] == 1) {
					simulMap[rowIndex][target] = 0;
					breakCnt++;
					break;
				}
				//5-2-1. 처음으로 부숴질 벽돌 좌표 큐에 넣기
				else if(simulMap[rowIndex][target] >= 2) {
					breakPos.offer(new Pos(rowIndex, target));
					break;
				}
			}
			
			//5-3. 공백큐가 될 때까지 반복
			while(!breakPos.isEmpty()) {
				Pos nowPos = breakPos.poll();
				
				//5-4. 위력이 1이면 해당 벽돌만 부숨 (부순 벽돌 수 증가)
				if(simulMap[nowPos.row][nowPos.col] == 1) {
					simulMap[nowPos.row][nowPos.col] = 0;
					breakCnt++;
				}
				//5-5. 위력이 1보다 크면 해당 벽돌의 위력만큼 4방으로 부수기 (부순 벽돌 수 증가)
				else if(simulMap[nowPos.row][nowPos.col] >= 2) {
					//위력만큼 4방으로 부수기
					for (int deltaIndex = 0; deltaIndex < deltaCol.length; deltaIndex++) {
						for (int power = 1; power <= simulMap[nowPos.row][nowPos.col]-1; power++) {
							int nextRow = nowPos.row + (deltaRow[deltaIndex] * power);
							int nextCol = nowPos.col + (deltaCol[deltaIndex] * power);
							
							//영역 체크
							if(nextRow < 0 || nextRow >= mapRow || nextCol < 0 || nextCol >= mapCol) {
								continue;
							}
							
							//벽이 없으면 pass
							if(simulMap[nextRow][nextCol] == 0) {
								continue;
							}
							
							//5-6. 부수는 중 위력이 1보다 큰 벽이 있으면 큐에 넣기
							if(simulMap[nextRow][nextCol] >= 2) {
								breakPos.offer(new Pos(nextRow, nextCol));
								continue;
							}
							
							simulMap[nextRow][nextCol] = 0;
							breakCnt++;
						}
					}
					//중심 벽돌 부수기
					simulMap[nowPos.row][nowPos.col] = 0;
					breakCnt++;
				}
			}
			
			//5-7. 구슬 1개에 대한 결과를 얻으면 빈 공간 채우기
			downWall();
			
			//5-8. 모든 구슬을 쏘면 많이 부숴진 벽돌 개수 갱신
			maxBreakCnt = Math.max(maxBreakCnt, breakCnt);
		}
	}
	
	static void downWall() {
		List<Integer> wall = new ArrayList<>();
		for (int col = 0; col < mapCol; col++) {
			//5-7-1. 마지막 행부터 남아있는 벽돌의 위력을 큐에 저장
			for (int index = mapRow-1; index >= 0; index--) {
				if(simulMap[index][col] >= 1) {
					wall.add(simulMap[index][col]);
					simulMap[index][col] = 0;
				}
			}
			
			//5-7-2. 맵의 마지막 행부터 차례대로 큐에 있는 벽돌 저장 
			for (int index = mapRow-1; index >= 0; index--) {
				if(wall.isEmpty()) {
					break;
				}
				simulMap[index][col] = wall.get(0);
				wall.remove(0);
			}
		}
	}
}