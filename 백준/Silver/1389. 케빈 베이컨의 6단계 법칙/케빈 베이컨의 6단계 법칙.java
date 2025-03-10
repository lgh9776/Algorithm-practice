import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int count;
	static int rel;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		inputData();

		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				for (int k = 0; k < count; k++) {
					if(map[j][i] > 0 && map[i][k] > 0) {
						if(map[j][k] == 0) {
							map[j][k] = map[j][i] + map[i][k];
						}
						else {
							map[j][k] = map[j][k] < map[j][i] + map[i][k] ? map[j][k] : map[j][i] + map[i][k];
						}
					}
				}
			}
		}
		
		int minIndex = -1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < count; i++) {
			int sum = 0;
			for (int j = 0; j < count; j++) {
				sum += map[i][j];
			}
			if(min > sum) {
				minIndex = i;
				min = sum;
			}
		}
		
		System.out.println(minIndex + 1);
	}

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		count = Integer.parseInt(st.nextToken());
		rel = Integer.parseInt(st.nextToken());
		
		map =  new int[count][count];
		for (int i = 0; i < rel; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			map[start][end] = 1;
			map[end][start] = 1;
		}
	}
}