import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int mapRow, mapCol;
	static int[][] map;
	static boolean[][] isVisited;
	static int moveCnt;
	
	static int[] delta_x = {-1, 0, 1, 0};
	static int[] delta_y = {0, 1, 0, -1};
	
	static class Pos{
		int x, y;
		
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		input();
		
		bfs(0, 0);
		
		System.out.println(moveCnt);
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		mapRow = Integer.parseInt(st.nextToken());
		mapCol = Integer.parseInt(st.nextToken());
		
		map = new int[mapRow][mapCol];
		for (int row = 0; row < mapRow; row++) {
			st = new StringTokenizer(br.readLine());
			char[] numStr = st.nextToken().toCharArray();
			for (int col = 0; col < mapCol; col++) {
				map[row][col] = numStr[col] - '0';
			}
		}
		
		moveCnt = 1; //지나는 칸 수이기 때문에 시작점 포함
		isVisited = new boolean[mapRow][mapCol];
	}
	
	static void bfs(int x, int y) {
		Deque<Pos> q = new ArrayDeque<>();
		
		q.offer(new Pos(x, y));
		isVisited[x][y] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			moveCnt++;
			for (int i = 0; i < size; i++) {
				Pos now = q.poll();
				
				for (int index = 0; index < delta_x.length; index++) {
					int nextX = now.x + delta_x[index];
					int nextY = now.y + delta_y[index];
					
					if(nextX == mapRow-1 && nextY == mapCol-1) {
						return;
					}
					
					if(nextX < 0 || nextX >= mapRow || nextY < 0 || nextY >= mapCol) {
						continue;
					}
					
					if(isVisited[nextX][nextY]){
						continue;
					}
					
					if(map[nextX][nextY] == 1) {
						q.offer(new Pos(nextX, nextY));
						isVisited[nextX][nextY] = true;
					}
				}
			}
		}
	}
}