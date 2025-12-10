import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 사다리 수, 뱀의 수 입력 받기
 * 2. 사다리와 뱀 표시
 * 3. 시작점 큐에 넣기
 * 4. 100에 도달할 때까지 반복
 * 5. 현재 큐 사이즈만큼 반복
 * 	5-1. 이동한 곳이 100이면 break
 * 	5-2. 이동한 곳에 사다리 or 뱀이 있으면 이동
 * 	5-3. 방문한 곳은 pass
 * 	5-4. 다음 지점 큐에 넣기
 */

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N, M;
	static int[][] map;
	static boolean[][] isVisit;
	static int count;
	
	public static void main(String[] args) throws IOException {
		inputData();
		
		// 3. 시작점 큐에 넣기
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);
		
		// 4. 100에 도달할 때까지 반복
		int size;
		boolean isArrive = false;
		while(!isArrive) {
			count++;
			
			// 5. 현재 큐 사이즈만큼 반복
			size = q.size();
			for (int i = 0; i < size; i++) {
				int index = q.poll();
				
				for (int j = 1; j <= 6; j++) {
					int next = index + j;
					// 5-1. 이동한 곳이 100이면 break
					if(next == 100) {
						isArrive = true;
						break;
					}
					
					if(next > 100) {
						break;
					}
					
					// 5-2. 이동한 곳에 사다리 or 뱀이 있으면 이동
					if(map[next/10][next%10] != 0) {
						next = map[next/10][next%10];
					}
					
					// 5-3. 방문한 곳은 pass
					if(isVisit[next/10][next%10] == true) {
						continue;
					}
					
					// 5-4. 다음 지점 큐에 넣기
					q.offer(next);
					isVisit[next/10][next%10] = true;
				}
				
				if(isArrive == true) {
					break;
				}
			}
		}
		System.out.println(count);
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		// 1. 사다리 수, 뱀의 수 입력 받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 2. 사다리와 뱀 표시
		map = new int[11][11];
		int a, b;
		for (int i = 0; i < N+M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			map[a/10][a%10] = b;
		}
		
		isVisit = new boolean[11][11];
		count = 0;
	}
}