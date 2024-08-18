import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 1. 맵 사이즈 입력 받기
 * 2. 맵 정보 입력 받기
 * 3. (n, n)에 도착 시 이동방법 수 증가
 * 4. 오른쪽으로 이동해서 왔을 때, 계속 이동 가능한지?
 * 5. 아래쪽으로 이동됐을 때
 * 6. 대각선으로 이동됐을 때
 * 	6-1. 오른쪽을 거쳐서 이동
 * 	6-2. 아래를 거쳐서 이동
 * 7. 대각선 이동만 가능한 경우
 * 7-1. (오른쪽, 아래쪽 dfs 순회 후 대각선 고려) 오른,아래,대각 모두 벽이 아닐 때 대각선 이동
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int result = 0;
	static int mapSize;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		inputData();
		find(0, 1, 0);
		System.out.println(result);
	}

	static void inputData() throws IOException {
		//1. 맵 사이즈 입력 받기
		st = new StringTokenizer(br.readLine());
		mapSize = Integer.parseInt(st.nextToken());
		
		//2. 맵 정보 입력 받기
		map = new int[mapSize][mapSize];
		for(int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	//dir = 0(오른쪽), 1(아래), 2(대각선)
	static void find(int nowRow, int nowCol, int dir) {
		//3. (n, n)에 도착 시 이동방법 수 증가
		if(nowRow == mapSize - 1 && nowCol == mapSize - 1) {
			result++;
			return;
		}
		
		//4. 오른쪽으로 이동해서 왔을 때, 계속 이동 가능한지?
		//아래로 이동했다가 바로 오른쪽 이동은 못함
		if(dir == 0) {
			if(nowCol + 1 < mapSize && map[nowRow][nowCol + 1] == 0) {
				find(nowRow, nowCol + 1, 0);
			}
		}
		//5. 아래쪽으로 이동됐을 때
		else if(dir == 1) {
			if(nowRow+1 < mapSize && map[nowRow+1][nowCol] == 0) {
				find(nowRow+1, nowCol, 1);
			}
		}
		//6. 대각선으로 이동됐을 때
		else if(dir == 2) {
			//6-1. 오른쪽을 거쳐서 이동
			if(nowCol+1 < mapSize && map[nowRow][nowCol + 1] == 0) {
				find(nowRow, nowCol+1, 0);
			}
			
			//6-2. 아래를 거쳐서 이동
			if(nowRow+1 < mapSize && map[nowRow+1][nowCol] == 0) {
				find(nowRow+1, nowCol, 1);
			}
		}
		
		//7. 대각선 이동만 가능한 경우
		if(nowRow+1 < mapSize && nowCol+1 < mapSize) {
			//7-1. (오른쪽, 아래쪽 dfs 순회 후 대각선 고려) 오른,아래,대각 모두 벽이 아닐 때 대각선 이동
			//이전 방향에 어떤 방향으로 들어왔는지 상관없이 고려 -> row, col값 모든 델타 계산
			if(map[nowRow + 0][nowCol + 1] == 0 && map[nowRow + 1][nowCol + 0] == 0 && map[nowRow + 1][nowCol + 1] == 0) {
				find(nowRow + 1, nowCol + 1, 2);
			}
		}
	}
}