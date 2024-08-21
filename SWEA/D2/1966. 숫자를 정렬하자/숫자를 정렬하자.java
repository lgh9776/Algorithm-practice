import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 입력 받을 숫자의 수 입력 받기
 * 3. 숫자 입력 받기
 * 4. 정렬하기
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			//2. 입력 받을 숫자의 수 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			int count = Integer.parseInt(st.nextToken());
			int[] nums = new int[count];
			
			//3. 숫자 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			for(int index = 0; index < count; index++) {
				nums[index] = Integer.parseInt(st.nextToken());
			}
			
			//4. 정렬하기
			Arrays.sort(nums);
			
			//출력
			for(int index = 0; index < count; index++) {
				sb.append(nums[index]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}
