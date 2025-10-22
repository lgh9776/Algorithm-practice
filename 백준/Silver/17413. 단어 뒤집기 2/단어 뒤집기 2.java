import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Stack<Character> s = new Stack<>();
		char[] str = br.readLine().toCharArray();
		
		for (int i = 0; i < str.length; i++) {
			if(str[i] == ' ') {
				int size = s.size();
				for (int j = 0; j < size; j++) {
					sb.append(s.pop());
				}
				sb.append(" ");
				continue;
			}
			else if(str[i] == '<') {
				while(!s.isEmpty()) {
					sb.append(s.pop());
				}
				
				while(str[i] != '>') {
					sb.append(str[i]);
					i++;
				}
				sb.append(str[i]);
				continue;
			}
			s.push(str[i]);
		}
		
		while(!s.isEmpty()) {
			sb.append(s.pop());
		}
		
		System.out.println(sb);
	}

}