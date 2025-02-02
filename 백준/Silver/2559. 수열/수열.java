import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		int days = Integer.parseInt(st.nextToken());
		int continuous = Integer.parseInt(st.nextToken());
		
		long sum[] = new long[days+1];
		long max = Integer.MIN_VALUE;
		
		// 누적합 구하기
		st = new StringTokenizer(br.readLine());
		for (int day = 1; day <= days; day++) {
			sum[day] = sum[day-1] + Integer.parseInt(st.nextToken());
			
			if(day >= continuous) {
				max = max < sum[day] - sum[day-continuous] ? sum[day] - sum[day-continuous] : max;
			}
		}	
		System.out.println(max);
	}
}
