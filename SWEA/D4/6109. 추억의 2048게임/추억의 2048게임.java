import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. map 사이즈, 방향 입력 받기
 * 3. map 정보 입력 받기
 * 4. 입력 받은 방향에 따라 배열 90도씩 회전
 * 5. 타일 아래로 밀기
 * 	5-1. 블록만 밀어주기 (빈 부분 제거)
 * 	5-2. 옮길 것이 범위 밖일 경우 pass
 * 	5-3. 옮길 것에 숫자가 있을때
 * 		5-3-1. 같으면 현재 인덱스에 두 수의 합 저장, 옮길 인덱스 비우기
 * 		5-3-2. 숫자가 다르면 옮기지 않음 -> 가만히 둠
 * 6. 배열을 돌렸다면 원래 방향으로 다시 돌려주기 
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int[][] map;
	static int mapSize;
	static int dir;
	//down(0), left(3), up(2), right(1)
	//방향과 회전 횟수를 동시에 나타냄
	//아래로 미는게 기본, 시계방향 90도 회전
	//왼쪽:3번 회전, 위쪽:2번회전, 오른쪽:1번회전
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append("\n");
			
			inputData();
			
			//아래쪽:0번, 왼쪽:3번, 위쪽:2번, 오른쪽:1번 회전
			//4. 입력 받은 방향에 따라 배열 90도씩 회전
			for(int turn = 0; turn < dir; turn++) {
				turnMap();
			}
			
			//5. 타일 아래로 밀기
			moveElement();
			
			//6. 배열을 돌렸다면 원래 방향으로 다시 돌려주기 (회전 안했으면 돌리지x)
			//4-dir번 돌려야 복구됨 
			if(dir != 0) {
				for(int turn = 0; turn < 4-dir; turn++) {
					turnMap();
				}
			}
			
			//최종 결과 출력
			for(int row = 0; row < mapSize; row++) {
				for(int col = 0; col < mapSize; col++) {
					sb.append(map[row][col]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. map 사이즈, 방향 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		String inputDir = st.nextToken();
		
		if(inputDir.equals("down"))
			dir = 0;
		else if(inputDir.equals("left"))
			dir = 3;
		else if(inputDir.equals("up"))
			dir = 2;
		else if(inputDir.equals("right"))
			dir = 1;
		
		//3. map 정보 입력 받기
		map = new int[mapSize][mapSize];
		for(int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void turnMap() {
		//오른쪽으로 90도씩 회전
		int[][] turnMap = new int[mapSize][mapSize];

		for(int row = 0; row < mapSize; row++) {
			for(int col = 0; col < mapSize; col++) {
				turnMap[row][col] = map[mapSize-col-1][row];
			}
		}
		
		//회전시킨 것 원본에 저장해주기
		for(int row = 0; row < mapSize; row++) {
			for(int col = 0; col < mapSize; col++) {
				map[row][col] = turnMap[row][col];
			}
		}
	}
	
	static void moveElement() {
		//5-1. 블록만 밀어주기 (빈 부분 제거)
		removeSpace();

		for(int row = mapSize-1; row >= 0; row--) {
			for(int col = 0; col < mapSize; col++) {
				//5-2. 옮길 것이 범위 밖일 경우 pass
				if(row-1 < 0) {
					continue;
				}
				
				//5-3. 옮길 것에 숫자가 있을때
				if(row-1 >= 0 && map[row-1][col] > 0) {
					//5-3-1. 같으면 현재 인덱스에 두 수의 합 저장, 옮길 인덱스 비우기
					if(map[row-1][col] == map[row][col]) {
						map[row][col] += map[row-1][col];
						map[row-1][col] = 0;
					}
					//5-3-2. 숫자가 다르면 옮기지 않음 -> 가만히 둠
				}
				//빈 칸이 없도록 아래로 다 밀어둔 상태 -> 윗줄부터 계산해주면 합쳐져도 중간 공백 안생김
				//합친 곳도 그 다음것과 또 비교 가능
			}
		}
		//계산 후 빈칸 제거
		removeSpace();
	}
	
	//중간 공백 제거해주는 메소드
	static void removeSpace() {
		for(int row = mapSize-1; row >= 0; row--) {
			for(int col = 0; col < mapSize; col++) {
				//빈 부분이 있으면
				if(map[row][col] == 0) {
					int spaceCnt = 1;
					
					//모든 빈부분 제거 (연속된 빈 부분이 있을 수 있으니 반복문으로 빈부분 수 count)
					while(row-spaceCnt >= 0 && map[row-spaceCnt][col] == 0) {
						spaceCnt++;
					}
					
					if(row-spaceCnt >= 0) {
						map[row][col] = map[row-spaceCnt][col];
						map[row-spaceCnt][col] = 0;
					}
				}
			}
		}
	}
}