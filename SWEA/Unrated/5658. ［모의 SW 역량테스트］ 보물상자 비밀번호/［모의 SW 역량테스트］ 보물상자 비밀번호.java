import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 숫자 개수, 몇번째의 숫자를 구할지 입력 받기
 * 3. 숫자는 큐에 입력 받기
 * 4. 숫자의 개수만큼 반복 (자리 1개씩 옮기기)
 * 	4-1. 변의 개수만큼 반복 (각 변의 값 계산을 위해서)
 * 		4-1-1. 한 변의 자릿수만큼 큐에서 빼기
 * 		4-1-2. 해당 16진수가 이미 있으면 pass
 * 		4-1-3. 없으면 16진수와 16진수를 10진수로 변환한 값 둘 다 저장
 * 		4-1-4. 계산한 16진수는 다시 큐에 넣어주기
 * 	4-2. 큐에서 1개 뺀 후 다시 넣어주기 (자리 변환)
 * 5. 숫자가 저장되어 있는 리스트 정렬
 * 6. 구해야할 순서의 숫자 출력
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int numCnt;
	static int findNum;
	static Queue<Character> nums;
	
	static List<String> resultHex;
	static List<Integer> resultNum;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();
			
			//4. 숫자의 개수만큼 반복 (자리 1개씩 옮기기)
			for (int cnt = 0; cnt < numCnt; cnt++) {
				calEdges();
				
				//4-2. 큐에서 1개 뺀 후 다시 넣어주기 (자리 변환)
				nums.offer(nums.peek());
				nums.poll();
			}
			
			//5. 숫자가 저장되어 있는 리스트 정렬
			Collections.sort(resultNum, Comparator.reverseOrder());
			
			//6. 구해야할 순서의 숫자 출력
			sb.append(resultNum.get(findNum-1)).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. 숫자 개수, 몇번째의 숫자를 구할지 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		numCnt = Integer.parseInt(st.nextToken());
		findNum = Integer.parseInt(st.nextToken());
		
		resultHex = new ArrayList<>();
		resultNum = new ArrayList<>();
		
		//3. 숫자는 큐에 입력 받기
		char[] input = br.readLine().trim().toCharArray();
		nums = new ArrayDeque<>();
		for(char token : input) {
			nums.offer(token);
		}
	}
	
	static void calEdges() {
		//4-1. 변의 개수만큼 반복 (각 변의 값 계산을 위해서)
		for (int edgeCnt = 0; edgeCnt < 4; edgeCnt ++) {
			//4-1-1. 한 변의 자릿수만큼 큐에서 빼기
			String calNum = "";
			for (int cnt = 0; cnt < numCnt/4; cnt++) {
				calNum += nums.poll();
			}
			
			//4-1-2. 해당 16진수가 이미 있으면 pass
			if(resultHex.contains(calNum)) {
				for(char token : calNum.toCharArray()) {
					nums.offer(token);
				}
				continue;
			}
			
			//4-1-3. 없으면 16진수와 16진수를 10진수로 변환한 값 둘 다 저장
			resultHex.add(calNum);
			resultNum.add(Integer.parseInt(calNum, 16));
			
			//4-1-4. 계산한 16진수는 다시 큐에 넣어주기
			for(char token : calNum.toCharArray()) {
				nums.offer(token);
			}
		}
	}
}