import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. map 사이즈(row, col), 맨홀뚜껑 좌표, 소요된 시간 입력 받기
 * 3. map 정보 입력 받기
 * 4. (bfs)
 * 	4-1. 맨홀 위치 큐에 넣고 방문 처리
 * 	4-2. 큐가 빌 때까지 반복
 * 	4-3. (해당 depth 종료 시) 시간 증가
 * 	4-4. 경과된 시간과 같아지면 return
 * 	4-5. 현재 큐의 사이즈만큼 반복 (빼고 추가되는 것에 영향 없게 저장해서 사용)
 * 		4-5-1. 큐에 있는 것 꺼내기 (좌표)
 * 		4-5-2. 해당 좌표의 파이프 종류 확인
 * 		4-5-3. 이동 가능한 방향 순회
 * 		4-5-4. 이동한 곳이 배열 범위 내에 있는지, 방문 하지 않은 것인지 확인
 * 		4-5-5. 빈 곳이면 pass
 * 		4-5-6. 이동한 곳에 있는 파이프 종류가 연결가능한 것인지 확인
 * 		4-5-7. 모두 만족 시 큐에 좌표 넣기, 방문 처리, 경우의 수 증가
 * 5. 경우의 수 출력
 *  
 * 이동할 수 있는 방향 델타 배열 생성
 * 해당 방향으로 이동 시 연결 가능한 파이프 종류 저장
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int PIPE_COUNT = 7;
	static final int DIR_COUNT = 4;
	
	static class Pos{
		int row, col;

		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	//상(0) 하(1) 좌(2) 우(3)
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
	//각 방향에서 연결할 수 있는 파이프
	static List<Integer>[] canConnect; 

	//각 파이프가 갈 수 있는 방향들
	static List<Integer>[] canDir;
	
	static int mapRow, mapCol;
	static Pos start;
	static int endTime;
	static int[][] map;
	static boolean[][] isVisit;
	
	static int canLocatedCnt;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();
			fixConnectDir();
			
			//4. (bfs)
			bfs();
			
			//5. 경우의 수 출력
			sb.append(canLocatedCnt).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. map 사이즈(row, col), 맨홀뚜껑 좌표, 소요된 시간 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		mapRow = Integer.parseInt(st.nextToken());
		mapCol = Integer.parseInt(st.nextToken());
		start = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		endTime = Integer.parseInt(st.nextToken());
		
		//3. map 정보 입력 받기
		map = new int[mapRow][mapCol];
		isVisit = new boolean[mapRow][mapCol];
		for (int row = 0; row < mapRow; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < mapCol; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		canLocatedCnt = 0;
	}

	static void fixConnectDir() {
		//각 방향에서 연결할 수 있는 파이프
		canConnect = new ArrayList[DIR_COUNT];
		canConnect[0] = new ArrayList<>(Arrays.asList(1, 2, 5, 6));
		canConnect[1] = new ArrayList<>(Arrays.asList(1, 2, 4, 7));
		canConnect[2] = new ArrayList<>(Arrays.asList(1, 3, 4, 5));
		canConnect[3] = new ArrayList<>(Arrays.asList(1, 3, 6, 7));
		
		//상(0) 하(1) 좌(2) 우(3)
		//각 파이프가 갈 수 있는 방향들
		canDir = new ArrayList[PIPE_COUNT+1];
		canDir[1] = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
		canDir[2] = new ArrayList<>(Arrays.asList(0, 1));
		canDir[3] = new ArrayList<>(Arrays.asList(2, 3));
		canDir[4] = new ArrayList<>(Arrays.asList(0, 3));
		canDir[5] = new ArrayList<>(Arrays.asList(1, 3));
		canDir[6] = new ArrayList<>(Arrays.asList(1, 2));
		canDir[7] = new ArrayList<>(Arrays.asList(0, 2));
	}
	
	static void bfs() {
		Deque<Pos> q = new ArrayDeque<>();
		int time = 0; //bfs의 depth
		
		//4-1. 맨홀 위치 큐에 넣고 방문 처리
		q.offer(start);
		isVisit[start.row][start.col] = true;
		canLocatedCnt++; //첫 위치 고려
		
		//4-2. 큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			//4-3. (해당 depth 종료 시) 시간 증가
			time++;
			
			//4-4. 경과된 시간과 같아지면 return
			if(time == endTime)
				return;
			
			int size = q.size();
			//4-5. 현재 큐의 사이즈만큼 반복 (빼고 추가되는 것에 영향 없게 저장해서 사용)
			for (int cnt = 0; cnt < size; cnt++) {
				//4-5-1. 큐에 있는 것 꺼내기 (좌표)
				Pos nowPos = q.poll();
				
				//4-5-2. 해당 좌표의 파이프 종류 확인
				int pipeIndex = map[nowPos.row][nowPos.col];
				
				//4-5-3. 이동 가능한 방향 순회				
				for(int dir : canDir[pipeIndex]) {
					int nextRow = nowPos.row + deltaRow[dir];
					int nextCol = nowPos.col + deltaCol[dir];
							
					//4-5-4. 이동한 곳이 배열 범위 내에 있는지, 방문 하지 않은 것인지 확인
					if(nextRow < 0 || nextCol < 0 || nextRow >= mapRow || nextCol >= mapCol || isVisit[nextRow][nextCol])
						continue;
					
					//4-5-5. 빈 곳이면 pass
					if(map[nextRow][nextCol] == 0)
						continue;
					
					//4-5-6. 이동한 곳에 있는 파이프 종류가 연결가능한 것인지 확인
					for(int pipeNum : canConnect[dir]) {
						if(map[nextRow][nextCol] == pipeNum) {
							//4-5-7. 모두 만족 시 큐에 좌표 넣기, 방문 처리, 경우의 수 증가
							q.offer(new Pos(nextRow, nextCol));
							isVisit[nextRow][nextCol] = true;
							canLocatedCnt++;
						}
					}
				}
			}
		}
	}
}