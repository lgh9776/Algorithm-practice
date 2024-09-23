import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 학생 수 입력 받기
 * 3. 각 학생이 선택한 학생 입력 받기
 * 4. dfs로 탐색되지 않은 모든 노드를 시작으로 사이클 존재하는지 확인
 * 	4-1. (기저조건) 방문한 노드이면 리턴
 * 	4-2. 현재 노드 방문 처리
 * 	4-3. 다음 노드가 방문하지 않은 것이면 다음 노드를 기준으로 dfs
 * 	4-4. 다음 노드가 방문된 것 일 때 (재방문 = 사이클)
 * 		4-4-1. 다음 노드가 탐색 처리 전이라면
 * 		4-4-2. 다음 노드를 기준으로 사이클을 이루는 노드 탐색 (다음 노드 ~ 현재 이전 노드까지)
 * 	4-5. 현재 노드의 사이클 여부 및 탐색 완료 처리 (후에 재탐색 방지)
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int studentCnt;
	static int[] team;
	static boolean[] isVisit;
	static boolean[] isDone; //해당 노드를 시작으로 경로 탐색
	static int teamStudentCnt;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			inputData();
			
			//4. dfs로 탐색되지 않은 모든 노드를 시작으로 사이클 존재하는지 확인
			for (int index = 1; index <= studentCnt; index++) {
				if(!isDone[index]) //탐색 처리 필요한 곳만 dfs
					dfs(index);
			}
			sb.append(studentCnt-teamStudentCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	static void inputData() throws IOException {
		//2. 학생 수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		studentCnt = Integer.parseInt(st.nextToken());
		
		team = new int[studentCnt+1];
		isVisit = new boolean[studentCnt+1];
		isDone = new boolean[studentCnt+1];
		
		//3. 각 학생이 선택한 학생 입력 받기
		teamStudentCnt = 0;
		st = new StringTokenizer(br.readLine().trim());
		for (int index = 1; index <= studentCnt; index++) {
			team[index] = Integer.parseInt(st.nextToken());
		}
	}

	static void dfs(int nowIndex) {
		//4-1. (기저조건) 방문한 노드이면 리턴
		if(isVisit[nowIndex])
			return;
		
		//4-2. 현재 노드 방문 처리
		isVisit[nowIndex] = true;
		
		//4-3. 다음 노드가 방문하지 않은 것이면 다음 노드를 기준으로 dfs
		int nextIndex = team[nowIndex];
		if(!isVisit[nextIndex]) {
			dfs(nextIndex);
		}
		//4-4. 다음 노드가 방문된 것 일 때 (재방문 = 사이클)
		else {
			//4-4-1. 다음 노드가 탐색 처리 전이라면 
			if(!isDone[nextIndex]) {
				teamStudentCnt++; //현재 노드를 포함하기 위해 +1
				
				//4-4-2. 다음 노드를 기준으로 사이클을 이루는 노드 탐색 (다음 노드 ~ 현재 이전 노드까지)
				for (int index = nextIndex; index != nowIndex; index = team[index]) {
					teamStudentCnt++; //사이클에 포함된 노드 수 증가
				}
			}
		}
		//4-5. 현재 노드의 사이클 여부 및 탐색 완료 처리 (후에 재탐색 방지)
		//isDone으로 사이클이 있든 없든 해당 노드를 기준으로 탐색 처리 여부를 저장하여 재탐색 방지 -> 시간 감소
		isDone[nowIndex] = true;
	}
}