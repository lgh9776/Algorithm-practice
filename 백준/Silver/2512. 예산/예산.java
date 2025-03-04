import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int num;
	static long[] requestMoney;
	static long totalMoney;

	public static void main(String[] args) throws IOException {
		
		inputData();
		
		int count = 0;
		long max = -1;
		long perMoney;
		int i = 0;
		while(count != num) {
			perMoney = totalMoney / (num-count);
			if(perMoney >= requestMoney[i]) {
				totalMoney -= requestMoney[i];
				max = max < requestMoney[i] ? requestMoney[i] : max;
				count++;
			}
			else {
				break;
			}
			i++;
		}
		
		if(count == num) {
			System.out.println(max);
		}
		else {
			System.out.println(totalMoney / (num-count));
		}
	}
	
	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		num = Integer.parseInt(st.nextToken());
		requestMoney = new long[num];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < num; i++) {
			requestMoney[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(requestMoney);
		
		st = new StringTokenizer(br.readLine());
		totalMoney = Integer.parseInt(st.nextToken());
	}
}