import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static StringBuilder sb;
	static BufferedReader br;
	static StringTokenizer st;
	
	static int[][] map;
	static int mapSize;
	static int problemCount;

	public static void main(String[] args) throws IOException {
		inputData();
		
		int x1, y1, x2, y2;
		int sum;
		for (int i = 0; i < problemCount; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			sum = map[x2][y2] - map[x2][y1-1] - map[x1-1][y2] + map[x1-1][y1-1];
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}
	
	static void inputData() throws IOException {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		mapSize = Integer.parseInt(st.nextToken());
		problemCount = Integer.parseInt(st.nextToken());
		
		map = new int[mapSize+1][mapSize+1];
		for (int i = 1; i <= mapSize; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= mapSize; j++) {
				map[i][j] = map[i-1][j] + map[i][j-1] - map[i-1][j-1] + Integer.parseInt(st.nextToken());
			}
		}
	}
}
