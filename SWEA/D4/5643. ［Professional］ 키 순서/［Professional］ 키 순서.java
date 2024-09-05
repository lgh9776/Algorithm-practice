import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 학생 수 입력 받기
 * 3. 키를 비교한 횟수 입력 받기
 * 4. 학생들의 키 본인보다 큰, 작은 기준으로 인접리스트로 만들기
 * 5. 본인보다 작은 사람, 본인보다 큰 사람 수를 저장할 배열
 * 6. 나보다 작은 사람들 찾기
 * 	6-1. 인접한 요소가 없으면 시작했던 사람 칸에 count 저장 후 return
 * 	6-2. 현재 요소의 인접한 것을 따라 계속 올라감
 * 	6-3. 사이클이 있는 것은 제외하기 위해 방문하지 않은 것만 처리
 * 7. 나보다 큰 사람들 찾기 (같은 원리)
 * 8. 각 학생을 기준으로 자신보다 크고 작은 학생수를 합한 값이 모든 자신을 제외한 학생수와 동일하면 찾을 수 있음
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int studentCnt; //vertex
	static int compareCnt; //edge
	static List<Integer>[] adjSmall; //나보다 작은 사람들 저장
	static List<Integer>[] adjBig; //나보다 큰 사람들 저장

	static int[] smallCnt;
	static int[] bigCnt;
	
	static int count;
	static boolean[] isVisit;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();
			
			//6. 나보다 작은 사람들 찾기
			for (int start = 1; start <= studentCnt; start++) {
				count = 0;
				isVisit = new boolean[studentCnt+1];
				findSmallLeaf(start, start);
			}
			
			//7. 나보다 큰 사람들 찾기
			for (int start = 1; start <= studentCnt; start++) {
				count = 0;
				isVisit = new boolean[studentCnt+1];
				findBigLeaf(start, start);
			}
			
			
			//8. 각 학생을 기준으로 자신보다 크고 작은 학생수를 합한 값이 모든 자신을 제외한 학생수와 동일하면 찾을 수 있음
			int result = 0;
			for (int index = 1; index <= studentCnt; index++) {
				if(smallCnt[index] + bigCnt[index] == studentCnt-1) {
					result++;
				}
			}
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. 학생 수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		studentCnt = Integer.parseInt(st.nextToken());
		
		//3. 키를 비교한 횟수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		compareCnt = Integer.parseInt(st.nextToken());
		
		adjSmall = new ArrayList[studentCnt+1];
		adjBig = new ArrayList[studentCnt+1];
		for (int index = 0; index < studentCnt+1; index++) {
			adjSmall[index] = new ArrayList<>();
			adjBig[index] = new ArrayList<>();
		}
		
		//4. 학생들의 키 본인보다 큰, 작은 기준으로 인접리스트로 만들기
		for (int cnt = 0; cnt < compareCnt; cnt++) {
			st = new StringTokenizer(br.readLine().trim());
			int small = Integer.parseInt(st.nextToken());
			int big = Integer.parseInt(st.nextToken());
			
			adjSmall[big].add(small);
			adjBig[small].add(big);
		}
		//5. 본인보다 작은 사람, 본인보다 큰 사람 수를 저장할 배열
		smallCnt = new int[studentCnt+1];
		bigCnt = new int[studentCnt+1];
	}

	static void findSmallLeaf(int start, int now) {
		//6-1. 인접한 요소가 없으면 시작했던 사람 칸에 count 저장 후 return
		if(adjSmall[now].isEmpty()) {
			smallCnt[start] = count;
			return;
		}
		
		//6-2. 현재 요소의 인접한 것을 따라 계속 올라감
		for(int next : adjSmall[now]) {
			//6-3. 사이클이 있는 것은 제외하기 위해 방문하지 않은 것만 처리
			if(!isVisit[next]) {
				count++;
				isVisit[next] = true;
				findSmallLeaf(start, next);
			}
		}
		smallCnt[start] = count;
	}
	
	static void findBigLeaf(int start, int now) {
		if(adjBig[now].isEmpty()) {
			bigCnt[start] = count;
			return;
		}
		
		for(int next : adjBig[now]) {
			if(!isVisit[next]) {
				count++;
				isVisit[next] = true;
				findBigLeaf(start, next);
			}
		}
		bigCnt[start] = count;
	}
}