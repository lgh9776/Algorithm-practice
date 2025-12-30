import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Long> pq = new PriorityQueue<>(
			(a, b) -> {
				if(Math.abs(a) != Math.abs(b)) {
					return Long.compare(Math.abs(a), Math.abs(b));
				}
				return Long.compare(a, b);
			}
		);
				
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			long next = Long.parseLong(st.nextToken());
			
			if(next == 0) {
				if(!pq.isEmpty()) {
					sb.append(pq.poll()).append("\n");
				}
				else {
					sb.append(0).append("\n");
				}
			}
			else {
				pq.offer(next);
			}
		}
		System.out.println(sb);
	}
}