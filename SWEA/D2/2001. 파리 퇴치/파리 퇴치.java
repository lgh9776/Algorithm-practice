import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기 
 * 2. map 사이즈, 파리채 사이즈 입력 받기 (mapSize, toolSize)
 * 3. 파리 정보 입력 받기
 * 4. 각 칸에 대한 누적합을 구하여 저장한다 (sum)
 * 	4-1. map[row][col]의 누적합 = 
 * 		map[row-1][col] + map[row][col-1] - map[row-1][col-1] (공통부분)
 * 5. 누적합으로 가장 많이 죽일 수 있는 경우 구하기
 * 	5-1. map[toolSize-1][toolSize-1]부터 시작
 * 	5-2. 누적합으로 현재 위치에서 파리채 사이즈만큼 죽일 수 있는 파리 구하기
 * 	5-3. bestNum 갱신
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int[][] map;
	static int[][] sum;
	
	static int mapSize;
	static int toolSize;
	static int bestNum = 0;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기 
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			st = new StringTokenizer(br.readLine());
			//2. map 사이즈, 파리채 사이즈 입력 받기 (mapSize, toolSize)
			mapSize = Integer.parseInt(st.nextToken());
			toolSize = Integer.parseInt(st.nextToken());
			
			//3. 파리 정보 입력 받기
			map = new int[mapSize][mapSize];
			sum = new int[mapSize+1][mapSize+1];
			for(int row = 0; row < mapSize; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col = 0; col < mapSize; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			calSum();
			findBest();
			sb.append(bestNum).append("\n");
            bestNum = 0;
		}
		System.out.print(sb);
	}
	
	static void calSum() {
		//4. 각 칸에 대한 누적합을 구하여 저장한다 (sum)
		for(int row = 1; row <= mapSize; row++) {
			for(int col = 1; col <= mapSize; col++) {
				//4-1. map[row][col]의 누적합 구하기
				if(row - 1 >= 0 && col - 1 >= 0) {
					//현재 좌표값 + row, col 각각 이전까지의 누적합 - 공통부분
					sum[row][col] = map[row-1][col-1] + sum[row-1][col] + sum[row][col-1] - sum[row-1][col-1];
				}
			}
		}
	}
	
	static void findBest() {
		//5. 누적합으로 가장 많이 죽일 수 있는 경우 구하기
		int currentNum = 0;
		//5-1. map[toolSize-1][toolSize-1]부터 시작
		for(int row = toolSize; row <= mapSize; row++) {
			for(int col = toolSize; col <= mapSize; col++) {
				//5-2. 누적합으로 현재 위치에서 파리채 사이즈만큼 죽일 수 있는 파리 구하기
				if(row - toolSize >= 0 && col - toolSize >= 0) {
					//현재 위치의 누적합 - 파리채 위치를 제외한 row, col 각각의 누적합 + 공통부분
					currentNum = sum[row][col] - sum[row][col-toolSize] - sum[row-toolSize][col] + sum[row-toolSize][col-toolSize];

					//5-3. bestNum 갱신
					bestNum = Math.max(bestNum, currentNum);
				}
			}
		}
	}
}
