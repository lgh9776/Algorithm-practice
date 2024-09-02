import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 벌통 크기, 선택 벌통 개수, 최대 채취 양 입력 받기
 * 3. 꿀 정보 입력 받기
 * 4. 시작점을 다르게 각 일꾼의 범위 설정 후 채취
 * 	4-1. 첫번째 일꾼 채취
 * 		4-1-1. 현재 범위에서 채취할 때 max 채취
 * 	4-2. 두번째 일꾼 채취
 * 		4-2-1. (같은 행) 두번째 일꾼은 첫번째 일꾼의 범위 다음 칸부터 시작
 * 		4-2-2. (다른 행) 다음 행 차례대로 순회
 * 		4-2-3. 해당 행의 (범위 내) 모든 칸을 시작점으로 범위 잡아서 순회
 * 	4-3. 각 일꾼의 최대 수익으로 기존 total 갱신
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int mapSize;
	static int choiceCnt;
	static int canMaxGet;
	static int[][] map;
	static boolean[] isVisited;
	
	static int maxCost;
	static int totalMaxCost;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();
			findMaxGet();
			
			sb.append(totalMaxCost).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. 벌통 크기, 선택 벌통 개수, 최대 채취 양 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		choiceCnt = Integer.parseInt(st.nextToken());
		canMaxGet = Integer.parseInt(st.nextToken());
		
		//3. 꿀 정보 입력 받기
		map = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void findMaxGet() {
		int total = Integer.MIN_VALUE;
		for (int row = 0; row < mapSize; row++) {
			//4. 시작점을 다르게 각 일꾼의 범위 설정 후 채취
			for (int col = 0; col <= mapSize-choiceCnt; col++) {		
				//4-1. 첫번째 일꾼 채취
				maxCost = Integer.MIN_VALUE;
				powerSet(row, col, 0, 0, 0);
				//4-1-1. 현재 범위에서 채취할 때 max 채취
				int firstMax = maxCost;
				
				//4-2. 두번째 일꾼 채취
				maxCost = Integer.MIN_VALUE;
				int secondMax = Integer.MIN_VALUE;
				
				//4-2-1. (같은 행) 두번째 일꾼은 첫번째 일꾼의 범위 다음 칸부터 시작
				for (int secondCol = col+choiceCnt; secondCol <= mapSize-choiceCnt; secondCol++) {
					powerSet(row, secondCol, 0, 0, 0);
					secondMax = Math.max(secondMax, maxCost);
				}
				
				//4-2-2. (다른 행) 다음 행 차례대로 순회
				for (int secondRow = row+1; secondRow < mapSize; secondRow++) { //다음행부터 모든 행 순회
					//4-2-3. 해당 행의 (범위 내) 모든 칸을 시작점으로 범위 잡아서 순회
					for (int secondCol = 0; secondCol <= mapSize-choiceCnt; secondCol++) { 
						powerSet(secondRow, secondCol, 0, 0, 0);
						secondMax = Math.max(secondMax, maxCost);
					}
				}
				
				//4-3. 각 일꾼의 최대 수익으로 total 갱신
				total = Math.max(total, firstMax + secondMax);
			}
		}
		totalMaxCost = total;
	}

	//해당 범위에서 선택할 수 있는 부분집합들
	static void powerSet(int row, int col, int selectIndex, int sum, int cost) {
		//(기저 조건) 채취할 벌꿀이 최대 채취 벌꿀보다 크면 return
		if(sum > canMaxGet)
			return;
		
		//부분집합이 완성되었으면
		if(choiceCnt == selectIndex) {
			//각 인덱스를 선택하여 계산한 수익과 maxCost 비교 후 갱신
			if(maxCost < cost) {
				maxCost = cost;
			}
			return;
		}
		
		//현재 칸 채취 후 다음 벌통으로
		powerSet(row, col+1, selectIndex+1, sum+map[row][col], cost + map[row][col] * map[row][col]); //col만 움직여주면 됨
		
		//현재 칸 채취x, 다음 벌통으로 
		powerSet(row, col+1, selectIndex+1, sum, cost);
	}
}