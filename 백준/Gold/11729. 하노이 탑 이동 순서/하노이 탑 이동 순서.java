import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	
	static int count;
	
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());
		count = 0;
		
		hanoi(size, 1, 2, 3);
		
		System.out.println(count);
		System.out.println(sb);
	}
	
	static void hanoi(int n, int from, int mid, int to) {
		if(n == 1) {
			sb.append(from).append(" ").append(to).append("\n");
			count++;
			return;
		}
		
		hanoi(n - 1, from, to, mid);
		sb.append(from).append(" ").append(to).append("\n");
		count++;
		hanoi(n - 1, mid, from, to);
	}
}