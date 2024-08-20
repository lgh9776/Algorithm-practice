import java.io.IOException;
import java.util.Scanner;

/*
 * 이전 요소가 현재 요소보다 크고, 현재 요소가 다음 요소보다 작을 때
 * 	고려대상 '우뚝 선 산' 선정, 고려대상 해제
 * 이전 요소가 현재 요소보다 크고, 현재 요소가 다음 요소보다 클 때
 * 	고려대상 '우뚝 선 산' 선정
 * => isSmall은 이전 비교 상태를 나타냄 -> 이전 요소 기준 다음 요소보다 작은지? 
 * 
 * 1. 테스트 케이스 입력 받기
 * 2. 요소 개수 입력 받기
 * 3. 요소의 높이 입력 받기
 * 4. 요소 개수만큼 반복
 * 5. 현재 요소가 다음 요소보다 작으면
 * 	5-1. isSmall이 1일 경우 (이전 요소가 현재 요소보다 작았을 때)
 * 	5-2. isSmall이 0일 경우 (이전 요소가 현재 요소보다 클 때)
 * 		5-2-1. '우뚝 선 산' 추가
 * 		5-2-2. 고려대상 해제
 * 6. 현재 요소가 다음 요소보다 크면
 * 	6-1. isSmall이 1일 경우 (이전 요소가 현재 요소보다 작았을 때)
 * 		6-1-1. 고려대상 추가
 * 	6-2. isSmall이 0일 경우 (이전 요소가 현재 요소보다 클 때)
 * 		6-2-1. '우뚝 선 산' 추가
 * 7. 마지막 요소 처리
 */

public class Solution {
	static int elementCnt;
	static int[] element;
	static int tempStand; //고려대상
	static int stand; //우뚝 선 산 개수
	static int isSmall;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		//1. 테스트 케이스 입력 받기
		int test_case = sc.nextInt();
		for(int testCase = 1; testCase <= test_case; testCase++) {
			//2. 요소 개수 입력 받기
			elementCnt = sc.nextInt();
			tempStand = 0;
			stand = 0;
			isSmall = -1;
			
			element = new int[elementCnt];
			for(int index = 0; index < elementCnt; index++) {
				//3. 요소의 높이 입력 받기
				element[index] = sc.nextInt();
			}
			
			//4. 요소 개수만큼 반복
			for(int index = 0; index < elementCnt; index++) {
				//5. 현재 요소가 다음 요소보다 작으면
				if(index + 1 < elementCnt && element[index] < element[index+1]) {
					//5-1. isSmall이 0일 경우 (이전 요소가 현재 요소보다 클 때)
					if(isSmall == 0){
						//5-2-1. '우뚝 선 산' 추가
						stand += tempStand;
						//5-2-2. 고려대상 해제
						tempStand = 0;
					}
					//5-2. isSmall이 1일 경우 (이전 요소가 현재 요소보다 작을 때)
					else if(isSmall == 1) {
						tempStand++;
					}
					isSmall = 1;
				}
				//6. 현재 요소가 다음 요소보다 크면
				else if(index + 1 < elementCnt && element[index] > element[index+1]){
					//6-1. isSmall이 1일 경우 (이전 요소가 현재 요소보다 작았을 때)
					if(isSmall == 1) {
						//6-1-1. 고려대상 추가
						tempStand++;
					}
					//6-2. isSmall이 false일 경우 (이전 요소가 현재 요소보다 클 때)
					else if(isSmall == 0) {
						//6-2-1. '우뚝 선 산' 추가
						stand += tempStand;
					}
					isSmall = 0;
				}
			}
			//7. 마지막 요소 처리
			//7-1. isSmall이 false일 경우 (이전 요소가 현재 요소보다 클 때)
			if(isSmall == 0) {
				stand += tempStand;
			}
			
			System.out.println("#" + testCase + " " + stand);
		}
	}
}
