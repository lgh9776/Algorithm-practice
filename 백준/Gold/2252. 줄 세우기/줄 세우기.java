import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 정점(학생 수), 간선(키를 비교한 횟수) 수 입력 받기
 * 2. 정점 수 만큼의 리스트 배열 생성
 * 3. 진입 차수를 관리할 배열 생성
 * 4. 키 비교 정보 입력 받기
 * 	4-1. to(더 큰 학생) 인덱스 진입 차수 증가
 * 5. 큐에 진입차수가 0인 정점의 인덱스 넣기
 * 6. 큐가 빌 때까지 반복
 * 	6-1. 큐의 맨 앞 요소 꺼내기
 * 		6-1-1. 출력할 결과에 추가
 * 	6-2. 그와 연결된 정점의 진입 차수 -1
 * 	6-3. 해당 진입 차수가 0이 되면 큐에 넣기
 * 
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int vCnt;
	static int eCnt;
	
	static List<Integer>[] adj;
	static int[] indegree;
	static Deque<Integer> out;
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		inputData();
		
		out = new ArrayDeque<>();
		//5. 큐에 진입차수가 0인 정점의 인덱스 넣기
		for (int index = 1; index <= vCnt; index++) {
			if(indegree[index] == 0) {
				out.offer(index);
			}
		}
		
		//6. 큐가 빌 때까지 반복
		while(!out.isEmpty()) {
			//6-1. 큐의 맨 앞 요소 꺼내기
			int outIndex = out.poll();
			
			//6-1-1. 출력할 결과에 추가
			sb.append(outIndex).append(" ");
			
			//6-2. 그와 연결된 정점의 진입 차수 -1
			for (int index : adj[outIndex]) {
				indegree[index]--;
				
				//6-3. 해당 진입 차수가 0이 되면 큐에 넣기
				if(indegree[index] == 0) {
					out.offer(index);
				}
			}
		}
		System.out.print(sb);
	}

	static void inputData() throws IOException {
		//1. 정점(학생 수), 간선(키를 비교한 횟수) 수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		vCnt = Integer.parseInt(st.nextToken());
		eCnt = Integer.parseInt(st.nextToken());
		
		//2. 정점 수 만큼의 리스트 배열 생성
		adj = new ArrayList[vCnt+1];
		for (int index = 1; index <= vCnt; index++) {
			adj[index] = new ArrayList<Integer>();
		}
		
		//3. 진입 차수를 관리할 배열 생성
		indegree = new int[vCnt+1];
		
		for (int count = 0; count < eCnt; count++) {
			//4. 키 비교 정보 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			int fromIndex = Integer.parseInt(st.nextToken());
			int toIndex = Integer.parseInt(st.nextToken());
			
			adj[fromIndex].add(toIndex);
			
			//4-1. to(더 큰 학생) 인덱스 진입 차수 증가
			indegree[toIndex]++;
		}
	}
}
