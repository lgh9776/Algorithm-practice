import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 해결 방법 : 도착점에서 시작해서 출발점을 찾는다
 * 1. 도착점을 시작 위치로 잡기 위해 마지막 줄에서 2가 저장된 y좌표를 찾는다.
 * 2. 출발점을 찾기 위해 row--하며 위로 계속 올라간다.
 * 3. 위로 올라가면서 좌우에 1이 있는지 확인
 *    3-1. 좌측에 1이 있으면, 다음 좌측 값이 1이 아닐 때까지 반복
 *    3-2. 우측에 1이 있으면, 다음 우측 값이 1이 아닐 때까지 반복
 * 4. row = 0일 때 break -> 이 시점의 col값이 출발점(result)
 */

public class Solution {
	public static final int LADDER_SIZE = 100;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int[][] ladder = new int[LADDER_SIZE][LADDER_SIZE];
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			//테스트 케이스 입력받기
			String testCaseNumber = br.readLine();
			
			//사다리 정보 입력받기
			int now_y = 0;
			for(int row = 0; row < LADDER_SIZE; row++) {
				st = new StringTokenizer(br.readLine().trim());
				
				for(int col = 0; col < LADDER_SIZE; col++) {
					ladder[row][col] = Integer.parseInt(st.nextToken());
					
					if(ladder[row][col] == 2) {
						now_y = col;
					}
				}
			}
			
			//2. 출발점을 찾기 위해 row--하며 위로 계속 올라간다.
			for(int row = LADDER_SIZE - 2; row > 0; row--) {
				
				//3-1. 좌측에 1이 있으면,
				if(now_y - 1 >= 0 && ladder[row][now_y - 1] == 1) {
					while(now_y - 1 >= 0) { //배열 범위 넘어가지 않도록
						if(ladder[row][now_y-1] == 1) { //다음 좌측 값이 1이면 이동
							ladder[row][now_y] = 0; //지나간 사다리는 없앰
							now_y--; //이동
						}
						else if(ladder[row][now_y-1] == 0) { //다음 좌측 값이 0이면 이동하지 않고 멈춤
							break;
						}
					}
				}
				
				//3-2. 우측에 1이 있으면,
				if(now_y + 1 < LADDER_SIZE && ladder[row][now_y + 1] == 1) {
					while(now_y + 1 < LADDER_SIZE) { //배열 범위 넘어가지 않도록
						if(ladder[row][now_y+1] == 1) { //다음 우측 값이 1이면 이동
							ladder[row][now_y] = 0; //지나간 사다리는 없앰
							now_y++; //이동
						}
						else if(ladder[row][now_y+1] == 0) { //다음 우측 값이 0이면 이동하지 않고 멈춤
							break;
						}
					}
				}
				
			}
			
			////4. row = 0이 되면  break되어 나옴 이 시점의 now_y값이 출발점(result) 출력
			sb.append("#").append(test_case).append(" ").append(now_y).append("\n");
		}
		System.out.print(sb);
	}
}
