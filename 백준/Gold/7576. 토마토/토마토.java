import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 1. 상자 사이즈 입력 받기
 * 2. 상자에 있는 토마토 정보 입력 받기
 * 3. BFS로  토마토 익히기
 * 4. 익은 토마토 위치를 큐에 넣기
 * 5. 큐의 크기만큼 반복
 * 6. 큐에서 위치를 꺼내 해당 토마토와 인접한 곳의 토마토 익히기
 * 7. 한번의 큐 사이즈만큼 반복이 돌았을 때 토마토가 익혀졌으면 날짜 증가 
 * 8. BFS 후에도 0(덜 익은 토마토)이 있으면 -1을 출력
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int[][] box;
	static boolean[][] isVisited;
	static int mapRow, mapCol;
	static int date;
	
	static int[] delta_x = {-1,0, 1, 0};
	static int[] delta_y = {0, 1, 0, -1};
	
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		input();
		
		//변화가 일어날 수 없는 경우 -1을 제외한 곳이 모두 1이면 0을 출력
		boolean isChange = false;
		for (int row = 0; row < mapRow; row++) {
			for (int col = 0; col < mapCol; col++) {
				if(box[row][col] == 0) {
					isChange = true;
				}
			}
		}
		
		if(!isChange) {
			System.out.println(0);
			return;
		}

		//3. BFS로  토마토 익히기
		bfs();
		
		//8. BFS 후에도 0(덜 익은 토마토)이 있으면 -1을 출력
		for (int row = 0; row < mapRow; row++) {
			for (int col = 0; col < mapCol; col++) {
				if(box[row][col] == 0) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(date);
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		//1. 상자 사이즈 입력 받기
		mapCol = Integer.parseInt(st.nextToken());
		mapRow = Integer.parseInt(st.nextToken());
		
		//2. 상자에 있는 토마토 정보 입력 받기
		box = new int[mapRow][mapCol];
		for (int row = 0; row < mapRow; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapCol; col++) {
				box[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		date = 0;
		isVisited = new boolean[mapRow][mapCol];
	}
	
	static void bfs() {
		//4. 익은 토마토 위치를 큐에 넣기
		Deque<Pos> q = new ArrayDeque<>();
		
		for (int row = 0; row < mapRow; row++) {
			for (int col = 0; col < mapCol; col++) {
				if(box[row][col] == 1) {
					q.offer(new Pos(row, col));
					isVisited[row][col] = true;
				}
			}
		}
		
		while(!q.isEmpty()) {
			int queueSize = q.size();
			boolean isChange = false;
			
			//5. 큐의 크기만큼 반복
			for (int size = 0; size < queueSize; size++) {
				//6. 큐에서 위치를 꺼내 해당 토마토와 인접한 곳의 토마토 익히기
				Pos now = q.poll();
				for (int index = 0; index < delta_x.length; index++) {
					int nextRow = now.x + delta_x[index];
					int nextCol = now.y + delta_y[index];
					
					//범위 체크
					if(nextRow < 0 || nextRow >= mapRow || nextCol < 0 || nextCol >= mapCol) {
						continue;
					}
					
					//방문 했던 곳이면 pass
					if(isVisited[nextRow][nextCol]){
						continue;
					}
					
					//안익은 토마토가 있는 경우 익히고 큐에 좌표 넣기
					if(box[nextRow][nextCol] == 0) {
						box[nextRow][nextCol] = 1;
						q.offer(new Pos(nextRow, nextCol));
						isVisited[nextRow][nextCol] = true;
						isChange = true;
					}
				}
			}
			
			//7. 한번의 큐 사이즈만큼 반복이 돌았을 때 토마토가 익혀졌으면 날짜 증가 
			//(토마토를 미리 익혀주기 때문에 마지막 날짜 계산을 하지 않도록 하기 위해 숙성이 일어난 날만 날짜 증가)
			if(isChange) {
				date++;
			}
		}
	}
}