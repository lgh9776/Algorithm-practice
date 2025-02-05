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

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static long[][] map;
	static int row;
	static int col;
	static Pos[] start;
	static Pos[] end;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		inputData();
		
		long[] result = new long[cnt];
		for (int i = 0; i < cnt; i++) {
			int size = (end[i].x - start[i].x + 1) * (end[i].y - start[i].y + 1);
			result[i] = map[end[i].x][end[i].y] - map[end[i].x][start[i].y-1] - map[start[i].x-1][end[i].y] + map[start[i].x-1][start[i].y-1];
			sb.append(result[i]/size).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void inputData() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		cnt = Integer.parseInt(st.nextToken());
		
		map = new long[row+1][col+1];
		for (int i = 1; i <= row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= col; j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num + map[i-1][j] + map[i][j-1] - map[i-1][j-1];
			}
		}
		
		start = new Pos[cnt];
		end = new Pos[cnt];
		
		for (int i = 0; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			start[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			end[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
	}
}
