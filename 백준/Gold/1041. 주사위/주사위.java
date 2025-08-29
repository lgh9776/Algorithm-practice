import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/* 
 * 1면 나오는 주사위 개수
 * 	- (각 면에서) 모서리를 제외한 중간 부분 : (n-2) * (n-2) * 5
 * 	- 최하단에 있는 (꼭짓점 제외) 모서리 부분 : (n-2) * 4
 * 2면 나오는 주사위 개수
 * 	- 최상단에 있는 모서리 부분 : (n-2) * 4
 * 	- (최상단 제외) 각 층의 꼭짓점 부분 : (n-1) * 4
 * 3면 나오는 주사위 개수
 * 	- 최상단 꼭짓점 : 4
 * 
 */

public class Main {
	
	static ArrayList<Integer> nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Integer.parseInt(st.nextToken());
		
		nums = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 6; i++) {
			nums.add(Integer.parseInt(st.nextToken()));
		}
		
		if(N == 1) {
			nums.remove(Collections.max(nums));
			
			int sum = 0;
			for (int i = 0; i < nums.size(); i++) {
				sum += nums.get(i);
			}
			System.out.println(sum);
		}
		else {
			int first = Collections.min(nums);
			findMirror(first);
			int second = Collections.min(nums);
			findMirror(second);
			int third = Collections.min(nums);

			long one = (5 * N * N) - (16 * N) + 12;
			one *= first;
			long two = (8 * N) - 12;
			two *= (first + second);
			long three = 4 * (first + second + third);
			
			System.out.println(one + two + three);
		}
	}
	
	static void findMirror(int select) {
		int idx1 = nums.indexOf(select);
		nums.remove(idx1);
		nums.add(idx1, 100);
		
		int idx2 = 0;
		if(idx1 == 0 || idx1 == 5)
			idx2 = idx1 == 0 ? 5 : 0;
		else if(idx1 == 1 || idx1 == 4)
			idx2 = idx1 == 1 ? 4 : 1;
		else if(idx1 == 2 || idx1 == 3)
			idx2 = idx1 == 2 ? 3 : 2;
		nums.remove(idx2);
		nums.add(idx2, 100);
	}
	
}