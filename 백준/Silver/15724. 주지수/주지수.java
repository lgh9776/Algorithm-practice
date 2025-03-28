import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Pos{
		int x, y;
		
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] map;
	static int mapRow, mapCol;
	static int areaCnt;
	static Pos[] areaFirst;
	static Pos[] areaSecond;
	
	public static void main(String[] args) throws IOException {
		
		inputData();
		
		long result = 0;
		for (int i = 0; i < areaCnt; i++) {
			result = map[areaSecond[i].x][areaSecond[i].y]
					- map[areaFirst[i].x-1][areaSecond[i].y]
					- map[areaSecond[i].x][areaFirst[i].y-1]
					+ map[areaFirst[i].x-1][areaFirst[i].y-1];
			sb.append(result).append("\n");
		}
		
		System.out.println(sb);
	}

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		mapRow = Integer.parseInt(st.nextToken());
		mapCol = Integer.parseInt(st.nextToken());
		
		map = new int[mapRow+1][mapCol+1];
		for (int i = 1; i <= mapRow; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= mapCol; j++) {
				map[i][j] = map[i-1][j] + map[i][j-1] - map[i-1][j-1] + Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		areaCnt = Integer.parseInt(st.nextToken());
		
		areaFirst = new Pos[areaCnt];
		areaSecond = new Pos[areaCnt];
		for (int i = 0; i < areaCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			areaFirst[i] = new Pos(n1, n2);
			
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			areaSecond[i] = new Pos(n1, n2);
		}
	}
}
