import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static char[] str;
	static char[] word;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		str = st.nextToken().toCharArray();
		int strLen = str.length;
		
		st = new StringTokenizer(br.readLine());
		word = st.nextToken().toCharArray();
		int wordLen = word.length;
	
		Stack<Character> result = new Stack<>();
		
		for (int index = 0; index < strLen; index++) {
			result.push(str[index]);
			
			//스택에 문자를 넣을 때마다 제거할 문자열이 생기는지 확인 후 바로 제거
			if(result.size() >= wordLen) {
				boolean isSame = true; 
				
				for (int i = 0; i < wordLen; i++) {
					if(result.get(result.size() - wordLen + i) != word[i]) {
						isSame = false;
						break;
					}
				}
				
				if(isSame) {
					for (int i = 0; i < wordLen; i++) {
						result.pop();
					}					
				}
			}
		}
		
		if(result.size() == 0) {
			sb.append("FRULA");
		}
		else {
			for (Character c : result) {
				sb.append(c);
			}
		}
		System.out.println(sb);
	}
}