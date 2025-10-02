import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int size = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean isValid = false;
		Stack<Integer> stack = new Stack<>();
		int num = 1;
		int i = 0;
		while (true) {
			if(num <= size) {
				if(stack.isEmpty() || stack.peek() < arr[i]) {
					stack.push(num++);
					sb.append("+").append("\n");
				}
			}
			
			if(stack.peek() > arr[i]) {
				isValid = false;
				break;
			}
			
			if(stack.peek() == arr[i]) {
				stack.pop();
				i++;
				sb.append("-").append("\n");
			}
			
			if(num >= size && stack.isEmpty()) {
				isValid = true;
				break;
			}
		}
		
		if(isValid) {
			System.out.println(sb);
		}
		else {
			System.out.println("NO");
		}
	}
}
