import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 1. 연구소의 사이즈 입력 받기
 * 2. 연구소 상황 입력 받기
 * 	2-1. 벽을 세울 수 있는 곳의 좌표 따로 저장
 * 3. 빈 영역 중 3개를 뽑는 조합 호출
 * 4. (기재조건) 3개를 다 뽑았을 경우 안전영역 계산 후 return
 * 	4-1. 새로운 벽 위치에서 바이러스를 계산할 복사본 생성
 * 	4-2. 선택된 좌표에 벽 설치
 * 	4-3. 바이러스 전염
 * 		4-3-1. 전염된 구간 변경
 * 		4-3-2. 전염된 부분 추가
 * 	4-4. 안전영역 계산 후 갱신
 * 5. 남은 요소가 뽑아야 할 수보다 작으면 return
 * 6. (전처리) 선택되지 않은 요소 선택 후 다음 재귀 호출
 * 7. (후처리) 해당 요소 선택 해제
 * 
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int mapRow, mapCol;
	static int[][] map;
	static ArrayList<int[]> canWall;
	static int bestSafeArea = 0;
	
	static boolean[] isSelected;
	static int[] selected;
	static final int SELECT_NUM = 3;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		inputData();
		
		//3. 빈 영역 중 3개를 뽑는 조합 생성
		isSelected = new boolean[canWall.size()];
		selected = new int[SELECT_NUM];
		combination(0, 0);
		System.out.println(bestSafeArea);
	}
	
	static void inputData() throws Exception {
		//1. 연구소의 사이즈 입력 받기
		st = new StringTokenizer(br.readLine());
		mapRow = Integer.parseInt(st.nextToken());
		mapCol = Integer.parseInt(st.nextToken());
		
		//2. 연구소 상황 입력 받기
		map = new int[mapRow][mapCol];
		canWall = new ArrayList<>();
		for(int row = 0; row < mapRow; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < mapCol; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				
				//2-1. 벽을 세울 수 있는 곳의 좌표 따로 저장
				if(map[row][col] == 0) {
					int[] saveData = {row, col};
					canWall.add(saveData);
				}
			}
		}
	}
	
	static void combination(int selectIndex, int elementIndex) {
		//4. (기재조건) 3개를 다 뽑았을 경우 return
		if(selectIndex == SELECT_NUM) {
			calSafeArea();
			return;
		}
		
		//5. 남은 요소가 뽑아야 할 수보다 작으면 return
		if(SELECT_NUM - (selectIndex + 1) > canWall.size() - (elementIndex + 1)) {
			return;
		}
		
		for(int index = elementIndex; index < canWall.size(); index++) {
			if(isSelected[index] == false) {
				//6. (전처리) 선택되지 않은 요소 선택 후 다음 재귀 호출
				selected[selectIndex] = index;
				isSelected[index] = true;
				
				combination(selectIndex + 1, index + 1);
				
				//7. (후처리) 해당 요소 선택 해제
				isSelected[index] = false;
			}
		}
	}
	
	static void calSafeArea() {
		//4-1. 새로운 벽 위치에서 바이러스를 계산할 복사본 생성
		int[][] calMap = new int[mapRow][mapCol];
		ArrayList<int[]> virous = new ArrayList<>();
		for(int row = 0; row < mapRow; row++) {
			for(int col = 0; col < mapCol; col++) {
				calMap[row][col] = map[row][col];
				
				//바이러스 위치 저장
				if(map[row][col] == 2) {
					int[] saveData = {row, col};
					virous.add(saveData);
				}
			}
		}
		
		//4-2. 선택된 좌표에 벽 설치
		for(int index = 0; index < SELECT_NUM; index++) {
			calMap[canWall.get(selected[index])[0]][canWall.get(selected[index])[1]] = 1;
		}
		
		int[] delta_x = {-1, 0, 1, 0};
		int[] delta_y = {0, 1, 0, -1};
 		//4-3. 바이러스 전염
		for(int virousCnt = 0; virousCnt < virous.size(); virousCnt++) {
			//4-3-1. 전염된 구간 변경
			for(int index = 0; index < 4; index++) {
				int currentRow = virous.get(virousCnt)[0] + delta_x[index];
				int currentCol = virous.get(virousCnt)[1] + delta_y[index];
				//맵 크기 내에서만 연산하도록
				if(currentCol >= 0 && currentCol < mapCol && currentRow >= 0 && currentRow < mapRow) {
					if(calMap[currentRow][currentCol] == 0) {
						//4-3-2. 전염된 부분 추가
						calMap[currentRow][currentCol] = 2;
						int[] saveData = {currentRow, currentCol};
						virous.add(saveData);
					}
				}
			}
		}
		
		//4-4. 안전영역 계산 후 갱신
		int safeIndex = 0;
		for(int row = 0; row < mapRow; row++) {
			for(int col = 0; col < mapCol; col++) {
				if(calMap[row][col] == 0) {
					safeIndex++;
				}
			}
		}
		bestSafeArea = Math.max(bestSafeArea, safeIndex);	
	}
}
