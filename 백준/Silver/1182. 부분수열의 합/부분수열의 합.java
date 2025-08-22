import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int[] nums;
	static int N, S;
	static int count;

	public static void main(String[] args) throws IOException {
		inputData();
		findSum(0, 0, 0);
		System.out.println(count);
	}

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		count = 0;
	}

	static void findSum(int sum, int choiceCnt, int index) {
		if(index == N && choiceCnt > 0 && S == sum) {
			count++;
		}
		
		if(index >= N) {
			return;
		}

		findSum(sum + nums[index], choiceCnt + 1, index + 1);
		findSum(sum, choiceCnt, index + 1);
	}
}