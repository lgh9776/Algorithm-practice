import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 집합 원소의 개수, 연산의 개수 입력 받기
 * 2. 각 원소의 그룹을 표시할 배열 생성
 * 3. 연산의 개수만큼 반복
 * 4. 연산이 0일 경우 a와 b를 합집합
 * 5. 연산이 1일 경우 a와 b의 루트 찾음
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int[] p;
	static int n, m;
	
	public static void main(String[] args) throws IOException {
		inputData();

		// 3. 연산의 개수만큼 반복
		int cal, a, b;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			cal = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			// 4. 연산이 0일 경우 a와 b를 합집합
			if(cal == 0) {
				union(a, b);
			}
			// 5. 연산이 1일 경우 a와 b의 루트 찾음
			else if(cal == 1) {
				if(find(a) == find(b)) {
					sb.append("YES").append("\n");
				}
				else {
					sb.append("NO").append("\n");
				}
			}
		}
		System.out.println(sb);
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		// 1. 집합 원소의 개수, 연산의 개수 입력 받기
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// 2. 각 원소의 그룹을 표시할 배열 생성
		p = new int[n + 1];
		for (int i = 0; i < n+1; i++) {
			p[i] = -1;
		}
	}

	static void union(int a, int b) {
		int p1 = find(a);
		int p2 = find(b);

		if(p1 == p2) { // 루트가 같으면 같은 그룹
			return;
		}
		else { // 루트가 다를 경우 합쳐줌
			p[p2] = p1;
		}
	}
	
	static int find(int index) {
		if(p[index] == -1) {
			return index; // 최종 루트인 경우 return
		}
		// 그 노드의 부모를 계속 찾음 (배열에는 부모 노드의 인덱스가 있음)
		return p[index] = find(p[index]);
	}
}
