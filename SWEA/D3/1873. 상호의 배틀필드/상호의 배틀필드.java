import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 방향, 좌표 저장해두고 마지막 전차 위치에 표시
 * 
 * 1. 테스트 케이스 입력 받기
 * 2. map 사이즈 입력 받기
 * 3. map 배열 생성 후 정보 입력 받기
 * 	3-1. 전차 초기 위치 저장 후 비워주기
 * 4. 사용자의 입력 개수 입력 받기
 * 5. 사용자 입력 받아서 배열에 저장
 * 6. 사용자 입력만큼 반복하며 입력에 대한 동작 수행
 * 	6-1. U(0), D(1), L(2), R(3)이 입력으로 들어오면
 * 		6-1-1. 전차의 방향을 입력으로 들어온 방향으로 바꿈
 * 		6-1-2. 그 방향으로 1칸 전진 가능하면 (평지라면) 이동
 * 	6-2. S가 입력으로 들어오면
 * 		6-2-1. 바라보는 방향으로 1칸씩 포탄 전진 
 * 		6-2-2. 벽돌 벽(*)을 만나면 평지로 바꾸고 전진 멈춤
 * 		6-2-3. 강철 벽(#)을 만나면 전진 멈춤
 * 		6-2-4. 맵 밖으로 나가면 전진 멈춤
 * 7. 동작 수행이 끝나면 현재 전차 위치에 현재 방향의 토큰 표시
 * 8. 출력
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	//맵 정보
	static char[][] map;
	static int mapRowSize, mapColSize;
	
	//전차 정보
	static int dir;
	static int currentRow, currentCol;
	static final int U = 0, D = 1, L = 2, R = 3;
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
	//사용자 입력
	static int inputCnt;
	static char[] userInput;
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			//데이터 입력 받기
			inputData();
			
			playGame();
			
			//7. 동작 수행이 끝나면 현재 전차 위치에 현재 방향의 토큰 표시
			if(dir == U)
				map[currentRow][currentCol] = '^';
			else if(dir == D)
				map[currentRow][currentCol] = 'v';
			else if(dir == L)
				map[currentRow][currentCol] = '<';
			else if(dir == R)
				map[currentRow][currentCol] = '>';
			
			//8. 출력
			for (int row = 0; row < mapRowSize; row++) {
				for (int col = 0; col < mapColSize; col++) {
					sb.append(map[row][col]);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. map 사이즈 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		mapRowSize = Integer.parseInt(st.nextToken());
		mapColSize = Integer.parseInt(st.nextToken());
		
		//3. map 배열 생성 후 정보 입력 받기
		map = new char[mapRowSize][mapColSize];
		for(int row = 0; row < mapRowSize; row++) {
			map[row] = br.readLine().trim().toCharArray();
		}
		
		//3-1. 전차 초기 위치 및 방향 저장 후 비워주기
		findStart();
		
		//4. 사용자의 입력 개수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		inputCnt = Integer.parseInt(st.nextToken());
		
		//5. 사용자 입력 받아서 배열에 저장
		userInput = br.readLine().trim().toCharArray();
	}
	
	static void findStart() {
		//3-1. 전차 초기 위치 및 방향 저장 후 비워주기
		for (int row = 0; row < mapRowSize; row++) {
			for (int col = 0; col < mapColSize; col++) {
				if(map[row][col] == '^') {
					currentRow = row;
					currentCol = col;
					dir = U;
					map[row][col] = '.';
					return;
				}
				else if(map[row][col] == 'v') {
					currentRow = row;
					currentCol = col;
					dir = D;
					map[row][col] = '.';
					return;
				}
				else if(map[row][col] == '<') {
					currentRow = row;
					currentCol = col;
					dir = L;
					map[row][col] = '.';
					return;
				}
				else if(map[row][col] == '>') {
					currentRow = row;
					currentCol = col;
					dir = R;
					map[row][col] = '.';
					return;
				}
			}
		}
	}
	
	static void playGame() {
		//6. 사용자 입력만큼 반복하며 입력에 대한 동작 수행
		for(int index = 0; index < inputCnt; index++) {
			//6-1. U(0), D(1), L(2), R(3)이 입력으로 들어오면
			if(userInput[index] == 'U' || userInput[index] == 'D' || userInput[index] == 'L' || userInput[index] == 'R') {
				//6-1-1. 전차의 방향을 입력으로 들어온 방향으로 바꿈
				dir = userInput[index] == 'U' ? U : dir;
				dir = userInput[index] == 'D' ? D : dir;
				dir = userInput[index] == 'L' ? L : dir;
				dir = userInput[index] == 'R' ? R : dir;
				
				//6-1-2. 그 방향으로 1칸 전진 가능하면 (평지라면) 이동
				int nextRow = currentRow + deltaRow[dir];
				int nextCol = currentCol + deltaCol[dir];
				if(nextRow >= 0 && nextRow < mapRowSize && nextCol >= 0 && nextCol < mapColSize) {
					if(map[nextRow][nextCol] == '.') {
						currentRow = nextRow;
						currentCol = nextCol;
					}
				}
			}
			//6-2. S가 입력으로 들어오면
			else if(userInput[index] == 'S') {
				//포탄의 다음 위치를 나타냄, 포탄의 시작 위치 = 현재 전차의 위치
				int bombRow = currentRow;
				int bombCol = currentCol;
				
				while(true) {
					//6-2-1. 바라보는 방향으로 1칸씩 포탄 전진
					bombRow +=  deltaRow[dir];
					bombCol +=  deltaCol[dir];
					
					//6-2-2. 맵 밖으로 나가면 전진 멈춤
					if(bombRow >= mapRowSize || bombRow < 0 || bombCol >= mapColSize || bombCol < 0) {
						break;
					}
					
					//6-2-3. 벽돌 벽(*)을 만나면 평지로 바꾸고 전진 멈춤
					if(map[bombRow][bombCol] == '*') {
						map[bombRow][bombCol] = '.';
						break;
					}
					
					//6-2-4. 강철 벽(#)을 만나면 전진 멈춤
					if(map[bombRow][bombCol] == '#') {
						break;
					}
				}
			}
		}
	}

}