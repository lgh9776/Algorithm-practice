import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. 사람의 수, 친구 관계의 수 입력 받기
 * 2. 친구 관계 그래프 생성
 * 3. 문제에서 제시된 관계 DFS로 탐색
 * 	3-1. 친구 관계를 찾았으면 return
 * 	3-2. 탐색해서 찾은 친구 수가 5면 return
 * 	3-3. 모든 친구 탐색
 * 		3-3-1. 관계에 포함된 친구면 pass
 * 		3-3-2. 포함 표시 및 카운팅 증가
 * 		3-3-3. 다음 친구를 찾기 위한 재귀 호출
 * 		3-3-4. 친구 관계를 찾았으면 return
 * 		3-3-5. 원상 복구
 */

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N, M;
	static List<Integer>[] map;
	static boolean[] isContain;
	static boolean isFind;

	public static void main(String[] args) throws IOException {
		inputData();
		
		// 3. 문제에서 제시된 관계 DFS로 탐색
		for (int i = 0; i < N; i++) {
			isContain[i] = true;
			dfs(i, 1);
			
			if(isFind) {
				break;
			}
			
			isContain[i] = false;
		}
		
		// 4. 결과 출력
		if(isFind) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
	}
	
	static void dfs(int x, int depth) {
		// 3-1. 친구 관계를 찾았으면 return
		if(isFind) {
			return;
		}
		
		// 3-2. 탐색해서 찾은 친구 수가 5면 return
		if(depth == 5) {
			isFind = true;
			return;
		}
		
		// 3-3. 모든 친구 탐색
		for(int next : map[x]) {
			// 3-3-1. 관계에 포함된 친구면 pass
			if(isContain[next] == true) {
				continue;
			}
			
			// 3-3-2. 포함 표시 및 카운팅 증가
			isContain[next] = true;
			
			// 3-3-3. 다음 친구를 찾기 위한 재귀 호출
			dfs(next, depth + 1);

			// 3-3-4. 친구 관계를 찾았으면 return
			if(isFind == true) {
				return;
			}
			// 3-3-5. 원상 복구
			isContain[next] = false;
		}
	}

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 사람의 수, 친구 관계의 수 입력 받기
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isContain = new boolean[N];
		isFind = false;
		
		// 2. 친구 관계 그래프 생성
		map = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			map[i] = new ArrayList<>();
		}
		
		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			map[a].add(b);
			map[b].add(a);
		}
	}
}