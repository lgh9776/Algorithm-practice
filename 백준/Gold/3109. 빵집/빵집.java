import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. mapRow, mapCol 입력받기
 * 2. map 정보 입력 받기
 * 3. 반복문에서 row를 증가하며 시작점 갱신 
 * 	3-1. isDone 초기화
 * 	3-2. 시작점을 현재 좌표로 재귀 호출
 * 4. (기저조건, 가지치기)
 * 	4-1. 현재 좌표가 map 범위를 넘어가면 return (이동 불가)
 * 	4-2. 현재 좌표가 건물이면 return (이동 불가)
 * 	4-3. 현재 좌표가 방문한 곳이면 return (이동 불가)
 * 	4-4. 현재 좌표가 mapCol-1과 같으면 (파이프 설치 끝)
 * 		4-4-1. 해당 좌표 방문 표시
 * 		4-4-2. isDone으로 파이프 설치 했음을 표시
 * 5. (재귀유도) 현재 좌표가 비어있으면 (이동 가능)
 * 	5-1. 해당 좌표 방문 표시
 * 	5-2. isDone으로 현재 시작점에서 끝을 찾았으면 return
 * 	5-3. 현재 좌표에서 다음으로 이동하도록 재귀 호출
 * 6. 마지막 열에 방문한 곳 개수 = 연결된 곳
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int mapRow, mapCol;
	static char[][] map;
	static boolean[][] isVisit;
	static boolean isDone;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		inputData();
		
		//3. 반복문에서 row를 증가하며 시작점 갱신
		for(int startRow = 0; startRow < mapRow; startRow++) {
			//3-1. isDone 초기화
			isDone = false;
			
			//3-2. 시작점을 현재 좌표로 재귀 호출
			 findPipeCnt(startRow, 0);
		}
		
		//6. 마지막 열에 방문한 곳 개수 = 연결된 곳
		int count = 0;
		for(int index = 0; index < mapRow; index++) {
			if(isVisit[index][mapCol-1] == true)
				count++;
		}
		
		System.out.println(count);
	}
	
	static void inputData() throws IOException {
		
		//1. mapRow, mapCol 입력받기
		st = new StringTokenizer(br.readLine().trim());
		mapRow = Integer.parseInt(st.nextToken());
		mapCol = Integer.parseInt(st.nextToken());
		
		//2. map 정보 입력 받기
		map = new char[mapRow][mapCol];
		isVisit = new boolean[mapRow][mapCol];
		for(int row = 0; row < mapRow; row++) {
			map[row] = br.readLine().trim().toCharArray();
		}
	}
	
	static void findPipeCnt(int row, int col) {
		//4. (기저조건, 가지치기)
		//4-1. 현재 좌표가 map 범위를 넘어가면 return (이동 불가)
		if(row < 0 || row >= mapRow || col < 0 || col >= mapCol)
			return;
		
		//4-2. 현재 좌표가 건물이면 return (이동 불가)
		if(map[row][col] == 'x')
			return;
		
		//4-3. 현재 좌표가 방문한 곳이면 return (이동 불가)
		if(isVisit[row][col] == true)
			return;
		
		//4-4. 현재 좌표가 mapCol-1과 같으면 (파이프 설치 끝)
		if(col == mapCol-1) {
			//4-4-1. 해당 좌표 방문 표시
			isVisit[row][col] = true;
			//4-4-2. isDone으로 파이프 설치 했음을 표시
			 isDone = true;
		}
		
		//5. (재귀유도) 현재 좌표가 비어있으면 (이동 가능)
		if(map[row][col] == '.') {
			//5-1. 해당 좌표 방문 표시
			isVisit[row][col] = true;
			
			//5-2. isDone으로 현재 시작점에서 끝을 찾았으면 return
			if(isDone)
				return;
			//5-3. 현재 좌표에서 다음으로 이동하도록 재귀 호출
			//오른쪽 위
			findPipeCnt(row-1, col+1);
			
			//오른쪽
			if(isDone)
				return;
			findPipeCnt(row, col+1);
			
			//오른쪽 아래
			if(isDone)
				return;
			findPipeCnt(row+1, col+1);
			
		}
	}
}
