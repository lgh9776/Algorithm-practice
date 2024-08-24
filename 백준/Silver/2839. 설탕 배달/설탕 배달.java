import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1. 무게 입력 받기
 * 2. 5kg 봉지를 제일 많이 선택할 수 있게함
 * 3. 남은 무게에서 3kg 선택
 * 4. 해당 무게를 올길 수 없으면 -1출력
 * 5. 옮길 수 있으면 최소 봉지 수 갱신
 * 6. 5kg를 1봉지씩 줄이면서 계산
 * 
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 무게 입력 받기
		int weight = Integer.parseInt(br.readLine().trim());
		
		//2. 5kg 봉지를 제일 많이 선택할 수 있게함
		int current5kgCnt = weight / 5;
		int min = Integer.MAX_VALUE;
		while(current5kgCnt >= 0) {
			int currentTotalCnt = 0; //현재 총 봉지 수
			
			currentTotalCnt += current5kgCnt;
			//3. 남은 무게에서 3kg 선택
			int current3kgCnt = (weight - current5kgCnt * 5) / 3;
			currentTotalCnt += current3kgCnt;
			
			//4. 해당 무게를 올길 수 없으면 -1출력
			if(current3kgCnt * 3 + current5kgCnt * 5 != weight) {
				current5kgCnt--;
				continue;
			}
			//5. 옮길 수 있으면 최소 봉지 수 갱신
			else {
				min = Math.min(min, currentTotalCnt);
			}
			
			//6. 5kg를 1봉지씩 줄이면서 계산
			current5kgCnt--;
		}
		
		if(min != Integer.MAX_VALUE)
			System.out.println(min);
		else
			System.out.println(-1);
	}
}
