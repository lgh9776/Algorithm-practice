import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Pos{
		int x, y;
		
		public Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int[] delta_x = {-1, 0, 1, 0};
	static int[] delta_y = {0, 1, 0, -1};
	
	static int mapRow, mapCol;
	static int rectCnt;
	static int[][] map;
	static boolean[][] isVisited;
	static Pos[][] rectInfo; 
	static List<Integer> widths;
	
	public static void main(String[] args) throws IOException {
		
		inputData();
		rectCheck();
		
		for (int i = 0; i < mapRow; i++) {
			for (int j = 0; j < mapCol; j++) {
				if(map[i][j] == 0 && !isVisited[i][j]) {
					bfs(i, j);
				}
			}
		}
		
		sb.append(widths.size()).append("\n");
		Collections.sort(widths);
		for(int width : widths) {
			sb.append(width).append(" ");
		}
		System.out.println(sb);
	}

	static void inputData() throws IOException {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		mapCol = Integer.parseInt(st.nextToken());
		mapRow = Integer.parseInt(st.nextToken());
		rectCnt = Integer.parseInt(st.nextToken());

		rectInfo = new Pos[rectCnt][2];
		for (int i = 0; i < rectCnt; i++) {
			st = new StringTokenizer(br.readLine());
			rectInfo[i][0] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			rectInfo[i][1] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		map = new int[mapRow][mapCol];
		isVisited = new boolean[mapRow][mapCol];
		widths = new ArrayList<Integer>();
	}

	static void rectCheck() {
		for (int i = 0; i < rectCnt; i++) {
			for (int j = rectInfo[i][0].x; j < rectInfo[i][1].x; j++) {
				for (int k = rectInfo[i][0].y; k < rectInfo[i][1].y; k++) {
					if(isVisited[j][k]) {
						continue;
					}
					
					map[j][k] = 1;
					isVisited[j][k] = true;
				}
			}
		}
	}
	
	static void bfs(int x, int y) {
		Queue<Pos> q = new ArrayDeque<>();
		q.add(new Pos(x, y));
		isVisited[x][y] = true;
		
		int width = 1;
		while(!q.isEmpty()) {
			Pos now = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nextX = now.x + delta_x[i];
				int nextY = now.y + delta_y[i];
				
				if(nextX < 0 || nextX >= mapRow || nextY < 0 || nextY >= mapCol) {
					continue;
				}
				
				if(map[nextX][nextY] == 0 && !isVisited[nextX][nextY]) {
					q.add(new Pos(nextX, nextY));
					isVisited[nextX][nextY] = true;
					width++;
				}
			}
		}
		widths.add(width);
	}
}