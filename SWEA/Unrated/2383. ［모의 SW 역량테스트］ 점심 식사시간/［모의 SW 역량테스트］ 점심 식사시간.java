import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. map 크기 입력 받기
 * 3. map 정보 입력 받기
 * 	3-1. 사람 위치, 계단 위치 따로 저장
 * 4. 부분집합에서 사용할 값 초기화
 * 5. (부분집합) 첫 계단에 내려갈 사람들 구하기
 * 6. (기저조건) 부분집합이 완성되면
 * 	6-1. 자동으로 2번 계단에 나머지 사람 배치
 * 	6-2. 각 사람에 대한 계단까지 도착 시간 구하기
 * 	6-3. 모든 사람이 계단을 내려갈 때까지 반복
 * 		6-3-1. 대기중인 사람이 없고, 계단 이용하는 중인 사람도 없을 때 탈출
 * 		6-3-2. 계단 이용 확인 (각 계단을 이용자 시간 체크)
 * 		6-3-3. 계단을 사용할 수 있으면 대기 인원 감소 (계단 도착시간으로 구성된) 우선순위 큐에서 제거
 * 		6-3-4. 계단 사용 중이면 이용 시간 감소, 새로운 사람 이용 추가
 * 		6-3-5. 시간 증가
 * 		6-3-6. 계단까지 모두 내려갔다면 최소 시간 갱신
 * 8. 선택 후 방문
 * 9. 선택x 방문
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Locate{
		int x;
		int y;
		
		public Locate(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static List<Locate> steps;
	static List<Locate> persons;

	static int mapSize;
	static int[][] map;
	static boolean[] isSelected;
	static int minTime;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			inputData();
			
			//5. (부분집합) 첫 계단에 내려갈 사람들 구하기
			powerSet(0);
			
			sb.append(minTime).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. map 크기 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		
		//3. map 정보 입력 받기
		map = new int[mapSize][mapSize];
		steps = new ArrayList<>();
		persons = new ArrayList<>();
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				
				//3-1. 사람 위치, 계단 위치 따로 저장
				if(map[row][col] == 1)
					persons.add(new Locate(row, col));
				else if(map[row][col] >= 2)
					steps.add(new Locate(row, col));
			}
		}
		
		//4. 부분집합에서 사용할 값 초기화
		isSelected = new boolean[persons.size()];
		minTime = Integer.MAX_VALUE;
	}

	
	static void powerSet(int selectIndex) {
		//6. (기저조건) 부분집합이 완성되면
		if(selectIndex == persons.size()) {
			//6-1. 자동으로 2번 계단에 나머지 사람 배치
			PriorityQueue<Integer> step1Person = new PriorityQueue<>();
			PriorityQueue<Integer> step2Person = new PriorityQueue<>();
			
			//6-2. 각 사람에 대한 계단까지 도착 시간 구하기
			for(int index = 0; index < persons.size(); index++) {
				if(isSelected[index] == true) { //1번 계단 이용
					int xLen = Math.abs(persons.get(index).x - steps.get(0).x);
					int yLen = Math.abs(persons.get(index).y - steps.get(0).y);
					step1Person.add(xLen + yLen);
				}
				else if(isSelected[index] == false) { //2번 계단 이용
					int xLen = Math.abs(persons.get(index).x-steps.get(1).x);
					int yLen = Math.abs(persons.get(index).y-steps.get(1).y);
					step2Person.add(xLen + yLen);
				}
			}
			
			int restPerson = persons.size(); //계단 대기중인 인원
			//각 계단을 사용하고 있는 사람들의 남은 시간
			int[] usingStep1Time = new int[3];
			int[] usingStep2Time = new int[3];
			
			//6-3. 모든 사람이 계단을 내려갈 때까지 반복
			int time = 0;
			while(true) {
				
				//6-3-1. 대기중인 사람이 없고, 계단 이용하는 중인 사람도 없을 때 탈출
				if(restPerson == 0) {
					//계단 이용중인 사람 있는지 확인
					boolean isDone = true;
					for (int index = 0; index < 3; index++) {
						if(usingStep1Time[index] != 0 || usingStep2Time[index] != 0) {
							isDone = false;
							break;
						}
					}
					
					if(isDone == true) //계단 이용자 없을 때
						break;
				}
				
				//6-3-2. 계단 이용 확인 (각 계단을 이용자 시간 체크)
				for (int index = 0; index < 3; index++) {
					//계단1
					//6-3-3. 계단을 사용할 수 있으면 대기 인원 감소 (계단 도착시간으로 구성된) 우선순위 큐에서 제거
					//usingStep1Time은 순서를 나타내는 것x, 그냥 계단 이용중인 사람
					if(usingStep1Time[index] == 0) { //계단 이용할 수 있는 자리 존재
						if(!step1Person.isEmpty()) {
							//우선순위 큐에는 해당 계단에 도착하는 시간 순서대로 있음
							//해당 시간이 현재 시간보다 같거나 작으면 계단까지 도착한 것
							if(step1Person.peek() <= time) {
								restPerson--;
								usingStep1Time[index] = map[steps.get(0).x][steps.get(0).y]; //해당 계단 완료 시간
								step1Person.poll(); //계단 이용중이므로 대기(우선순위) 큐에서 제거
							}
						}
					}
					//6-3-4. 계단 사용 중이면 이용 시간 감소, 새로운 사람 이용 추가
					else {
						usingStep1Time[index]--; //계단 이용 시간 감소
						if(usingStep1Time[index] == 0) {
							if(!step1Person.isEmpty()) {
								if(step1Person.peek() <= time) {
									restPerson--;
									usingStep1Time[index] = map[steps.get(0).x][steps.get(0).y];
									step1Person.poll();
								}
							}
						}
					}
					
					//계단2
					if(usingStep2Time[index] == 0) {
						if(!step2Person.isEmpty()) {
							if(step2Person.peek() <= time) {
								restPerson--;
								usingStep2Time[index] = map[steps.get(1).x][steps.get(1).y]; 
								step2Person.poll();
							}
						}
					}
					else {
						usingStep2Time[index]--; //계단 이용 시간 감소
						if(usingStep2Time[index] == 0) {
							if(!step2Person.isEmpty()) {
								if(step2Person.peek() <= time) {
									restPerson--;
									usingStep2Time[index] = map[steps.get(1).x][steps.get(1).y]; 
									step2Person.poll();
								}
							}
						}
					}
				}
				//6-3-5. 시간 증가 
				//해당 시간에 계단까지 도착했는가 확인을 위해서 모든 절차 진행 후 시간 증가
				time++;
			}
			
			//6-3-6. 계단까지 모두 내려갔다면 최소 시간 갱신
			minTime = Math.min(minTime, time);
			return;
		}
		
		//8. 선택 후 방문
		isSelected[selectIndex] = true;
		powerSet(selectIndex + 1);
		
		//9. 선택x 방문
		isSelected[selectIndex] = false;
		powerSet(selectIndex + 1);
	}
	
}