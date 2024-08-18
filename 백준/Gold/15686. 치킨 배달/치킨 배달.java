import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * m개의 치킨집을 뽑는 조합으로 풀기
 * 1. 도시의 크기, 유지할 치킨집 수 입력 받기
 * 2. 도시의 정보 입력 받기
 * 	2-1. 치킨집 좌표 저장
 * 	2-2. 집 좌표 저장
 * 3. m개의 치킨집 뽑기
 * 4. (기재조건) m개의 치킨집을 다 뽑았을 때 return
 * 	4-1. 뽑은 치킨집 m개와 각각의 집 거리 계산
 * 	4-2. 가장 작은 값을 현재 도시 치킨 거리 값에 더한다
 * 	4-3. best 도시 치킨 거리 값보다 커지면 pass
 * 	4-4. 모든 연산을 마치면 best 도시 치킨 거리 값 갱신
 * 5. 남은 치킨집의 수가 뽑아야할 치킨집 수보다 작으면 return
 * 6. (전처리) 선택하지 않은 치킨집 선택&선택 표시
 * 7. 다음 선택할 인덱스, 다음 요소의 인덱스를 인자로 재귀호출
 * 8. (후처리) 현재 요소 선택 해제
 */


public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int mapSize; //도시 사이즈
	static int selectStoreCount; //남길 수 있는 치킨집 수
	static int bestLen = Integer.MAX_VALUE; //최소의 도시 치킨 거리
	
	static int[][] map; //도시 정보
//	static int[] selected; //선택된 치킨집 인덱스
	static boolean[] isSelect; //해당 요소가 선택됐는지 확인
	static List<int[]> homeXY; //집 좌표
	static List<int[]> storeXY; //치킨집 좌표
	
	public static void main(String[] args) throws IOException {
		inputData();
		
		//3. m개의 치킨집 뽑기
		combination(0, 0);
		System.out.println(bestLen);
	}

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		//1. 도시의 크기, 유지할 치킨집 수 입력 받기
		mapSize = Integer.parseInt(st.nextToken());
		selectStoreCount = Integer.parseInt(st.nextToken());
		
		map = new int[mapSize][mapSize];
//		selected = new int[selectStoreCount];
		
		//2. 도시의 정보 입력 받기
		homeXY = new ArrayList<>();
		storeXY = new ArrayList<>();
		for(int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				
				//2-1. 치킨집 좌표 저장
				if(map[row][col] == 2) {
					int[] save = {row, col};
					storeXY.add(save);
				}
					
				//2-2. 집 좌표 저장
				else if(map[row][col] == 1) {
					int[] save = {row, col};
					homeXY.add(save);
				}
			}
		}
		isSelect = new boolean[storeXY.size()];
		
	}
	
	static void combination(int selectIndex, int elementIndex) {
		//4. (기재조건) m개의 치킨집을 다 뽑았을 때 return
		if(selectIndex == selectStoreCount) {
			//4-1. 뽑은 치킨집 m개와 각각의 집 거리 계산
			calLen();
			return;
		}
		
		//5. 남은 치킨집의 수가 뽑아야할 치킨집 수보다 작으면 return
		if(selectStoreCount - (selectIndex + 1) > storeXY.size() - (elementIndex + 1)) {
			return;
		}
		
		for(int index = elementIndex; index < storeXY.size(); index++) {
			//6. (전처리) 선택하지 않은 치킨집 선택&선택 표시
			if(isSelect[index] == false) {
//				selected[selectIndex] = index;
				isSelect[index] = true;
				
				//7. 다음 선택할 인덱스, 다음 요소의 인덱스를 인자로 재귀호출
				combination(selectIndex + 1, index + 1);
				
				//8. (후처리) 현재 요소 선택 해제
				isSelect[index] = false;				
			}
		}
	}
	
	static void calLen() {
		int currentBestLen = Integer.MAX_VALUE; //선택된 치킨집 중 1개와 현재 집 거리를 계산하여 최소의 치킨거리 저장
		int currentCityLen = 0; //현재 계산중인 도시의 치킨 거리
		
		//4-1. 뽑은 치킨집 m개와 각각의 집 거리 계산
		for(int homeIndex = 0; homeIndex < homeXY.size(); homeIndex++) { //각 집의 좌표
			for(int storeIndex = 0; storeIndex < storeXY.size(); storeIndex++) { //뽑힌 치킨집 좌표
				//homeIndex의 집과 선택된 치킨집의 거리 구해서 배열에 저장
				if(isSelect[storeIndex]) {
					int xLen = Math.abs(homeXY.get(homeIndex)[0] - storeXY.get(storeIndex)[0]);
					int yLen = Math.abs(homeXY.get(homeIndex)[1] - storeXY.get(storeIndex)[1]);
					currentBestLen = Math.min(currentBestLen, xLen + yLen);
				}
			}			
			//4-2. 가장 작은 값을 현재 도시 치킨 거리 값에 더한다
			currentCityLen += currentBestLen;
			currentBestLen = Integer.MAX_VALUE;
			
			//4-3. best 도시 치킨 거리 값보다 커지면 pass
			if(currentCityLen > bestLen) {
				break;
			}
		}
		//4-4. 모든 연산을 마치면 best 도시 치킨 거리 값 갱신
		bestLen = Math.min(bestLen, currentCityLen);
	}
	
}
