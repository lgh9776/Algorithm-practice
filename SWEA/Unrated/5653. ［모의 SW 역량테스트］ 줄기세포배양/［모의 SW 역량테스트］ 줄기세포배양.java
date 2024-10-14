import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 줄기세포 : 비활성 상태(0), 활성 상태(1), 죽은 상태(2), 생명력 수치, 배양 시간
 * 
 * 1. 테스트 케이스 입력 받기
 * 2. 줄기 세포가 분포된 초기 세로/가로 크기, 배양 시간 입력 받기
 * 3. 초기 맵 정보 입력 받기 (맵의 중앙에 들어가도록)
 * 
 * 4. 배양 시간 만큼 시뮬레이션 반복
 * 5. 죽지 않은 세포의 lifeTime 증가
 * 6. 죽지 않은 줄기세포가 있을 때
 * 	6-1. 비활성 상태이고 활성화 시간이 채워졌으면 활성 상태로 변경 후 pass
 * 	6-2. 해당 세포가 활성 상태인 경우
 * 		6-2-1. 4방으로 번식
 * 		6-2-2. 이미 세포가 있는 경우 pass
 * 		6-2-3. lifeTime이 0인 경우(동시에 번식) 생명력이 더 큰 것으로 번식
 * 	6-3. 생명력 시간만큼 활성되었으면 죽은 상태로 변경
 * 7. 비활성, 활성 상태인 세포들 수 출력
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int MAX_ROW = 360;
	static final int MAX_COL = 360;
	
	static final int DEACTIVE = 0;
	static final int ACTIVE = 1;
	static final int DEATH = 2;
	
	static int[] delta_row = {-1, 0, 1, 0};
	static int[] delta_col = {0, 1, 0, -1};
	
	static class Cell{
		int state;
		int lifePower;
		int lifeTime;
		
		public Cell(int lifePower) {
			this.state = 0;
			this.lifePower = lifePower;
			this.lifeTime = 0;
		}
	}
	
	static int initRow, initCol;
	static int totalTime;
	static Cell[][] map;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			inputData();
			
			//4. 배양 시간 만큼 시뮬레이션 반복
			for (int time = 0; time < totalTime; time++) {
				simulation();
			}
			
			//7. 비활성, 활성 상태인 세포들 수 출력
			int count = 0;
			for (int row = 0; row < MAX_ROW; row++) {
				for (int col = 0; col < MAX_COL; col++) {
					if(map[row][col] != null && map[row][col].state != DEATH)
						count++;
				}
			}
			
			sb.append(count).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. 줄기 세포가 분포된 초기 세로/가로 크기, 배양 시간 입력 받기
		st = new StringTokenizer(br.readLine());
		initRow = Integer.parseInt(st.nextToken());
		initCol = Integer.parseInt(st.nextToken());
		totalTime = Integer.parseInt(st.nextToken());
		
		//3. 초기 맵 정보 입력 받기 (맵의 중앙에 들어가도록)
		map = new Cell[MAX_ROW][MAX_COL];
		for (int row = MAX_ROW/2 - initRow/2; row < MAX_ROW/2 - initRow/2 + initRow; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = MAX_COL/2 - initCol/2; col < MAX_COL/2 - initCol/2 + initCol; col++) {
				int power = Integer.parseInt(st.nextToken());
				
				if(power == 0)
					map[row][col] = null;
				else if(power >= 1)
					map[row][col] = new Cell(power);
			}
		}
	}
	
	static void simulation() {
		//5. 죽지 않은 세포의 lifeTime 증가
		for (int row = 0; row < MAX_ROW; row++) {
			for (int col = 0; col < MAX_COL; col++) {
				if(map[row][col] == null)
					continue;
				
				if(map[row][col].state != DEATH)
					map[row][col].lifeTime++;
			}
		}
		
		for (int row = 0; row < MAX_ROW; row++) {
			for (int col = 0; col < MAX_COL; col++) {
				if(map[row][col] == null)
					continue;
				
				//6. 죽지 않은 줄기세포가 있을 때
				if(map[row][col].state != DEATH) {
					//6-1. 비활성 상태이고 활성화 시간이 채워졌으면 활성 상태로 변경 후 pass
					if(map[row][col].state == DEACTIVE && map[row][col].lifeTime == map[row][col].lifePower) {
						map[row][col].state = ACTIVE;
						continue;
					}
					
					//6-2. 해당 세포가 활성 상태인 경우
					if(map[row][col].state == ACTIVE) {
						//6-2-1. 4방으로 번식
						for (int index = 0; index < delta_col.length; index++) {
							Cell nowCell = map[row][col];
							int nextRow = row + delta_row[index];
							int nextCol = col + delta_col[index];
							
							//6-2-2. 이미 세포가 있는 경우 pass
							if(map[nextRow][nextCol] != null && map[nextRow][nextCol].lifeTime != 0)
								continue;
							
							//6-2-3. lifeTime이 0인 경우(동시에 번식) 생명력이 더 큰 것으로 번식
							if(map[nextRow][nextCol] != null && map[nextRow][nextCol].lifeTime == 0)
								map[nextRow][nextCol].lifePower = Math.max(nowCell.lifePower, map[nextRow][nextCol].lifePower);
							else if(map[nextRow][nextCol] == null)
								map[nextRow][nextCol] = new Cell(nowCell.lifePower);
							
						}
						
						//6-3. 생명력 시간만큼 활성되었으면 죽은 상태로 변경 
						if(map[row][col].lifeTime >= map[row][col].lifePower*2)
							map[row][col].state = DEATH;
						
					}
				}
			}
		}
	}
}