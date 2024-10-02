import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 자석의 날 정보 deque로 저장 -> 0번이 화살표 위치, 1번 인덱스부터 순서대로 시계방향
 * 회전 후 인접한 자석을 확인하고 회전하는 부분 메소드 생성 (인자로 자석 번호, 인덱스 번호 넘기기)
 * 
 * 1. 테스트 케이스 입력 받기
 * 2. 톱니바퀴를 회전시키는 횟수 입력 받기
 * 3. 톱니바퀴 정보 입력 받기 //N극 (0), S극 (1)
 * 4. 회전 정보 입력 받기 //시계(1), 반시계(-1)
 * 5. 톱니바퀴 회전
 * 	5-1. 회전 순서대로 톱니바퀴 회전시키기 위해 반복
 * 	5-2. 인접한 톱니바퀴끼리의 회전 가능 여부 저장 
 * 		 (회전하기 전 인접한 것이 서로 다르면 회전할 때 인접한 것은 반대 방향으로 회전)
 * 	5-3. 회전할 톱니바퀴 회전큐에 넣기
 * 	5-4. 회전큐가 공백큐가 될 때까지 반복
 * 		5-4-1. 회전큐에서 회전 시킬 톱니바퀴를 꺼내고 해당 톱니바퀴 회전
 * 		5-4-2. 해당 톱니바퀴와 인접한 톱니바퀴가 회전 가능한 것이면
 * 		5-4-3. 인접한 톱니바퀴 번호, 회전 방향을 저장해 회전큐에 넣음
 * 6. 모든 회전이 끝나면 점수 계산 후 출력 (화살표 부분이 S극이면, 1번 1점/2번 2점/3번 4점/4번 8점)
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	//회전 정보를 저장할 클래스
	static class Turn{
		int index;
		int dir;
		
		public Turn() {};
		
		public Turn(int index, int dir) {
			super();
			this.index = index;
			this.dir = dir;
		}
	}
	
	static final int GEAR_CNT = 4;
	static final int GEAR_TEETH_CNT = 8;
	
	static int turnCnt;
	static Queue<Turn> turnInfo;
	static List<Integer>[] gear;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();
			
			//5. 톱니바퀴 회전
			turnGear();
			
			//6. 모든 회전이 끝나면 점수 계산 후 출력 (화살표 부분이 S극이면, 1번 1점/2번 2점/3번 4점/4번 8점)
			int score = 0;
			for (int index = 1; index <= GEAR_CNT; index++) {
				if(index == 1 && gear[index].get(0) == 1)
					score += 1;
				else if(index == 2 && gear[index].get(0) == 1)
					score += 2;
				else if(index == 3 && gear[index].get(0) == 1)
					score += 4;
				else if(index == 4 && gear[index].get(0) == 1)
					score += 8;
			}
			sb.append(score).append("\n");
		}
		System.out.println(sb);
	}
	
	static void inputData() throws IOException {
		//2. 톱니바퀴를 회전시키는 횟수 입력 받기
		st = new StringTokenizer(br.readLine());
		turnCnt = Integer.parseInt(st.nextToken());
		
		//3. 톱니바퀴 정보 입력 받기 //N극 (0), S극 (1)
		gear = new ArrayList[GEAR_CNT+1];
		for (int index = 1; index <= GEAR_CNT; index++) {
			st = new StringTokenizer(br.readLine());
			gear[index] = new ArrayList<>();
			for (int teethIndex = 0; teethIndex < GEAR_TEETH_CNT; teethIndex++) {
				gear[index].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		//4. 회전 정보 입력 받기 //시계(1), 반시계(-1)
		turnInfo = new ArrayDeque<>();
		for (int index = 0; index < turnCnt; index++) {
			st = new StringTokenizer(br.readLine());
			Turn info = new Turn();
			info.index = Integer.parseInt(st.nextToken());
			info.dir = Integer.parseInt(st.nextToken());
			turnInfo.offer(info);
		}
	}

	static void turnGear() {
		//5-1. 회전 순서대로 톱니바퀴 회전시키기 위해 반복
		for (int turnIndex = 0; turnIndex < turnCnt; turnIndex++) {
			//5-2. 인접한 톱니바퀴끼리의 회전 가능 여부 저장 
			//(회전하기 전 인접한 것이 서로 다르면 회전할 때 인접한 것은 반대 방향으로 회전)
			//인접한 부분을 비교해서 회전 가능 여부 저장
			boolean[] canTurn = new boolean[3]; 
			canTurn[0] = gear[1].get(2) == gear[2].get(6) ? false : true;
			canTurn[1] = gear[2].get(2) == gear[3].get(6) ? false : true;
			canTurn[2] = gear[3].get(2) == gear[4].get(6) ? false : true;
			
			//5-3. 회전할 톱니바퀴 회전큐에 넣기
			Queue<Turn> turnQueue = new ArrayDeque<>();
			turnQueue.offer(turnInfo.poll());
			
			//5-4. 회전큐가 공백큐가 될 때까지 반복
			while(!turnQueue.isEmpty()) {
				//5-4-1. 회전큐에서 회전 시킬 톱니바퀴를 꺼내고 해당 톱니바퀴 회전
				Turn nowTurn = turnQueue.poll();
				
				//반시계 방향일 경우 - 첫번째 꺼내서 마지막에 넣음 
				if(nowTurn.dir == -1) {
					int item = gear[nowTurn.index].get(0);
					gear[nowTurn.index].remove(0);
					gear[nowTurn.index].add(item);
				}
				//시계 방향일 경우 - 마지막 꺼내서 첫번째에 넣음
				else if(nowTurn.dir == 1) {
					int item = gear[nowTurn.index].get(gear[nowTurn.index].size()-1);
					gear[nowTurn.index].remove(gear[nowTurn.index].size()-1);
					gear[nowTurn.index].add(0, item);
				}
				
				//5-4-2. 해당 톱니바퀴와 인접한 톱니바퀴가 회전 가능한 것이면
				if(nowTurn.index == 1) {
					//5-4-3. 인접한 톱니바퀴 번호, 회전 방향을 저장해 회전큐에 넣음
					if(canTurn[0] == true) {
						turnQueue.offer(new Turn(2, nowTurn.dir * (-1)));
						canTurn[0] = false; //처리 완료
					}
				}
				else if(nowTurn.index == 2) {
					if(canTurn[0] == true) {
						turnQueue.offer(new Turn(1, nowTurn.dir * (-1)));
						canTurn[0] = false;
					}
					
					if(canTurn[1] == true) {
						turnQueue.offer(new Turn(3, nowTurn.dir * (-1)));
						canTurn[1] = false;
					}
				}
				else if(nowTurn.index == 3) {
					if(canTurn[1] == true) {
						turnQueue.offer(new Turn(2, nowTurn.dir * (-1)));
						canTurn[1] = false;
					}
					
					if(canTurn[2] == true) {
						turnQueue.offer(new Turn(4, nowTurn.dir * (-1)));
						canTurn[2] = false;
					}
				}
				else if(nowTurn.index == 4) {
					if(canTurn[2] == true) {
						turnQueue.offer(new Turn(3, nowTurn.dir * (-1)));
						canTurn[2] = false;
					}
				}
			}	
		}	
	}
}