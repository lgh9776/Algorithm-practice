import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int numCnt = Integer.parseInt(st.nextToken());
		
		Set<Long> numSet = new HashSet<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < numCnt; i++) {
			numSet.add(Long.parseLong(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		int userNumCnt = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < userNumCnt; i++) {
			if(numSet.contains(Long.parseLong(st.nextToken()))) {
				sb.append(1).append(" ");
			}
			else {
				sb.append(0).append(" ");
			}
		}
		System.out.println(sb);
	}
}
