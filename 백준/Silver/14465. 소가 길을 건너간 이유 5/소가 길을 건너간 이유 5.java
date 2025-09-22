import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int[] a = new int[N+1];
		int num = 0;
		for (int i = 0; i < B; i++) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			a[num] = -1;
		}
		
		for (int i = 1; i <= N; i++) {
			if(a[i] == -1) {
				a[i] = a[i-1];
				continue;
			}
			a[i] = a[i-1] + 1;
		}
		
		int count = 0;
		int min = Integer.MAX_VALUE;
		for (int i = K; i <= N; i++) {
			count = K - (a[i] - a[i-K]);
			min = count < min ? count : min;	
		}
		System.out.println(min);
	}
}