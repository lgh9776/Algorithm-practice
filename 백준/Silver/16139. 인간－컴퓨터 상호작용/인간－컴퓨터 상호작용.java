import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static char[] sentence;
	static int[] counts;
	static Map<Character, int[]> wordCounts;
	
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		sentence = st.nextToken().toCharArray();
		
		st = new StringTokenizer(br.readLine());
		int questionCount = Integer.parseInt(st.nextToken());
		
        wordCounts = new HashMap<>();
		char targetWord;
		int start, end;
		for (int i = 0; i < questionCount; i++) {
			st = new StringTokenizer(br.readLine());
			targetWord = st.nextToken().charAt(0);
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			findAnswer(targetWord, start, end);
		}
		System.out.println(sb);
	}
	
	static void findAnswer(char t, int s, int e) {

		if(!wordCounts.isEmpty() && wordCounts.containsKey(t)) {
			counts = wordCounts.get(t);
			if(s == 0)
				sb.append(counts[e]).append("\n");
			else
				sb.append(counts[e] - counts[s-1]).append("\n");
			return;
		}
		
		int num = 0;
		counts = new int[sentence.length];
		for (int i = 0; i < sentence.length; i++) {
			if(t == sentence[i]) {
				num++;
			}
			counts[i] = num;
		}
		
		wordCounts.put(t, counts);
		if(s == 0)
			sb.append(counts[e]).append("\n");
		else
			sb.append(counts[e] - counts[s-1]).append("\n");
	}
}