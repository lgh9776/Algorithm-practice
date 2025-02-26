import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 1. 원소의 개수, 원소들 입력 받기
 * 2. 2개의 수를 선택해 더하여 저장하는 배열 생성
 * 3. 2개의 수를 더한 배열의 원소에 1개의 수를 더한게 배열에 있는지 이분탐색
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static int arrSize;
	static long[] originArr;
	static Set<Long> twoSum;
	static long max;
	
	public static void main(String[] args) throws IOException {
		
		// 1. 원소의 개수, 원소들 입력 받기
		inputData();
		
		// 2. 2개의 수를 선택해 더하여 저장하는 배열 생성
		addTwoItem();
		
		// 3. a+b = d-c 를 찾을 때, d를 고정해두고 c값 찾기
		for (int i = 0; i < arrSize; i++) {
			long d = originArr[i];
			
			for (int j = 0; j < arrSize; j++) {
				if(originArr[i] > originArr[j] && twoSum.contains(originArr[i] - originArr[j])) {
					max = max < d ? d : max;
				}
			}
		}

		System.out.println(max);
	}
	

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		arrSize = Integer.parseInt(st.nextToken());
		originArr = new long[arrSize];
		
		for (int index = 0; index < arrSize; index++) {
			st = new StringTokenizer(br.readLine());
			originArr[index] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(originArr);
		twoSum = new HashSet<>();
		max = Long.MIN_VALUE;
	}

	static void addTwoItem() {
		for (int i = 0; i < arrSize; i++) {
			for (int j = 0; j < arrSize; j++) {
				twoSum.add(originArr[i] + originArr[j]);
			}
		}
	}
}