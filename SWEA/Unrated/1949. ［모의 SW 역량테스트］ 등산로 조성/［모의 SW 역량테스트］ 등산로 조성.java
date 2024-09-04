import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. mapSize, fixDepth 입력 받기
 * 3. map 정보 입력 받기
 * 	3-1. 입력 받으면서 가장 큰 값 갱신 시
 * 		3-1-1. 출발지 리스트 비우고 새로 저장
 * 	3-2. max값과 같으면 출발지 리스트에 좌표 추가
 * 4. 모든 출발지를 시작점으로 돌리기
 * 5. dfs : 복사본 map 사용해서 경로 찾기
 * 	5-1. 변경하지 않았다면 주변을 1~k씩 감소시켜 이동 가능한 곳 찾으면
 * 		5-1-1. 조건 만족 시 해당 인덱스 값 map에 저장
 * 		5-1-2. 재귀 전처리 후 재귀호출
 * 		5-1-3. 원상복구
 * 	5-2. (기저조건) 이동할 수 있는 방향이 하나도 없을 때
 * 	5-3. 반복문으로 상하좌우 확인
 * 		5-3-1. 이동할 인덱스 계산
 * 		5-3-2. 배열 범위 내, 방문하지 않음, 현재 값보다 작으면
 * 		5-3-3. 이동할 곳 방문 처리
 * 		5-3-4. 이동할 곳을 인자로 count 증가시켜 재귀 호출
 * 		5-3-5. (후처리) 방문 해제
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
	
	static int mapSize;
	static int fixDepth;
	static int[][] map;
	static int[][] originMap;
	static boolean[][] isVisit;
	static boolean isChange;
	
	static List<Pos> start;
	static Pos fixNow;
	static int maxLen;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();
			
			//4. 모든 출발지를 시작점으로 돌리기
			for (Pos startPos : start) {
				//5. dfs : 복사본 map 사용해서 경로 찾기
				isVisit[startPos.row][startPos.col] = true;
				dfs(startPos.row, startPos.col, 1); //길이에 시작점 포함
				isVisit[startPos.row][startPos.col] = false;
			}
			sb.append(maxLen).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. mapSize, fixDepth 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		fixDepth = Integer.parseInt(st.nextToken());
		maxLen = Integer.MIN_VALUE;
		
		//3. map 정보 입력 받기
		start = new ArrayList<>();
		map = new int[mapSize][mapSize];
		originMap = new int[mapSize][mapSize];
		isVisit = new boolean[mapSize][mapSize];
		int maxHeight = Integer.MIN_VALUE;
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				originMap[row][col] = map[row][col];
				
				//3-1. 입력 받으면서 가장 큰 값 갱신 시
				if(maxHeight < map[row][col]) {
					maxHeight = map[row][col];
					
					//3-1-1. 출발지 리스트 비우고 새로 저장
					start.clear();
					start.add(new Pos(row, col));
				}
				//3-2. max값과 같으면 출발지 리스트에 좌표 추가
				else if(maxHeight == map[row][col]) {
					start.add(new Pos(row, col));
				}
			}
		}
	}

	static void dfs(int row, int col, int len) {
		//5-1. 변경하지 않았다면
		//모든 것에 접근할 수 없을 때만 하면 깎아서 갈 수 있는 경우를 고려하지 못함
		//깎는 것부터 고려하고 깎을 수 없을 때 사방 접근 불가능 여부 판단
		if(!isChange) {
			for (int dir = 0; dir < deltaCol.length; dir++) {
				int nextRow = row + deltaRow[dir];
				int nextCol = col + deltaCol[dir];
				
				if(nextRow >= 0 && nextRow < mapSize && nextCol >= 0 && nextCol < mapSize && !isVisit[nextRow][nextCol]) {
					//주변을 1~k씩 감소시켜 이동 가능한 곳 찾으면
					for (int fixAmount = 1; fixAmount <= fixDepth ; fixAmount++) {
						//5-1-1. 조건 만족 시 해당 인덱스 값 map에 저장
						if(map[row][col] > map[nextRow][nextCol] - fixAmount) {
							map[nextRow][nextCol] = map[nextRow][nextCol] - fixAmount;
							//5-1-2. 재귀 전처리 후 재귀호출
							isVisit[nextRow][nextCol] = true;
							isChange = true;

							dfs(nextRow, nextCol, len+1);
							
							//5-1-3. 원상복구
							isVisit[nextRow][nextCol] = false;
							isChange = false;
							map[nextRow][nextCol] = originMap[nextRow][nextCol];
							break;
						}
					}
				}
			}
		}
		
		//5-2. (기저조건) 이동할 수 있는 방향이 하나도 없을 때
		if(!checkAdj(row, col)) {
			maxLen = Math.max(maxLen, len);
			return;
		}
		
		//5-3. 반복문으로 상하좌우 확인
		for (int dir = 0; dir < deltaCol.length; dir++) {
			//5-3-1. 이동할 인덱스 계산
			int nextRow = row + deltaRow[dir];
			int nextCol = col + deltaCol[dir];
			
			//5-3-2. 배열 범위 내, 방문하지 않음, 현재 값보다 작으면
			if(nextRow >= 0 && nextRow < mapSize && nextCol >= 0 && nextCol < mapSize
			&& !isVisit[nextRow][nextCol] && map[row][col] > map[nextRow][nextCol]) {
				//5-3-3. 이동할 곳 방문 처리
				isVisit[nextRow][nextCol] = true;
				
				//5-3-4. 이동할 곳을 인자로 count 증가시켜 재귀 호출
				dfs(nextRow, nextCol, len+1);
				
				//5-3-5. (후처리) 방문 해제
				isVisit[nextRow][nextCol] = false;
			}
		}
	}
	
	static boolean checkAdj(int row, int col) {
		for (int dir = 0; dir < deltaCol.length; dir++) {
			int nextRow = row + deltaRow[dir];
			int nextCol = col + deltaCol[dir];
			
			//배열 범위 내, 방문하지 않음, 현재 값보다 작으면 이동 가능
			if(nextRow >= 0 && nextRow < mapSize && nextCol >= 0 && nextCol < mapSize
			&& !isVisit[nextRow][nextCol] && map[row][col] > map[nextRow][nextCol])
				return true;
		}
		return false;
	}
}