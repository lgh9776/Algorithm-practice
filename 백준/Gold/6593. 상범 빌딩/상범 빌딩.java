import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 빌딩 정보가 있는지 판단
 * 2. 빌딩 정보 입력 받기
 * 	2-1. 시작 지점 좌표 저장
 * 3. 시작 지점부터 BFS 탐색
 * 	3-1. 동서남북상하 순으로 좌표 탐색
 * 	3-2. 방문했거나 범위 밖인 경우 제외
 * 	3-3. .인 경우 해당 좌표 큐에 넣고 방문 표시
 * 	3-4. E인 경우 시간 증가, 성공 표시 후 break
 * 	3-5. 시간 증가
 * 4. 결과 출력
 */

public class Main {
	
	static class Pos{
		int height, row, col;
		
		public Pos(int height, int row, int col) {
			this.height = height;
			this.row = row;
			this.col = col;
		}
	}

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int[] delta_col = {0, 0, 1, -1, 0, 0};
	static int[] delta_row = {1, -1, 0, 0, 0, 0};
	static int[] delta_height = {0, 0, 0, 0, 1, -1};
	
	static char[][][] map;
	static boolean[][][] isVisited;
	static int L, R, C;
	static Pos startPos;
	static int time;
	static boolean isSuccess;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		while(true) {
			// 1. 빌딩 정보가 있는지 판단
			st = new StringTokenizer(br.readLine());
			
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if(L == 0 && R == 0 && C == 0) {
				break;
			}
			
			// 2. 빌딩 정보 입력 받기
			inputBuilding();
			
			// 3. 시작 지점부터 BFS 탐색
			escape();
			
			// 4. 결과 출력
			if(isSuccess == true) {
				sb.append("Escaped in ").append(time).append(" minute(s).\n");
			}
			else {
				sb.append("Trapped!\n");
			}
		}
		System.out.println(sb);
	}
	
	static void inputBuilding() throws IOException {
		map = new char[L][R][C];
		isVisited = new boolean[L][R][C];
		startPos = null;
		
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < R; j++) {
				st = new StringTokenizer(br.readLine());
				map[i][j] = st.nextToken().toCharArray();
				
				// 2-1. 시작 지점 좌표 저장
				if(startPos != null) {
					continue;
				}
				
				for (int k = 0; k < C; k++) {
					if(map[i][j][k] == 'S') {
						startPos = new Pos(i, j, k);
						break;
					}
				}
				
			}
			br.readLine();
		}
		
		isSuccess = false;
		time = 0;
	}

	static void escape() {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(startPos);
		isVisited[startPos.height][startPos.row][startPos.col] = true;
		
		while(true) {
			int size = q.size();
			
			if(size == 0) {
				break;
			}
			
			for (int index = 0; index < size; index++) {
				Pos nowPos = q.poll();
				
				// 3-1. 동서남북상하 순으로 좌표 탐색
				for (int i = 0; i < delta_col.length; i++) {
					Pos nextPos = new Pos(nowPos.height + delta_height[i], nowPos.row + delta_row[i], nowPos.col + delta_col[i]);
					
					// 3-2. 방문했거나 범위 밖인 경우 제외
					if(nextPos.height < 0 || nextPos.height >= L || nextPos.row < 0 || nextPos.row >= R || nextPos.col < 0 || nextPos.col >= C) {
						continue;
					}
					
					if(isVisited[nextPos.height][nextPos.row][nextPos.col]) {
						continue;
					}
					
					// 3-3. .인 경우 해당 좌표 큐에 넣고 방문 표시
					if(map[nextPos.height][nextPos.row][nextPos.col] == '.') {
						q.offer(nextPos);
						isVisited[nextPos.height][nextPos.row][nextPos.col] = true;
					}
					
					// 3-4. E인 경우 시간 증가, 성공 표시 후 break
					if(map[nextPos.height][nextPos.row][nextPos.col] == 'E') {
						time++;
						isSuccess = true;
						return;
					}
				}
			}
			// 3-5. 시간 증가
			time++;
		}
	}
}