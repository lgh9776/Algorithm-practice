import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 물품 개수, 최대 무게 입력 받기
 * 2. 각 물품의 무게, 가치 입력 받기
 * 3. 고려할 물건의 개수를 1개씩 늘려감
 * 4. 가방의 무게를 최대무게까지 1씩 늘림
 * 5. 현재 물건을 넣을 수 있는 경우
 * 6. 해당 물건의 무게를 뺀 이전 물건까지 고려한 최적값
 * 7. 고려할 대상의 물건을 넣었을 때 vs 넣지 않았을 때 중 가치가 큰 것 저장
 * 8. 모든 물건, 최대 무게까지 고려한 것 출력
 * 
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Stuff{
		int weight;
		int value;
		
		public Stuff(int weight, int value) {
			super();
			this.weight = weight;
			this.value = value;
		}
	}
	
	static int stuffCnt;
	static int maxWeight;
	static Stuff[] stuffs;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		inputData();
		
		//3. 고려할 물건의 개수를 1개씩 늘려감
		//4. 가방의 무게를 최대무게까지 1씩 늘림
		dp = new int[stuffCnt+1][maxWeight+1];
		for (int itemIndex = 1; itemIndex <= stuffCnt; itemIndex++) {
			for (int weightIndex = 1; weightIndex <= maxWeight; weightIndex++) {
				int inNowStuffValue = 0;
				//5. 현재 물건을 넣을 수 있는 경우
				if(weightIndex >= stuffs[itemIndex].weight) {
					
					//고려할 대상의 물건 가치
					inNowStuffValue = stuffs[itemIndex].value;
					
					//6. 해당 물건의 무게를 뺀 이전 물건까지 고려한 최적값
					int restWeight = weightIndex - stuffs[itemIndex].weight;
					inNowStuffValue += dp[itemIndex-1][restWeight];
				}
				//7. 고려할 대상의 물건을 넣었을 때 vs 넣지 않았을 때 중 가치가 큰 것 저장
				dp[itemIndex][weightIndex] = Math.max(dp[itemIndex-1][weightIndex], inNowStuffValue);
			}
		}
		//8. 모든 물건, 최대 무게까지 고려한 것 출력
		System.out.println(dp[stuffCnt][maxWeight]);
	}

	static void inputData() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		//1. 물품 개수, 최대 무게 입력 받기
		stuffCnt = Integer.parseInt(st.nextToken());
		maxWeight = Integer.parseInt(st.nextToken());
		
		//2. 각 물품의 무게, 가치 입력 받기
		stuffs = new Stuff[stuffCnt+1];
		for (int index = 1; index <= stuffCnt; index++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			stuffs[index] = new Stuff(weight, value);
		}
	}

}
