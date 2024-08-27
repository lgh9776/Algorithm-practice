import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 정점들로 그래프 생성하기
 * 진입 차수 관리하는 배열 생성
 * 
 * 1. 테스트 케이스(10번)만큼 반복 하기
 * 2. 정점 수 입력 받기
 * 3. 정점 수만큼 반복하며 정점 간 관계 저장 (배열 : 해당 정점, 리스트 : to)
 * 	3-1. to로 지목된 정점은 진입 차수 증가
 * 4. 진입 차수가 0인 것들 큐에 넣기
 * 5.큐가 빌 때까지 반복
 * 	5-1. 큐에 있는 정점의 인덱스 꺼냄
 * 	5-2. 해당 정점의 to들의 진입차수 -1
 * 	5-3. -1했을 때 진입차수가 0이 되면 큐에 넣음
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int TEST_CASE = 10;
	
	static int vCnt;
	static int eCnt;
	static List<Integer>[] adj; //정점들의 관계를 담을 수 있는 배열
	static int[] inDegree; //진입차수 관리할 배열
	static Deque<Integer> outVertax; //진입차수가 0인 정점
	
	//1. 테스트 케이스(10번)만큼 반복 하기
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for(int test = 1; test <= TEST_CASE; test++) {
			sb.append("#").append(test).append(" ");
			
			inputData();
			
			//4. 진입 차수가 0인 것들 큐에 넣기
			outVertax = new ArrayDeque<>();
			for(int index = 1; index <= vCnt; index++) {
				if(inDegree[index] == 0) {
					outVertax.offer(index);
				}
			}
			
			//5.큐가 빌 때까지 반복
			while(!outVertax.isEmpty()) {
				//5-1. 큐에 있는 정점의 인덱스 꺼냄 (진입 차수가 0인 것들)
				int delVertexIndex = outVertax.poll();
				sb.append(delVertexIndex).append(" ");
				
				//5-2. 해당 정점의 to들의 진입차수 -1
				for(int index = 0; index < adj[delVertexIndex].size(); index++) {
					int toIndex = adj[delVertexIndex].get(index); //해당 정점과 간선으로 연결된 to정점의 인덱스
					inDegree[toIndex]--;
					
					//5-3. -1했을 때 진입차수가 0이 되면 큐에 넣음
					if(inDegree[toIndex] == 0) {
						outVertax.offer(toIndex);
					}
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. 정점 수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		vCnt = Integer.parseInt(st.nextToken());
		eCnt = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[vCnt+1]; //정점에서 지목하는 걸 저장하기 위해 모든 정점이 시작 배열에 있어야함
		for(int index = 1; index <= vCnt; index++) {
			adj[index] = new ArrayList<>();
		}
		
		//3. 간선 수만큼 반복하며 정점 간 관계 저장 (배열 : 해당 정점, 리스트 : to)
		inDegree = new int[vCnt+1]; //모든 정점의 진입 차수 정보를 담기 위해 정점의 개수만큼
		st = new StringTokenizer(br.readLine().trim());
		for(int edge = 0; edge < eCnt; edge++) {
			int fromIndex = Integer.parseInt(st.nextToken());
			int toIndex = Integer.parseInt(st.nextToken());
			adj[fromIndex].add(toIndex);
			
			//3-1. to로 지목된 정점은 진입 차수 증가
			inDegree[toIndex]++;
		}
	}
}