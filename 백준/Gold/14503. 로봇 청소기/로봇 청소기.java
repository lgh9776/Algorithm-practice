import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 방의 크기 입력 받기 (roomRow, roomCol)
 * 2. 로봇 청소기의 첫 좌표 입력 받기 (robotX, robotY)
 * 3. 로봇 청소기의 첫 방향 입력 받기 (robotDir)
 * 4. 방의 상태 입력 받기 (room) //0 : 청소x, 1 : 벽, 2 : 청소됨
 * 5. 현재 위치가 청소되지 않은 상태면 청소
 * 6. 현재 로봇 위치 기준, 상하좌우 청소 안된 곳 검사
 * 7. 현재 위치 주변(상하좌우)이 모두 청소되었으면
 * 	7-1. 벽때문에 후진할 수 없으면 작동 정지
 * 	7-2. 바라보는 방향 유지, 1칸 후진
 * 8. 현재 위치 주변(상하좌우)에 청소되지 않은 칸이 있으면
 * 	8-1. 반시계 방향 90도 회전 (dir--)
 * 	8-2. 방향을 기준의 앞칸이 청소되지 않았으면 전진
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int roomRow; //방 세로 크기
	static int roomCol; //방 가로 크기
	static int robotX; //로봇 x좌표
	static int robotY; //로봇 y좌표
	static int robotDir; // 0:위, 1:오른, 2:아래, 3:왼
	static int cleanCount = 0;
	
	static int[][] room;
	static int[] deltaX = {-1, 0, 1, 0};
	static int[] deltaY = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		inputData();
		clean();
		System.out.println(cleanCount);
	}

	static void inputData() throws IOException {
		st = new StringTokenizer(br.readLine());
		//1. 방의 크기 입력 받기 (roomRow, roomCol)
		roomRow = Integer.parseInt(st.nextToken());
		roomCol = Integer.parseInt(st.nextToken());
		room = new int[roomRow][roomCol];
		
		//2. 로봇 청소기의 첫 좌표 입력 받기 (robotX, robotY)
		st = new StringTokenizer(br.readLine());
		robotX = Integer.parseInt(st.nextToken());
		robotY = Integer.parseInt(st.nextToken());
		
		//3. 로봇 청소기의 첫 방향 입력 받기 (robotDir)
		robotDir = Integer.parseInt(st.nextToken());
		
		//4. 방의 상태 입력 받기 (room) //0 : 청소x, 1 : 벽, 2 : 청소됨
		for(int row = 0; row < roomRow; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < roomCol; col++) {
				room[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void clean() {
		while(true) {
			//5. 현재 위치가 청소되지 않은 상태면 청소
			if(room[robotX][robotY] == 0) {
				room[robotX][robotY] = 2;
				cleanCount++;
			}
			
			//6. 현재 로봇 위치 기준, 상하좌우 청소 안된 곳 검사
			boolean isClean = true;
			for(int index = 0; index < deltaX.length; index++) {
				if(room[robotX + deltaX[index]][robotY + deltaY[index]] == 0) {
					isClean = false;
					break;
				}
			}
			
			//7. 현재 위치 주변(상하좌우)이 모두 청소되었으면
			if(isClean) {
				//7-1. 벽때문에 후진할 수 없으면 작동 정지
				if(room[robotX - deltaX[robotDir]][robotY - deltaY[robotDir]] == 1) {
					return;
				}
				
				//7-2. 바라보는 방향 유지, 1칸 후진
				robotX = robotX - deltaX[robotDir];
				robotY = robotY - deltaY[robotDir];
			}
			
			//8. 현재 위치 주변(상하좌우)에 청소되지 않은 칸이 있으면
			else if(!isClean) {
				//8-1. 반시계 방향 90도 회전 (dir--)
				robotDir = robotDir == 0 ? 3 : robotDir-1;
				
				//8-2. 방향을 기준의 앞칸이 청소되지 않았으면 전진
				if(room[robotX + deltaX[robotDir]][robotY + deltaY[robotDir]] == 0) {
					robotX = robotX + deltaX[robotDir];
					robotY = robotY + deltaY[robotDir];
				}
			}
		}
	}
}
