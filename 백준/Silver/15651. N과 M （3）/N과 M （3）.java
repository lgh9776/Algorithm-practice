import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int num, len;
	static Stack<Integer> selected;

	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		num = Integer.parseInt(st.nextToken());
		len = Integer.parseInt(st.nextToken());
		selected = new Stack<Integer>();

		findNums();
		
		System.out.println(sb);
	}
	
	static void findNums() {
		if(selected.size() == len) {
			for (int i = 0; i < selected.size(); i++) {
				sb.append(selected.get(i)).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= num; i++) {
			selected.push(i);
			findNums();
			selected.pop();
		}
	}
}