import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		
		int cnt = Integer.parseInt(st.nextToken());
		int[] nums = new int[cnt+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= cnt; i++) {
			nums[i] = Integer.parseInt(st.nextToken()) + nums[i-1];
		}
		
		st = new StringTokenizer(br.readLine());
		int areaCnt = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < areaCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int a1 = Integer.parseInt(st.nextToken());
			int a2 = Integer.parseInt(st.nextToken());
			
			sb.append(nums[a2] - nums[a1-1]).append("\n");
		}
		System.out.println(sb);
	}
}
