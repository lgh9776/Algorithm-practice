import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * 
 * 1. 테스트 케이스 입력 받기
 * 2. 노드 수, 사람 네트워크 정보 입력 받기
 * 	2-1. 연결되지 않은 것은 무한대로 설정
 * 3. 모든 쌍 최단 경로 구하기 (모든 간선의 가중치가 1이라 생각)
 * 4. 각 경로에 대해 모든 정점이 경유 고려 대상이 되도록 반복
 * 	4-1. 현재 출발지 -> 도착지로 바로 가는 길
 * 	4-2. 현재 출발지 -> 경유지 + 경유지 -> 현재 도착지
 * 	4-3. 둘 중 작은 값을 저장
 * 5. 각 행의 합 = 각 출발지에서 각 도착지에 가는 최단 경로들의 합 = CC
 * 6. CC값이 제일 작은 것이 정답
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	//Integer.MAX_VALUE -> 경유지 계산에서 더했을 때 음수가 나와서 최솟값으로 바뀜
	static final int INF = 99999999;
	
	static int nodeCnt;
	static int[][] network;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			//2. 노드 수, 사람 네트워크 정보 입력 받기
			st = new StringTokenizer(br.readLine());
			nodeCnt = Integer.parseInt(st.nextToken());
			
			//2차원 배열의 row, col은 출발지-도착지를 의미
			network = new int[nodeCnt][nodeCnt];
			for (int row = 0; row < nodeCnt; row++) {
				for (int col = 0; col < nodeCnt; col++) {
					network[row][col] = Integer.parseInt(st.nextToken());
					
					//2-1. 연결되지 않은 것은 무한대로 설정
					//직접 연결이 안될 수 있을뿐 모든 노드는 연결되어 있음
					//-> 큰 값으로 설정 시 다른 노드를 경유해서 갔을 때 무조건 갱신됨
					if(row != col && network[row][col] == 0) {
						network[row][col] = INF;
					}
				}
			}
			
			//3. 모든 쌍 최단 경로 구하기 (모든 간선의 가중치가 1이라 생각)
			findPath();
			
			//현재 2차원 배열에는 모든 경유지를 고려했을 때
			//모든 출발지에서 모든 도착지로 가는 최적값이 저장되어 있음
			
			//5. 각 행의 합 = 각 출발지에서 각 도착지에 가는 최단 경로들의 합 = CC
			int minCC = INF;
			for (int row = 0; row < nodeCnt; row++) {
				int nowRowCC = 0;
				for (int col = 0; col < nodeCnt; col++) {
					nowRowCC += network[row][col];
				}
				minCC = Math.min(minCC, nowRowCC);
			}
			
			//6. CC값이 제일 작은 것이 정답
			sb.append(minCC).append("\n");
		}
		System.out.print(sb);
	}
	
	/*
	 * 현재 고려하는 경유지를 경유한 
	 * 모든 출발점~도착점의 최적값을 갱신
	 * 
	 * 출발 노드 1, 도착 노드 2, 3, 4, 5 ... 에 대해서
	 * 출발 노드 2, 도착 노드 1, 3, 4, 5 ... 에 대해서
	 * 모든 출발 노드 경우에 대해 업데이트
	 * => 떨어져 있는 경우가 없기에 경유해서 도착하는 값이 무조건 저장됨
	 * 
	 */
	
	static void findPath() {
		//4. 각 경로에 대해 모든 정점이 경유 고려 대상이 되도록 반복
		for (int index = 0; index < nodeCnt; index++) { //경유지 고려
			for (int start = 0; start < nodeCnt; start++) { //출발지 고려
				for (int end = 0; end < nodeCnt; end++) { //도착지 고려
					if(start == end)
						continue;
					
					//4-1. 현재 출발지 -> 도착지로 바로 가는 길
					int direct = network[start][end];
					
					//4-2. 현재 출발지 -> 경유지 + 경유지 -> 현재 도착지
					int around = network[start][index] + network[index][end];
					
					//4-3. 둘 중 작은 값을 저장
					network[start][end] = Math.min(direct, around);
				}
			}
		}
	}
}