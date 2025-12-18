import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Map<String, Integer> map = new TreeMap<>();
		
		String s;
		int cnt = 0;
		while((s = br.readLine()) != null) {
			cnt++;
			
			if(!map.containsKey(s)) {
				map.put(s, 1);
			}
			else {
				map.put(s, map.get(s) + 1);
			}
		}

		final double total = cnt;
		map.forEach((key, value) ->
			sb.append(key).append(" ")
			  .append(String.format("%.4f", value / total * 100))
			  .append("\n")
		);
		
		System.out.println(sb);
	}
}