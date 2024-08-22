import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. map 사이즈 입력 받기
 * 3. map에 저장할 숫자 입력 받기
 * 4. 0,0부터 모든 좌표를 시작점으로 다른 방으로 이동할 수 있는 횟수 구하기
 * 	4-1. 다른 좌표가 시작점일 때 지나간 좌표는 시작점 될 수 없음
 * 		 (경로가 똑같아서 다른 좌표에서 지나간거면 이동횟수가 더 작음)
 * 5. 현재 방이 존재하지 않으면 return
 * 6. 받은 방 번호가 현재 방 번호보다 1작지 않으면 return
 * 7. 받은 방 번호가 현재 방 번호보다 1크면
 * 	7-1. 이동횟수 증가
 * 	7-2. 해당 좌표를 기준으로 또 이동
 * 	7-3. 모두 이동이 불가능하면 이동 횟수와 시작 방 번호 갱신
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int mapSize;
	static int[][] map;
	static boolean[][] isNotStart; //해당 좌표를 시작점으로 하는지
	
	static int[] deltaRow = {-1, 0, 1, 0}; //상하좌우 이동 시 row에 더할값
	static int[] deltaCol = {0, 1, 0, -1}; //상하좌우 이동 시 col에 더할값
	
	static int startRoomNum;
	static int maxCount;
	static int maxStartRoomNum;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();		
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");

			inputData();
			
			//4. 0,0부터 모든 좌표를 시작점으로 다른 방으로 이동할 수 있는 횟수 구하기
			for (int row = 0; row < mapSize; row++) {
				for (int col = 0; col < mapSize; col++) {
					//4-1. 다른 좌표가 시작점일 때 지나간 좌표는 시작점 될 수 없음
					if(isNotStart[row][col] == true) {
						continue;
					}
					startRoomNum = map[row][col];
					findMoveCnt(row, col, map[row][col], 1); //방문한 방의 개수를 세는 것 같음..
				}
			}
			
			sb.append(maxStartRoomNum).append(" ").append(maxCount).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. map 사이즈 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		maxCount = Integer.MIN_VALUE;
		maxStartRoomNum = 0;
		
		//3. map에 저장할 숫자 입력 받기
		isNotStart = new boolean[mapSize][mapSize];
		map = new int[mapSize][mapSize];
		for(int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void findMoveCnt(int row, int col, int num, int count) { //현재 좌표 row, col, 이전 방 번호, 현재 이동 횟수
		//5. 현재 방이 존재하지 않으면 return
		if(row < 0 || col < 0 || row >= mapSize || col >= mapSize) {
			return;
		}
		
		//6. 받은 방 번호가 현재 방 번호와 같지 않으면return
		if(map[row][col] != num) {
			return;
		}
		//7. 받은 방 번호가 현재 방 번호와 같으면
		else if(map[row][col] == num) {
			isNotStart[row][col] = true;
			//7-1. 이동횟수 증가, 1 큰 방을 찾기 위해 num+1
			//7-2. 해당 좌표를 기준으로 또 이동 (상,하,좌,우)
			findMoveCnt(row-1, col, num+1, count+1);
			findMoveCnt(row+1, col, num+1, count+1);
			findMoveCnt(row, col-1, num+1, count+1);
			findMoveCnt(row, col+1, num+1, count+1);
			
			if(count > maxCount) {
				maxCount = count;
				maxStartRoomNum = startRoomNum; 
			}
			else if(count == maxCount) {
				maxStartRoomNum = Math.min(maxStartRoomNum, startRoomNum);
			}
		}
	}
}
