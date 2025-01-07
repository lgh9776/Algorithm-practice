
/*
 * 1. 맵 사이즈 입력 받기
 * 2. 맵 정보 입력 받기
 * 3. (탈출조건) 범위를 벗어날 경우 
 *	3-1. 현재 이동한 칸 수로 최대 이동 칸 갱신 후 return
 * 4. (탈출조건) 이미 방문한 경우
 * 	4-1. 현재 이동한 칸 수로 최대 이동 칸 갱신 후 return
 * 5. (탈출조건) 이동한 곳의 알파벳이 지나온 알파벳인경우
 * 	5-1. 현재 이동한 칸 수로 최대 이동 칸 갱신 후 return
 * 6. 이동한 칸 수 증가
 * 7. 해당 칸 방문 표시
 * 8. 상우하좌로 이동하기
 * 	8-1. dfs로 다음 좌표 판단
 * 9. 이동한 칸 수, 방문 여부 원복
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int mapRow;
	static int mapCol;
	static char[][] map;
	static boolean[][] isVisit;
	
	static int[] visitAlpha;
	static int maxMoveCnt;
	static int moveCnt;
	
	static int[] delta_x = {-1, 0, 1, 0};
	static int[] delta_y = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		input();
		dfs(0, 0);
		System.out.println(maxMoveCnt);
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		//1. 맵 사이즈 입력 받기
		mapRow = Integer.parseInt(st.nextToken());
		mapCol = Integer.parseInt(st.nextToken());
		
		//2. 맵 정보 입력 받기
		map = new char[mapRow][mapCol];
		for (int row = 0; row < mapRow; row++) {
			st = new StringTokenizer(br.readLine());
			map[row] = st.nextToken().toCharArray();
		}
		
		isVisit = new boolean[mapRow][mapCol];
		visitAlpha = new int[26];
		maxMoveCnt = 0;
		moveCnt = 0;
	}

	
	static void dfs(int x, int y) {
		//3. (탈출조건) 범위를 벗어날 경우 
		if(x < 0 || x >= mapRow || y < 0 || y >= mapCol) {
			//3-1. 현재 이동한 칸 수로 최대 이동 칸 갱신 후 return
			maxMoveCnt = moveCnt > maxMoveCnt ? moveCnt : maxMoveCnt;
			return;
		}
		
		//4. (탈출조건) 이미 방문한 경우
		if(isVisit[x][y]) {
			//4-1. 현재 이동한 칸 수로 최대 이동 칸 갱신 후 return
			maxMoveCnt = moveCnt > maxMoveCnt ? moveCnt : maxMoveCnt;
			return;
		}
		
		//5. (탈출조건) 이동한 곳의 알파벳이 지나온 알파벳인경우
		int alphaIndex = (int)map[x][y] - 65;
		if(visitAlpha[alphaIndex] == 1) {
			//5-1. 현재 이동한 칸 수로 최대 이동 칸 갱신 후 return
			maxMoveCnt = moveCnt > maxMoveCnt ? moveCnt : maxMoveCnt;
			return;
		}
		
		//6. 이동한 칸 수 증가
		moveCnt++;
		
		//7. 해당 칸 방문 표시
		isVisit[x][y] = true;
		visitAlpha[alphaIndex] = 1;
		
		//8. 상우하좌로 이동하기
		for (int index = 0; index < delta_x.length; index++) {
			int nextRow = x + delta_x[index];
			int nextCol = y + delta_y[index];
			
			//8-1. dfs로 다음 좌표 판단
			dfs(nextRow, nextCol);
		}
		
		//9. 이동한 칸 수, 방문 여부 원복
		moveCnt--;
		isVisit[x][y] = false;
		visitAlpha[alphaIndex] = 0;
	}
}
