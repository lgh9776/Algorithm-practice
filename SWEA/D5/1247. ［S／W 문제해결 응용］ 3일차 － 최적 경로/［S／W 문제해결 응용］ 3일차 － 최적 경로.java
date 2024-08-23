import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 고객의 수 입력 받기
 * 	2-1. 재귀에 사용할 배열 생성
 * 3. 회사 좌표, 집 좌표, 고객의 좌표 입력 받기
 * 4. 고객의 집을 방문하는 모든 순서를 찾는 재귀 호출
 * 5. 현재까지 경로의 이동거리 구하기
 * 	5-1. 회사부터 첫 고객의 집까지 거리
 * 	5-2. 각 고객의 집들끼리 거리
 * 	5-3. 마지막 고객의 집부터 집까지 거리 
 * 6. (기저조건) 모든 집을 방문 했으면
 * 	6-1. 가장 짧은 이동거리 갱신 후 return
 * 7.현재까지 경로 거리가 가장 짧은 이동거리보다 같거나 크면 return
 * 8. 현재까지 선택 안된 것이 있으면
 *  8-1. 선택 표시
 * 	8-2. 다음 선택할 요소 찾으러 재귀 호출
 * 	8-3. 원상복구
 * 9. 가장 최적의 이동 거리 출력
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	//좌표를 저장을 위한 class
	static class Pair{
		int row;
		int col;
		
		public Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	//기본 입력 저장 
	static int customerCnt; //고객 수
	static Pair[] customerHome; //각 영역의 좌표
	static Pair company;
	static Pair home;
	
	//재귀에 필요한 변수들
	static boolean[] isSelect; //해당 요소가 선택됐는지
	static Pair[] select; //선택된 요소 저장
	static int minMove;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			inputData();
			
			//4. 고객의 집을 방문하는 모든 순서를 찾는 재귀 호출
			findMinMove(0);
			
			//9. 가장 최적의 이동 거리 출력
			sb.append(minMove).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. 고객의 수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		customerCnt = Integer.parseInt(st.nextToken());
		
		//2-1. 재귀에 사용할 배열 생성
		isSelect = new boolean[customerCnt];
		select = new Pair[customerCnt];
		minMove = Integer.MAX_VALUE;
		
		//3. 회사 좌표, 집 좌표, 고객의 좌표 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		int rowData = Integer.parseInt(st.nextToken());
		int colData = Integer.parseInt(st.nextToken());
		company = new Pair(rowData, colData);
		
		rowData = Integer.parseInt(st.nextToken());
		colData = Integer.parseInt(st.nextToken());
		home = new Pair(rowData, colData);
		
		customerHome = new Pair[customerCnt];
		for (int index = 0; index < customerCnt; index++) {
			rowData = Integer.parseInt(st.nextToken());
			colData = Integer.parseInt(st.nextToken());
			customerHome[index] = new Pair(rowData, colData);
		}
	}
		
	
	static void findMinMove(int selectIndex) {
		//5. 현재까지 경로의 이동거리 구하기
		int currentMove = 0;
		if(selectIndex != 0)
			currentMove = calMove(selectIndex);
		
		//6. (기저조건) 모든 집을 방문 했으면
		if(selectIndex == customerCnt) {
			//6-1. 가장 짧은 이동거리 갱신 후 return
			minMove = Math.min(minMove, currentMove);
			return;
		}
		
		//7.현재까지 경로 거리가 가장 짧은 이동거리보다 같거나 크면 return
		//이 조건 때문에 currentMove를 제일 큰 수로 저장하면 안됨
		if(minMove <= currentMove) { 
			return;
		}
		
		for(int index = 0; index < customerCnt; index++) {
			//8. 현재까지 선택 안된 것이 있으면
			if(!isSelect[index]) {
				//8-1. 선택 표시
				isSelect[index] = true;
				select[selectIndex] = customerHome[index];
				
				//8-2. 다음 선택할 요소 찾으러 재귀 호출
				findMinMove(selectIndex + 1);
				
				//8-3. 원상복구
				isSelect[index] = false;
			}
		}
	}
	
	static int calMove(int selectIndex) {
		int currentMove = 0;
		
		//5-1. 회사부터 첫 고객의 집까지 거리
		currentMove += Math.abs(company.row - select[0].row) +  Math.abs(company.col - select[0].col);

		//5-2. 각 고객의 집들끼리 거리
		for (int index = 0; index < selectIndex - 1; index++) {
			currentMove += Math.abs(select[index].row - select[index+1].row) +  Math.abs(select[index].col - select[index+1].col);
		}
			
		//5-3. 마지막 고객의 집부터 집까지 거리 
		currentMove += Math.abs(select[selectIndex-1].row - home.row) +  Math.abs(select[selectIndex-1].col - home.col);

		return currentMove;
	}
}