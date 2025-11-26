import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 기본 정보 입력 받기
 * 2. 길 정보 입력 받아서 인접행렬 생성
 * 3. (플로이드-워셜) 각 지점을 출발점으로 최단거리 구하기
 * 4. 각 지역에서 시작하여 수색범위 내 아이템 개수 구하기
 * 	4-1. 최대 아이템 개수 갱신
 * 5. 최대 아이템 개수 출력
 */

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int INF = Integer.MAX_VALUE / 2;
	
	static int[][] adjArr;
	static int n, m, r;
	static int[] items;

	public static void main(String[] args) throws IOException {
		inputData();
		
		// 3. (플로이드-워셜) 각 지점을 출발점으로 최단거리 구하기
		for (int mid = 1; mid <= n; mid++) {
			for (int s = 1; s <= n; s++) {
				for (int e = 1; e <= n; e++) {
					if(adjArr[s][e] > adjArr[s][mid] + adjArr[mid][e]) {
						adjArr[s][e] = adjArr[s][mid] + adjArr[mid][e];
					}
				}
			}
		}
		
		// 4. 각 지역에서 시작하여 수색범위 내 아이템 개수 구하기
		int max = Integer.MIN_VALUE;
		for (int s = 1; s <= n; s++) {
			int itemCnt = 0;
			for (int e = 1; e <= n; e++) {
				if(adjArr[s][e] <= m) {
					itemCnt += items[e];
				}
			}
			max = max < itemCnt ? itemCnt : max;
		}
		System.out.println(max);
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 기본 정보 입력 받기
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		items = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		// 2. 길 정보 입력 받아서 인접행렬 생성
		adjArr = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if(i == j) {
					adjArr[i][j] = 0;
				}
				else {
					adjArr[i][j] = INF;
				}
			}
		}
		
		int s, e, c;
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adjArr[s][e] = c;
			adjArr[e][s] = c;			
		}
	}
}