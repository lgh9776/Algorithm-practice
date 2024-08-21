import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 퍼즐 데이터 입력 받기
 * 3. row 기준 1줄씩 1~10까지 없는 수가 있는지 확인
 * 4. col 기준 1줄씩 1~10까지 없는 수가 있는지 확인
 * 5. 네모 박스 기준 1칸씩 1~10까지 없는 수가 있는지 확인
 * (중복된 수가 있으면 없는 수가 있음)
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int[][] map = new int[9][9];
	static int result;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			result = 1;
			
			//2. 퍼즐 데이터 입력 받기
			for(int row = 0; row < 9; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int col = 0; col < 9; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			checkMap();
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	static boolean findFalse(boolean[] includeNum) {
		for(int index = 0; index < 9; index++) {
			if(includeNum[index] == false)
				return false;
		}
		return true;
	}
	
	static void checkMap() {
		//3. row 기준 1줄씩 1~10까지 없는 수가 있는지 확인
		for(int row = 0; row < 9; row++) {
			boolean[] isOn = new boolean[9];
			for(int col = 0; col < 9; col++) {
				isOn[map[row][col] - 1] = true;
			}
			if(!findFalse(isOn)) {
				result = 0;
				return;
			}
		}
		
		//4. col 기준 1줄씩 1~10까지 없는 수가 있는지 확인
		for(int col = 0; col < 9; col++) {
			boolean[] isOn = new boolean[9];
			for(int row = 0; row < 9; row++) {
				isOn[map[row][col] - 1] = true;
			}
			if(!findFalse(isOn)) {
				result = 0;
				return;
			}
		}
		
		//5. 네모 박스 기준 1칸씩 1~10까지 없는 수가 있는지 확인
		for(int box = 0; box < 3; box++) {
			boolean[] isOn = new boolean[9];
			for(int row = 3*box; row < (box+1)*3; row++) {
				for(int col = 3*box; col < (box+1)*3; col++) {
					isOn[map[row][col] - 1] = true;
				}
			}
			if(!findFalse(isOn)) {
				result = 0;
				return;
			}
		}
	}
}
