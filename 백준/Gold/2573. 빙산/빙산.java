import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int[] delta_x = {-1, 0, 1, 0};
	static int[] delta_y = {0, 1, 0, -1};
	
	static class Pos{
		int x, y;
		public Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int mapRow, mapCol;
	static int[][] map;
	static boolean[][] isVisited;
	static int year;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. map 크기, 정보 입력 받기
		inputData();
		
		int iceCnt = 0;
		while(true) {
			iceCnt = 0;
			
			// 2. BFS로 빙산 덩어리 개수 구하기
			for (int row = 1; row < mapRow; row++) {
				for (int col = 1; col < mapCol; col++) {
					// 0이 아니고 방문하지 않은 곳 bfs 탐색
					if(map[row][col] != 0 && !isVisited[row][col]) {
						bfs(row, col);
						
						// 5. 빙산 덩어리 수 증가
						iceCnt++;
					}
				}
			}
			
			// 6. 빙산 덩어리 수가 2이상이면 해당 년도 출력
			if(iceCnt >= 2 || iceCnt == 0) {
				break;
			}

			// 7. 시간 계산
			year++;
			
			// 8. (1,1)부터 빙하가 있는 칸의 상하좌우를 확인해서 녹이기
			meltIce(1, 1);

			// visit 배열 초기화
			for (int i = 0; i < mapRow; i++) {
				for (int j = 0; j < mapCol; j++) {
					isVisited[i][j] = false;
				}
			}
		}
		
		if(iceCnt == 0) {
			System.out.println(0);
		}
		else {
			System.out.println(year);
		}
	}

	static void inputData() throws IOException {
		st = new StringTokenizer(br.readLine());
		mapRow = Integer.parseInt(st.nextToken());
		mapCol = Integer.parseInt(st.nextToken());
		
		map = new int[mapRow][mapCol];
		for (int i = 0; i < mapRow; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < mapCol; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		isVisited = new boolean[mapRow][mapCol];
		year = 0;
	}
	
	
	static void meltIce(int x, int y) {
		int[][] calIce = new int[mapRow][mapCol];
		
		for (int row = x; row < mapRow; row++) {
			for (int col = y; col < mapCol; col++) {
				if(map[row][col] != 0) {
					for (int i = 0; i < 4; i++) {
						int nextRow = row+delta_x[i];
						int nextCol = col+delta_y[i];
						
						if(nextRow < 0 || nextRow >= mapRow || nextCol < 0 || nextCol >= mapCol) {
							continue;
						}
						
						if(map[nextRow][nextCol] == 0) {
							calIce[row][col]++;
						}
					}
				}
			}
		}
		
		for (int row = x; row < mapRow; row++) {
			for (int col = y; col < mapCol; col++) {
				map[row][col] = map[row][col] - calIce[row][col] < 0 ? 0 : map[row][col] - calIce[row][col];
			}
		}
	}

	
	static void bfs(int x, int y) {
		Queue<Pos> q = new ArrayDeque<Pos>();
		
		// 3. 시작 좌표 큐에 넣기
		q.add(new Pos(x, y));
		
		// 4. 공백큐가 될 때까지 반복
		while(!q.isEmpty()) {
			// 4-1. 큐의 첫번째 좌표 꺼내기
			Pos nowPos = q.poll();
			
			// 4-2. 해당 좌표의 인접한 곳에 빙산이 있으면 좌표 큐에 넣기
			for (int i = 0; i < 4; i++) {
				int nextRow = nowPos.x + delta_x[i];
				int nextCol = nowPos.y + delta_y[i];
				
				if(nextRow < 0 || nextRow >= mapRow || nextCol < 0 || nextCol >= mapCol) {
					continue;
				}
				
				// 4-3. 큐에 넣은 곳 방문 표시
				if(map[nextRow][nextCol] != 0 && !isVisited[nextRow][nextCol]) {
					q.offer(new Pos(nextRow, nextCol));
					isVisited[nextRow][nextCol] = true;
				}
			}
		}
	}
}