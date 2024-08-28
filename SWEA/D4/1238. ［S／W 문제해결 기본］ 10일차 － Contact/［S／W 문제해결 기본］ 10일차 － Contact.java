import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스만큼 반복 (10번)
 * 2. 데이터 길이, 시작점 입력 받기
 * 3. from, to를 입력 받아 그래프 생성
 * 	3-1. from 노드에 to정보가 있으면 pass
 * 4. bfs 구현
 * 	4-1. 시작 노드 큐에 넣기
 * 	4-2. 시작 노드 방문 표시
 * 	4-3. 공백큐가 될 때까지 반복
 * 	4-4. 현재 큐의 사이즈만큼 반복
 * 		4-4-1. 큐 요소 꺼내기
 * 		4-4-2. 해당 요소의 인접 요소들 순회
 * 		4-4-3. 인접 요소가 방문되지 않았으면 큐에 넣고 방문 표시
 * 		4-4-4. 큐에서 꺼낸 요소 중 가장 큰 값 구하기
 * 	4-5. depth 증가, max값 새로 저장 (새로운 depth에서)
 * 5. max값 출력
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static final int TEST_CASE = 10;

	static int dataCnt;
	static int start;
	
	static List<Integer>[] adj;
	static boolean[] isVisited;
	static int maxNum;
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스만큼 반복 (10번)
		for(int testCase = 1; testCase <= TEST_CASE; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			inputData();
			
			//4. bfs 구현
			bfs();
			
			//5. max값 출력
			sb.append(maxNum).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. 데이터 길이, 시작점 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		dataCnt = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[101];
		for (int index = 0; index < 101; index++) {
			adj[index] = new ArrayList<Integer>();
		}
		
		isVisited = new boolean[101];
		//3. from, to를 입력 받아 그래프 생성
		st = new StringTokenizer(br.readLine().trim());
		for (int index = 0; index < dataCnt/2; index++) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			//3-1. from 노드에 to정보가 있으면 pass
			if(adj[from].contains(to)) {
				continue;
			}
			adj[from].add(to);
		}
	}
	
	static void bfs() {
		//4-1. 시작 노드 큐에 넣기
		Deque<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		
		//4-2. 시작 노드 방문 표시
		isVisited[start] = true;
		
		//4-3. 공백큐가 될 때까지 반복
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			//4-4. 현재 큐의 사이즈만큼 반복
			int num = Integer.MIN_VALUE;
			for (int index = 0; index < size; index++) {
				//4-4-1. 큐 요소 꺼내기
				int element = queue.poll();
				
				//4-4-2. 해당 요소의 인접 요소들 순회
				for(int adjElement : adj[element]) {
					//4-4-3. 인접 요소가 방문되지 않았으면 큐에 넣고 방문 표시
					if(!isVisited[adjElement]) {
						queue.offer(adjElement);
						isVisited[adjElement] = true;
					}
				}
				
				//4-4-4. 큐에서 꺼낸 요소 중 가장 큰 값 구하기
				num = Math.max(num, element);
			}
			//4-5. 현재 depth에서 가장 큰 max를 새로 저장
			maxNum = num;
		}
	}
}