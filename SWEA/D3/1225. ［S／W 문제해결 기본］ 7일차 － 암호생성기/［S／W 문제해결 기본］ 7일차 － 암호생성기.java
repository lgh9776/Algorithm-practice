import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 1. 테스트 케이스만큼 반복 (10으로 고정)
 * 2. 8개의 숫자 입력 받아 Queue에 저장
 * 3. 암호 생성 메소드 호출
 * 	3-1. poll로 front를 꺼냄
 * 	3-2. 해당 요소가 들어온 순서만큼 감소
 *  3-3. 계산한 숫자가 0보다 작아지면 return
 *  3-4. offer를 통해 큐의 맨 뒤에 삽입
 * 4. 해당 큐에 있는 암호 출력
 * 
 */


public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int TEST_CASE = 10;
	static final int DATA_COUNT = 8;
	static final int REDUCE_CYCLE = 5;
	
	static Queue<Integer> data = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스만큼 반복 (10으로 고정)
		for(int testCase = 1; testCase <= TEST_CASE; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			//2. 8개의 숫자 입력 받아 Queue에 저장
			inputData();

			//3. 암호 생성 메소드 호출
			makeResult();
			
			//4. 해당 큐에 있는 암호 출력
			for(int index = 0; index < DATA_COUNT; index++) {
				sb.append(data.poll()).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static void inputData() throws Exception {
		st = new StringTokenizer(br.readLine()); //테스트 케이스
		st = new StringTokenizer(br.readLine()); //데이터 
		
		for(int index = 0; index < DATA_COUNT; index++) {
			data.offer(Integer.parseInt(st.nextToken()));
		}
	}
	
	static void makeResult() {
		while(true) {
			for(int order = 1; order <= REDUCE_CYCLE; order++) {
				//3-1. poll로 front를 꺼냄
				int element = data.poll();
				
				//3-2. 해당 요소가 들어온 순서만큼 감소
				element -= order;
				
				//3-3. 계산한 숫자가 0보다 작아지면 return
				if(element <= 0) {
					element = 0;
					data.offer(element);
					return;
				}
				
				//3-4. offer를 통해 큐의 맨 뒤에 삽입
				data.offer(element);
			}
		}
	}

}
