import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static class Pos{
		int x, y;
		
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static StringBuilder sb;
	static BufferedReader br;
	static StringTokenizer st;
	
	static int[] delta_x = {-1, 0, 1, 0};
	static int[] delta_y = {0, 1, 0, -1};
	
	static int[][] map;
	static boolean[][] isVisit;
	static int row, col, K;
	static int result;
	
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int testCase = Integer.parseInt(st.nextToken());
		
		for (int tc = 0; tc < testCase; tc++) {
			InputData();
			
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if(map[i][j] == 1 && isVisit[i][j] == false) {
						findCabbage(i, j);
						result++;
					}
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	static void InputData() throws IOException {
		st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		result = 0;
		
		map = new int[row][col];
		isVisit = new boolean[row][col];
		
		int x, y;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;
		}
	}

	static void findCabbage(int x, int y) {
		Deque<Pos> q = new ArrayDeque<Pos>();
		q.offer(new Pos(x, y));
		isVisit[x][y] = true;
		
		int nextX, nextY;
		while(!q.isEmpty()) {
			Pos now = q.poll();
			
			for (int i = 0; i < 4; i++) {
				nextX = now.x + delta_x[i];
				nextY = now.y + delta_y[i];
				
				if(nextX < 0 || nextX >= row || nextY < 0 || nextY >= col) {
					continue;
				}
				
				if(map[nextX][nextY] == 1 && isVisit[nextX][nextY] == false) {
					q.offer(new Pos(nextX, nextY));
					isVisit[nextX][nextY] = true;
				}
			}
		}
	}
}