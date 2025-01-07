import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 맵 사이즈 입력 받기
 * 2. 맵 정보 입력 받기
 * 
 * 적록색약X
 * 3. 방문되지 않았고 맵을 벗어나지 않은 좌표 차례대로 방문
 * 	3-1. 그룹 수 증가
 * 4. (탈출조건) 해당 좌표가 범위를 벗어났을 경우
 * 5. (탈출조건) 이미 방문한 좌표일 경우
 * 6. (탈출조건) 기준 좌표의 색과 다를 경우
 * 7. 해당 좌표 방문 표시
 * 8. 해당 좌표의 상하좌우를 DFS로 넘김
 * 
 * 적록색약O
 * 9. 맵에 초록을 빨강으로 변경
 * 10. 방문되지 않았고 맵을 벗어나지 않은 좌표 차례대로 방문
 * 	10-1. 그룹 수 증가
 * 
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int mapSize;
	static String[][] map;
	static boolean[][] isVisit;
	
	static int basic;
	static int blindness;
	
	static String groupColor;
	
	static int[] delta_x = {0, 1, 0, -1};
	static int[] delta_y = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		input();
		
		//적록색약X
		//3. 방문되지 않았고 맵을 벗어나지 않은 좌표 차례대로 방문
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				if(!isVisit[row][col]) {
					//3-1. 그룹 수 증가
					basic++;
					
					groupColor = map[row][col];
					
					dfs(row, col);
				}
			}
		}
		
		sb.append(basic).append(" ");
		
		//9. 맵에 초록을 빨강으로 변경
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				if(map[row][col].equals("G")) {
					map[row][col] = "R";
				}
				
				isVisit[row][col] = false;
			}
		}
		
		//적록색약O
		//10. 방문되지 않았고 맵을 벗어나지 않은 좌표 차례대로 방문
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				if(!isVisit[row][col]) {
					//10-1. 그룹 수 증가
					blindness++;
					
					groupColor = map[row][col];
					
					dfs(row, col);
				}
			}
		}
		
		sb.append(blindness);
		System.out.println(sb);
	}

	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		//1. 맵 사이즈 입력 받기
		mapSize = Integer.parseInt(st.nextToken());
		
		//2. 맵 정보 입력 받기
		map = new String[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			map[row] = st.nextToken().split("");
		}
		
		isVisit = new boolean[mapSize][mapSize];
		basic = 0;
	}
	
	static void dfs(int x, int y) {
		//4. (탈출조건) 해당 좌표가 범위를 벗어났을 경우
		if(x < 0 || x >= mapSize || y < 0 || y >= mapSize){
			return;
		}
		
		//5. (탈출조건) 이미 방문한 좌표일 경우
		if(isVisit[x][y]) {
			return;
		}
		
		//6. (탈출조건) 기준 좌표의 색과 다를 경우
		if(!map[x][y].equals(groupColor)) {
			return;
		}
		
		//7. 해당 좌표 방문 표시
		isVisit[x][y] = true;
		
		//8. 해당 좌표의 상하좌우를 DFS로 넘김
		for (int index = 0; index < delta_x.length; index++) {
			int nextRow = x + delta_x[index];
			int nextCol = y + delta_y[index];
			
			if(nextRow < 0 || nextRow >= mapSize || nextCol < 0 || nextCol >= mapSize){
				continue;
			}
			
			dfs(nextRow, nextCol);
		}
	}
}