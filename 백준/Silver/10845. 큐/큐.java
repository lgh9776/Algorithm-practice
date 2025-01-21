import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cnt = Integer.parseInt(st.nextToken());
		
		Deque<Integer> q = new ArrayDeque<>();
		for (int index = 0; index < cnt; index++) {
			st = new StringTokenizer(br.readLine());
			String word = st.nextToken();
			
			if(word.equals("push")) {
				int num = Integer.parseInt(st.nextToken());
				q.offer(num);
				continue;
			}
			else if(word.equals("pop")) {
				if(q.isEmpty()) {
					sb.append(-1);
				}
				else {
					sb.append(q.poll());
				}
			}
			else if(word.equals("size")) {
				sb.append(q.size());
			}
			else if(word.equals("empty")) {
				if(q.isEmpty()) {
					sb.append(1);
				}
				else {
					sb.append(0);
				}
			}
			else if(word.equals("front")) {
				if(!q.isEmpty()) {
					sb.append(q.getFirst());
				}
				else {
					sb.append(-1);
				}
			}
			else if(word.equals("back")) {
				if(!q.isEmpty()) {
					sb.append(q.getLast());
				}
				else {
					sb.append(-1);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
