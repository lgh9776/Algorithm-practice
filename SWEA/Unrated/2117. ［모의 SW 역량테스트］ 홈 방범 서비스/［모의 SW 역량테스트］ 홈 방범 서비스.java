import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. mapSize, eachPay 입력 받기
 * 3. map 정보 입력 받기
 * 	3-1. 총 집의 수 구하기
 * 4. 서비스 제공 영역 1씩 증가시키는 반복
 * 	4-1. 운영 비용이 모든 집에서 지불한 금액보다 크면 break
 * 	4-2. 모든 칸을 마름모의 중심이 되도록 이중 반복문
 * 	4-3. (메소드) 해당 좌표에서 서비스 제공 영역(k)만큼의 마름모 순환 시 커버할 수 있는 집의 수 얻기
 * 		4-3-1. 해당 좌표를 기준으로 마름모 순환하는 반복문
 * 		4-3-2. 마름모 좌표가 배열 범위를 벗어나면 continue
 * 		4-3-3. 순환 시 해당 칸의 값이 1(집)이면 homeCnt 증가
 * 		4-3-4. 마름모 순환이 끝나면 homeCnt 반환 
 * 	4-4. 반환 받은 집의 수로 운영비용을 커버할 수 있으면
 * 		4-4-1. 최대로 커버할 수 있는 집의 수 갱신 판단
 * 5. 최대로 서비스 제공 받는 집의 수 출력
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int mapSize;
	static int eachPay;
	static int[][] map;
	static int totalHomeCnt;
	static int maxHomeCnt;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();
			
			//4. 서비스 제공 영역 1씩 증가시키는 반복
			int serviceSize = 1;
			while(true) {
				//4-1. 운영 비용이 모든 집에서 지불한 금액보다 크면 break
				int nowCost = serviceSize * serviceSize + (serviceSize-1) * (serviceSize-1);
				if(nowCost > totalHomeCnt * eachPay)
					break;
				
				//4-2. 모든 칸을 마름모의 중심이 되도록 이중 반복문
				for (int row = 0; row < mapSize; row++) {
					for (int col = 0; col < mapSize; col++) {
						//4-3. (메소드) 해당 좌표에서 서비스 제공 영역(k)만큼의 마름모 순환 시 커버할 수 있는 집의 수 얻기
						int nowHomeCnt = calHomeCnt(row, col, serviceSize);
						
						//4-4. 반환 받은 집의 수로 운영비용을 커버할 수 있으면
						if(nowCost <= nowHomeCnt * eachPay) {
							//4-4-1. 최대로 커버할 수 있는 집의 수 갱신 판단
							maxHomeCnt = Math.max(maxHomeCnt, nowHomeCnt);
						}
					}
				}
				serviceSize++;
			}
			sb.append(maxHomeCnt).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. mapSize, eachPay 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		eachPay = Integer.parseInt(st.nextToken());
		totalHomeCnt = 0;
		maxHomeCnt = Integer.MIN_VALUE;
		
		//3. map 정보 입력 받기
		map = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				
				//3-1. 총 집의 수 구하기
				if(map[row][col] == 1)
					totalHomeCnt++;
			}
		}
	}

	static int calHomeCnt(int row, int col, int size) {
		int homeCnt = 0;
		int start = col;
		int end = col;
		
		//4-3-1. 해당 좌표를 기준으로 마름모 순환하는 반복문
		for (int nowRow = row - (size - 1); nowRow <= row + (size - 1); nowRow++) {
			for (int nowCol = start; nowCol <= end; nowCol++) {
				//4-3-2. 마름모 좌표가 배열 범위를 벗어나면 continue
				if(nowRow < 0 || nowCol < 0 || nowRow >= mapSize || nowCol >= mapSize)
					continue;
				
				//4-3-3. 순환 시 해당 칸의 값이 1(집)이면 homeCnt 증가
				if(map[nowRow][nowCol] == 1)
					homeCnt++;			
			}
			if(nowRow < row) {
				start--;
				end++;
			}
			else {
				start++;
				end--;
			}	
		}
		//4-3-4. 마름모 순환이 끝나면 homeCnt 반환
		return homeCnt;
	}
}