import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. test_case : 테스트 케이스 입력 받기
 * 2. size : 달팽이 크기 입력 받기
 * 3. size * size 만큼 반복
 * 4. 델타 배열 돌린 인덱스가 조건을 만족하면 방향 결정
 * 	4-1. 해당 인덱스가 배열의 범위를 벗어나면 안됨
 * 	4-2. 해당 인덱스에 0보다 큰 값이 있으면 안됨
 * 5. 결정한 방향으로 조건을 만족하는 동안 값 저장
 * 	5-1. 해당 인덱스가 배열의 범위를 벗어나면 안됨
 * 	5-2. 해당 인덱스에 0보다 큰 값이 있으면 안됨	
 */

class Solution
{
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int[] DELTA_ROW = {0, 1, 0, -1};
	static final int[] DELTA_COL = {1, 0, -1, 0};
	static int[][] snail;
	
	public static void main(String args[]) throws Exception
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. test_case : 테스트 케이스 입력 받기
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			//2. size : 달팽이 크기 입력 받기
			int size = Integer.parseInt(br.readLine().trim());
			snail = new int[size][size];
			
			//3. size * size 만큼 반복
			int nowRow = 0;
			int nowCol = 0;
			int direction = 0;
			snail[nowRow][nowCol] = 1; //0, 0은 무조건 1
			for(int storeNum = 2; storeNum <= size*size;) {
				//4. 델타 배열 돌린 인덱스가 조건을 만족하면 방향 결정 (해당 direction 저장)
				 for(direction = 0; direction < DELTA_COL.length; direction++) {
					 int nextRow = nowRow + DELTA_ROW[direction];
					 int nextCol = nowCol + DELTA_COL[direction];
					 
					 //4-1. 해당 인덱스가 배열의 범위를 벗어나면 안됨
					 if(nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size ) {
						 continue;
					 }
					 //4-2. 해당 인덱스에 0보다 큰 값이 있으면 안됨
					 else if(snail[nextRow][nextCol] > 0) {
						 continue;
					 }
					 else {
						 break;
					 }
				 }
				 
				 //5. 결정한 방향으로 조건을 만족하는 동안 값 저장
				 while(true) {
					 int nextRow = nowRow + DELTA_ROW[direction];
					 int nextCol = nowCol + DELTA_COL[direction];
					 
					 //5-1. 해당 인덱스가 배열의 범위를 벗어나면 안됨
					 if(nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size ) {
						 break;
					 }
					 //5-2. 해당 인덱스에 0보다 큰 값이 있으면 안됨
					 else if(snail[nextRow][nextCol] > 0) {
						 break;
					 }
					 
					 nowRow = nextRow;
					 nowCol = nextCol;
					 snail[nowRow][nowCol] = storeNum;
					 storeNum++;
				 }
			}
			
			sb.append("#").append(test_case).append("\n");
			for(int row = 0; row < size; row++) {
				for(int col = 0; col < size; col++) {
					sb.append(snail[row][col]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
}