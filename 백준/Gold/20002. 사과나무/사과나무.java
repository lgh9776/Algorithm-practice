import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 크기, 이익 입력 받기
 * 	1-1. 1칸 중 최대값 구하기
 * 	1-2. 이익은 누적하여 더하기
 * 2. 1~N까지 크기의 각 정사각형 중 최대 이익 구하기 
 */

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;
	static int[][] map;
	static int max;
	
	public static void main(String[] args) throws IOException {
		// 1. 크기, 이익 입력 받기
		inputData();
		
		// 2. 1~N까지 크기의 각 정사각형 중 최대 이익 구하기
		int calValue = 0;
		for (int K = 2; K <= N; K++) {
			for (int i = K; i <= N; i++) {
				for (int j = K; j <= N; j++) {
					calValue = map[i][j] - map[i][j-K] - map[i-K][j] + map[i-K][j-K];
					max = max < calValue ? calValue : max;
				}
			}			
		}

		System.out.println(max);
	}

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		max = Integer.MIN_VALUE;
		
		map = new int[N+1][N+1];
		int inputValue;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				// 1-1. 1칸 중 최대값 구하기
				inputValue = Integer.parseInt(st.nextToken());
				max = max < inputValue ? inputValue : max;
				
				// 1-2. 이익은 누적하여 더하기
				map[i][j] = map[i-1][j] + map[i][j-1] - map[i-1][j-1] + inputValue;
			}
		}
	}
}