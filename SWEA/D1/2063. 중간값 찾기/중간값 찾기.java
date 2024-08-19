import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 1. 입력 받을 수의 개수 count 입력 받기
 * 2. List에 count개의 숫자 입력 받기
 * 3. sort하기
 * 4. 중간값 찾기 = 중간 인덱스의 값
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		//1. 입력 받을 수의 개수 count 입력 받기
		int count = Integer.parseInt(st.nextToken());
		
		//2. List에 count개의 숫자 입력 받기
		ArrayList<Integer> nums = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int index = 0; index < count; index++) {
			nums.add(Integer.parseInt(st.nextToken()));
		}
		
		//3. sort하기
		Collections.sort(nums);
		
		//4. 중간값 찾기 = 중간 인덱스의 값
		System.out.print(nums.get(count/2));
	}
}
