import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int caseCnt;
	static char[] func;
	static ArrayDeque<Integer> nums;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		caseCnt = Integer.parseInt(st.nextToken());
		nums = new ArrayDeque<>();
		
		for (int i = 0; i < caseCnt; i++) {
			inputData();
			
			boolean isReverse = false;
			boolean isError = false;
			for(char f : func) {
				if(f == 'R') {
					isReverse = !isReverse;
				}
				else if(f == 'D'){
					if(nums.size() == 0) {
						isError = true;
						break;
					}
					
					if(isReverse) {
						nums.pollLast();
					}
					else {
						nums.pollFirst();
					}			
				}
			}
			
			if(isError) {
				sb.append("error").append("\n");
				continue;
			}
			
			int size = nums.size();
			sb.append("[");
			if(isReverse){
				for (int j = 0; j < size; j++) {
					if(j == size-1) {
						sb.append(nums.pollLast());
						break;
					}
					sb.append(nums.pollLast()).append(",");	
				}
			}
			else {
				for (int j = 0; j < size; j++) {
					if(j == size-1) {
						sb.append(nums.pollFirst());
						break;
					}
					sb.append(nums.pollFirst()).append(",");	
				}
			}
			sb.append("]").append("\n");
			nums.clear();
		}
		System.out.println(sb);
	}
	
	static void inputData() throws IOException {
		st = new StringTokenizer(br.readLine());
		func = st.nextToken().toCharArray();
		
		st = new StringTokenizer(br.readLine());
		st = new StringTokenizer(br.readLine());
		String str = st.nextToken().replace("[", "").replace("]", "");
		if(str.equals("")) {
			return;
		}
		String[] charNum = str.split(",");
		for(String item : charNum) {
			nums.add(Integer.parseInt(item));
		}
	}
}