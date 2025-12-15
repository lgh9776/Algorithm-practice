import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			int count = 0;
			for (int j = 0; j < N; j++) {
				if(i == j) {
					continue;
				}
				
				if(map[i][j] == 'Y') {
					count++;
				}
				else if(map[i][j] == 'N') {
					for (int k = 0; k < N; k++) {
						if(map[i][k] == 'Y' && map[k][j] == 'Y') {
							count++;
							break; // 이미 둘은 2-친구 관계이므로 break
						}
					}
				}
			}
			
			if(count > max) {
				max = count;
			}
		}
		
		if(max == Integer.MIN_VALUE) {
			System.out.println(0);
		}
		else {
			System.out.println(max);
		}
	}
}