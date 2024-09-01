import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. mapSize 입력 받기
 * 3. map 정보 입력 받기
 * 	3-1. map의 가장자리를 제외한 부분에 있는 코어 좌표 저장
 * 4. 중복순열로 각 코어의 방향을 선택 해줌
 * 5. (기저조건) 코어 개수만큼 선택했으면
 * 	5-1. 전선 개수 계산
 * 	5-2. 제일 많은 코어 개수가 같으면 전선 길이 합이 적은 것으로 갱신
 * 	5-3. 제일 많은 코어 개수가 많은 것으로 전선 길이 합 갱신
 * 6. (기저조건) 남은 것을 다 연결해도 maxCore수 보다 적을 경우 return 
 * 7. 상하좌우로 나아갔을 때 현재 코어에서 연결할 수 있으면 연결
 * 7-1. 해당 방향으로 전선 연결
 * 7-2. 연결된 코어 카운트
 * 7-3. 다음 core 연결을 위해 재귀
 * 7-4. 원상 복구
 * 7-5. 아무 방향으로도 가지 못할 경우
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Pair{
		int row, col;

		public Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	//상(0), 하(1), 좌(2), 우(3)
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
	static int mapSize;
	static int[][] map;
	static List<Pair> cellLocate;
	
	static int connectPower;
	static int maxCore;
	static int minPowerLine;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();
			
			//4. 중복순열로 각 코어의 방향을 선택 해줌
			permutation(0);
			
			sb.append(minPowerLine).append("\n");
		}
		System.out.print(sb);
	}

	static void inputData() throws IOException {
		//2. mapSize 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		
		//3. map 정보 입력 받기
		map = new int[mapSize][mapSize];
		cellLocate = new ArrayList<>();
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				
				//3-1. map의 가장자리를 제외한 부분에 있는 코어 좌표 저장
				if(row != 0 && col != 0 && row != mapSize-1 && col != mapSize-1 && map[row][col] == 1) {
					cellLocate.add(new Pair(row, col));
				}
			}
		}
		
		maxCore = Integer.MIN_VALUE;
		minPowerLine = Integer.MAX_VALUE;
		connectPower = 0;
	}
	
	static void permutation(int selectIndex) {
		//5. (기저조건) 코어 개수만큼 선택했으면
		if(selectIndex == cellLocate.size()) {
			//5-1. 전선 개수 계산
			int totalPowerLine = 0;
			for (int row = 0; row < mapSize; row++) {
				for (int col = 0; col < mapSize; col++) {
					if(map[row][col] == 2) {
						totalPowerLine++;
					}
				}
			}
			
			//5-2. 제일 많은 코어 개수가 같으면 전선 길이 합이 적은 것으로 갱신
			if(maxCore == connectPower) {
				minPowerLine = Math.min(minPowerLine, totalPowerLine);
			}
			//5-3. 제일 많은 코어 개수가 많은 것으로 전선 길이 합 갱신
			else if(maxCore < connectPower) {
				maxCore = connectPower;
				minPowerLine = totalPowerLine;
			}		
			return;
		}
		
		//6. (기저조건) 남은 것을 다 연결해도 maxCore수 보다 적을 경우 return 
		if(connectPower + cellLocate.size() - selectIndex < maxCore)
			return;
		
		//7. 상하좌우로 나아갔을 때 현재 코어에서 연결할 수 있으면 연결
		if(canUp(selectIndex)) { //위
			//7-1. 해당 방향으로 전선 연결
			int nextRow = cellLocate.get(selectIndex).row;
			int nextCol = cellLocate.get(selectIndex).col;
			while(true) {
				nextRow += deltaRow[0];
				nextCol += deltaCol[0];
				
				if(nextRow < 0 || nextRow >= mapSize || nextCol < 0 || nextCol >= mapSize) {
					//7-2. 연결된 코어 카운트
					connectPower++;
					break;
				}
				map[nextRow][nextCol] = 2;
			}
			//7-3. 다음 core 연결을 위해 재귀
			permutation(selectIndex+1);
			
			//7-4. 원상 복구
			while(true) {
				nextRow -= deltaRow[0];
				nextCol -= deltaCol[0];
				
				if(map[nextRow][nextCol] == 1) {
					break;
				}
				map[nextRow][nextCol] = 0;
			}
			connectPower--;
		}
		
		if(canDown(selectIndex)) { //아래
			int nextRow = cellLocate.get(selectIndex).row;
			int nextCol = cellLocate.get(selectIndex).col;
			while(true) {
				nextRow += deltaRow[1];
				nextCol += deltaCol[1];
				
				if(nextRow < 0 || nextRow >= mapSize || nextCol < 0 || nextCol >= mapSize) {
					connectPower++;
					break;
				}
				map[nextRow][nextCol] = 2;
			}
			permutation(selectIndex+1);
			
			while(true) {
				nextRow -= deltaRow[1];
				nextCol -= deltaCol[1];
				
				if(map[nextRow][nextCol] == 1) {
					break;
				}
				map[nextRow][nextCol] = 0;
			}
			connectPower--;
		}
		
		if(canLeft(selectIndex)) { //왼
			int nextRow = cellLocate.get(selectIndex).row;
			int nextCol = cellLocate.get(selectIndex).col;
			while(true) {
				nextRow += deltaRow[2];
				nextCol += deltaCol[2];
				
				if(nextRow < 0 || nextRow >= mapSize || nextCol < 0 || nextCol >= mapSize) {
					connectPower++;
					break;
				}
				map[nextRow][nextCol] = 2;
			}
			permutation(selectIndex+1);
			
			while(true) {
				nextRow -= deltaRow[2];
				nextCol -= deltaCol[2];
				
				if(map[nextRow][nextCol] == 1) {
					break;
				}
				map[nextRow][nextCol] = 0;
			}
			connectPower--;
		}
		
		if(canRight(selectIndex)){ //오른
			int nextRow = cellLocate.get(selectIndex).row;
			int nextCol = cellLocate.get(selectIndex).col;
			while(true) {
				nextRow += deltaRow[3];
				nextCol += deltaCol[3];
				
				if(nextRow < 0 || nextRow >= mapSize || nextCol < 0 || nextCol >= mapSize) {
					connectPower++;
					break;
				}
				map[nextRow][nextCol] = 2;
			}
			permutation(selectIndex+1);
			
			while(true) {
				nextRow -= deltaRow[3];
				nextCol -= deltaCol[3];
				
				if(map[nextRow][nextCol] == 1) {
					break;
				}
				map[nextRow][nextCol] = 0;
			}
			connectPower--;
		}
		
		//7-5. 아무 방향으로도 가지 못할 경우
		permutation(selectIndex+1);
	}
	
	static boolean canUp(int index) {
		int nextRow = cellLocate.get(index).row;
		int nextCol = cellLocate.get(index).col;
		while(true) {
			nextRow += deltaRow[0];
			nextCol += deltaCol[0];
			
			if(nextRow < 0 || nextRow >= mapSize || nextCol < 0 || nextCol >= mapSize)
				break;
			
			if(map[nextRow][nextCol] == 1 || map[nextRow][nextCol] == 2)
				return false;
		}
		return true;
	}
	
	static boolean canDown(int index) {
		int nextRow = cellLocate.get(index).row;
		int nextCol = cellLocate.get(index).col;
		while(true) {
			nextRow += deltaRow[1];
			nextCol += deltaCol[1];
			
			if(nextRow < 0 || nextRow >= mapSize || nextCol < 0 || nextCol >= mapSize)
				break;
			
			if(map[nextRow][nextCol] == 1 || map[nextRow][nextCol] == 2)
				return false;
		}
		return true;
	}
	
	static boolean canLeft(int index) {
		int nextRow = cellLocate.get(index).row;
		int nextCol = cellLocate.get(index).col;
		while(true) {
			nextRow += deltaRow[2];
			nextCol += deltaCol[2];
			
			if(nextRow < 0 || nextRow >= mapSize || nextCol < 0 || nextCol >= mapSize)
				break;
			
			if(map[nextRow][nextCol] == 1 || map[nextRow][nextCol] == 2)
				return false;
		}
		return true;
	}
	
	static boolean canRight(int index) {
		int nextRow = cellLocate.get(index).row;
		int nextCol = cellLocate.get(index).col;
		while(true) {
			nextRow += deltaRow[3];
			nextCol += deltaCol[3];
			
			if(nextRow < 0 || nextRow >= mapSize || nextCol < 0 || nextCol >= mapSize)
				break;
			
			if(map[nextRow][nextCol] == 1 || map[nextRow][nextCol] == 2)
				return false;
		}
		return true;
	}
	
}