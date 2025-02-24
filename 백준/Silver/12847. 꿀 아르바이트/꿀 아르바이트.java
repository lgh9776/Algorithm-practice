import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static long[] sum;
	static int totalDay;
	static int canDay;
	static long max;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		totalDay = Integer.parseInt(st.nextToken());
		canDay = Integer.parseInt(st.nextToken());
		sum = new long[totalDay+1];
		max = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for (int day = 1; day <= totalDay; day++) {
			sum[day] = sum[day-1] + Integer.parseInt(st.nextToken());
			
			if(day >= canDay) {
				long now = sum[day] - sum[day-canDay];
				max = max < now ? now : max; 
			}
		}
		
		System.out.println(max);
	}

}
