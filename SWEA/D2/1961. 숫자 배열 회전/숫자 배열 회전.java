import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 배열의 사이즈 입력 받기
 * 3. 숫자 입력 받기
 * 4. 90도씩 시계방향으로 회전
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int[][] map;
	static int[][] map90;
	static int[][] map180;
	static int[][] map270;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append("\n");
			
			//2. 배열의 사이즈 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			int size = Integer.parseInt(st.nextToken());
			map = new int[size][size];
			map90 = new int[size][size];
			map180 = new int[size][size];
			map270 = new int[size][size];
			
			//3. 숫자 입력 받기
			for(int row = 0; row < size; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int col = 0; col < size; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			////4. 90도씩 시계방향으로 회전
			turn(map, map90);
			turn(map90, map180);
			turn(map180, map270);
			
			//출력
			for(int row = 0; row < size; row++) {
				for(int col = 0; col < size; col++) {
					sb.append(map90[row][col]);
				}
				sb.append(" ");
				
				for(int col = 0; col < size; col++) {
					sb.append(map180[row][col]);
				}
				sb.append(" ");
				
				for(int col = 0; col < size; col++) {
					sb.append(map270[row][col]);
				}
				sb.append(" ");
				sb.append("\n");
			};
		}
		System.out.print(sb);
	}

	static void turn(int[][] turnMap, int[][] saveMap) {
		//시계방향으로 회전시켜주는 메소드
		for(int row = 0; row < turnMap.length; row++) {
			for(int col = 0; col < turnMap.length; col++) {
				saveMap[row][col] = turnMap[turnMap.length-col-1][row];
			}
		}
	}
}
