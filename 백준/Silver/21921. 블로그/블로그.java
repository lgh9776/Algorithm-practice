import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int day = Integer.parseInt(st.nextToken());
		int continous = Integer.parseInt(st.nextToken());
		
		long[] sum = new long[day+1];
		long max = 0;
		int daysCnt = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= day; i++) {
			sum[i] = Integer.parseInt(st.nextToken()) + sum[i-1];
			
			// 연속일 중 방문자 수가 높은 것 체크
			if(i >= continous) {
				long nowSum = sum[i]-sum[i-continous];
				if(nowSum > max) {
					max = nowSum;
					daysCnt = 1;
				}
				else if(nowSum == max) {
					daysCnt++;
				}
			}
		}
		
		if(max == 0) {
			System.out.println("SAD");
		}
		else {
			System.out.println(max + "\n" + daysCnt);
		}
	}

}
