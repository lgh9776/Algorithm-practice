import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int studentCnt;
	static int compareCnt;
	
	static boolean[][] adjArr;
	static int goodCnt;

	public static void main(String[] args) throws IOException {
		
		inputData();

		// 플로이드-워셜로 자신보다 더 큰 것들의 관계 찾기
		for (int i = 0; i < studentCnt; i++) {
			for (int j = 0; j < studentCnt; j++) {
				for (int k = 0; k < studentCnt; k++) {
					// i보다 j가 클 때 j보다 k가 크면 i보다 k가 크다는 표시
					// j < i, i < k = j < k
					if(adjArr[j][i] && adjArr[i][k]) {
						adjArr[j][k] = true;
					}
				}
			}
		}
		
		// 기준 인덱스의 행, 열을 모두 확인하면 자신보다 큰/작은 모든 노드 알 수 있음
		for (int i = 0; i < studentCnt; i++) {
			int count = 0;
			for (int j = 0; j < studentCnt; j++) {
				if(adjArr[i][j] || adjArr[j][i]) {
					count++;
				}
			}
			
			if(count == studentCnt-1) {
				goodCnt++;
			}
		}
		System.out.println(goodCnt);
	}

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 학생 수, 키 비교 횟수 입력 받기
		st = new StringTokenizer(br.readLine());
		studentCnt = Integer.parseInt(st.nextToken());
		compareCnt = Integer.parseInt(st.nextToken());
		
		// 2. 키 비교 결과 인접리스트로 저장하기
		adjArr = new boolean[studentCnt][studentCnt];
		
		for (int i = 0; i < compareCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int small = Integer.parseInt(st.nextToken()) - 1;
			int big = Integer.parseInt(st.nextToken()) - 1;
			
			adjArr[small][big] = true;
		}
		
		goodCnt = 0;
	}
}