import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static class Pos{
		int x, y;
		
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int[] delta_x = {-1, 0, 1, 0};
	static int[] delta_y = {0, 1, 0, -1};
	
	static int[][] map;
	static boolean[][] isVisit;
	static int mapSize;
	static int maxHeight, minHeight;
	static int result;

	public static void main(String[] args) throws IOException {
		inputData();
		
		int count;
		for (int i = minHeight-1; i < maxHeight; i++) {
			isVisit = new boolean[mapSize][mapSize];
			count = 0;
			
			for (int j = 0; j < mapSize; j++) {
				for (int k = 0; k < mapSize; k++) {
					if(map[j][k] > i && !isVisit[j][k]) {
						findSafe(i, j, k);
						count++;
					}
				}
			}
			result = result < count ? count : result;
		}
		System.out.println(result);
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		mapSize = Integer.parseInt(st.nextToken());
		
		result = 0;
		maxHeight = Integer.MIN_VALUE;
		minHeight = Integer.MAX_VALUE;
		
		map = new int[mapSize][mapSize];
		for (int i = 0; i < mapSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < mapSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = maxHeight < map[i][j] ? map[i][j] : maxHeight;
				minHeight = minHeight > map[i][j] ? map[i][j] : minHeight;
			}
		}
	}
	
	static void findSafe(int depth, int x, int y) {
		ArrayDeque<Pos> q = new ArrayDeque<Pos>();
		q.offer(new Pos(x,y));
		isVisit[x][y] = true;
		
		while(!q.isEmpty()) {
			Pos now = q.poll();
			
			int nextX, nextY;
			for (int i = 0; i < 4; i++) {
				nextX = now.x + delta_x[i];
				nextY = now.y + delta_y[i];
				
				if(nextX < 0 || nextX >= mapSize || nextY < 0 || nextY >= mapSize) {
					continue;
				}
				
				if(map[nextX][nextY] > depth && !isVisit[nextX][nextY]) {
					q.offer(new Pos(nextX, nextY));
					isVisit[nextX][nextY] = true;
				}
			}
		}
	}
}