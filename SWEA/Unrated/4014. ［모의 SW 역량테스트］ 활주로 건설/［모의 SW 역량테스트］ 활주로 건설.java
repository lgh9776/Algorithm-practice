import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 지도 크기, 경사로 길이 입력 받기
 * 3. 지형 정보 입력 받기
 * 4. 지도의 각 가로, 세로줄 확인을 위해 1차원 배열에 넣어서 전달
 * 5. 1차원 배열을 순회하며 높이가 다른 곳 찾기
 * 6. 차이가 2이상이면 return 0
 * 7. 차이가 1이면 경사로 설치
 * 	7-1. 인덱스가 작은 쪽이 낮은 지형인 경우
 * 		7-1-1. 더 낮은 지형이 경사로 길이만큼 있는지 확인
 * 		7-1-2. 해당 지형에 경사로가 이미 설치되어 있는지도 확인
 * 		7-1-3. 경사로 길이만큼 없으면 return 0
 * 	7-2. 인덱스가 큰 쪽이 낮은 지형인 경우
 * 		7-2-1. 더 낮은 지형이 경사로 길이만큼 있는지 확인
 * 		7-2-2. 경사로 길이만큼 있으면 설치
 * 		7-2-3. 해당 길이만큼 더해주기
 * 		7-2-4. 경사로 길이만큼 없으면 return 0
 * 8. 활주로가 만들어지면 return 1
 * 
 * 활주로 조건
 * 1. 모두 동일한 숫자일 경우
 * 2. 높이 차이가 있다면 1차이만 있어야하고 경사로 설치가 가능해야함
 * 
 * 경사로 설치 조건
 * 1. 높이 차이가 1일 경우 설치 가능
 * 2. 낮은 지형의 길이가 경사로의 길이보다 같거나 커야함
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int mapSize;
	static int[][] map;
	static int roadLen;
	static int airstrip;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();
			
			//4. 지도의 각 가로, 세로줄 확인을 위해 1차원 배열에 넣어서 전달
			//가로
			for (int row = 0; row < mapSize; row++) {
				int[] line = new int[mapSize];
				for (int col = 0; col < mapSize; col++) {
					line[col] = map[row][col];
				}
				airstrip += checkRoad(line);
			}
			
			//세로
			for (int col = 0; col < mapSize; col++) {
				int[] line = new int[mapSize];
				for (int row = 0; row < mapSize; row++) {
					line[row] = map[row][col];
				}
				airstrip += checkRoad(line);
			}

			sb.append(airstrip).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		//2. 지도 크기, 경사로 길이 입력 받기
		mapSize = Integer.parseInt(st.nextToken());
		roadLen = Integer.parseInt(st.nextToken());
		
		//3. 지형 정보 입력 받기
		map = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		airstrip = 0;
	}
	
	static int checkRoad(int[] road) {
		boolean[] isVisit = new boolean[mapSize];
		
		for (int index = 1; index < mapSize; index++) {
			//5. 1차원 배열을 순회하며 높이가 다른 곳 찾기
			if(road[index-1] != road[index]) {
				//6. 차이가 2이상이면 return 0
				if(Math.abs(road[index-1] - road[index]) >= 2) {
					return 0;
				}
				
				//7. 차이가 1이면 경사로 설치
				int putCnt = 0;
				
				//7-1. 인덱스가 작은 쪽이 낮은 지형인 경우
				if(road[index-1] < road[index]) {
					int sameNum = road[index-1];
					
					//7-1-1. 더 낮은 지형이 경사로 길이만큼 있는지 확인
					for (int check = index-1; check >= 0; check--) {
						if(sameNum != road[check]) {
							return 0;
						}
						
						//7-1-2. 해당 지형에 경사로가 이미 설치되어 있는지도 확인
						if(isVisit[check] == true) {
							return 0;
						}
						
						putCnt++;
						isVisit[check] = true;
						
						if(putCnt == roadLen) {
							break;
						}
					}
					
					//7-1-3. 경사로 길이만큼 없으면 return 0
					if(putCnt < roadLen) {
						return 0;
					}
					
				}
				
				//7-2. 인덱스가 큰 쪽이 낮은 지형인 경우
				else if(road[index-1] > road[index]) {
					int sameNum = road[index];
					
					//7-2-1. 더 낮은 지형이 경사로 길이만큼 있는지 확인
					for (int check = index; check < mapSize; check++) {
						//7-2-2. 경사로 길이만큼 있으면 설치
						if(sameNum != road[check]) {
							return 0;
						}
						
						putCnt++;
						isVisit[check] = true;
						
						if(putCnt == roadLen) {
							break;
						}
					}
					
					//7-2-3. 경사로 길이만큼 없으면 return 0
					if(putCnt < roadLen) {
						return 0;
					}
					
					//7-2-4. 해당 길이만큼 더해주기
					index += (putCnt-1);
				}
			}
		}
		//8. 활주로가 만들어지면 return 1
		return 1;
	}
}