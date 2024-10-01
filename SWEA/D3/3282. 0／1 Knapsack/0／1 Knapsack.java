import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 물건 1번부터 n번까지 고려
 * 무게 0kg부터 최대 무게까지 고려
 * => 2차원 배열에 저장
 * 
 * 현재 선택된 물건에 대해
 * - 물건을 넣지 않는 경우 -> 현재 고려하는 물건 이전까지의 물건을 고려했을 때 (=이전 행) 최적해
 * - 물건을 넣는 경우 -> 현재 물건이 무조건 들어간다는 가정이 있어야 함 
 * 	 -> 현재 물건 넣기 (현재 고려하는 무게-현재 물건 무게), 총 가치 + 현재 물건의 가치
 * 	 -> 현재 고려하는 물건 이전까지 고려한 것(=이전 행)의 현재 고려하는 무게의 최적해 사용   
 * => 2가지의 경우를 비교하여 최적해를 저장
 * 
 * 1. 테스트 케이스 입력 받기
 * 2. 물건의 개수, 가방의 부피 입력 받기
 * 3. 각 물건의 부피, 물건의 가치 입력 받기
 * 4. DP를 이용하기 위해 각 최적해 값을 저장할 2차원 배열 생성 [물건수][부피수]
 * 5. 물건을 넣지 않는 경우 (이전 행, 해당 무게) = 최적해 1
 * 6. 물건을 넣는 경우 (이전 행, 현재 무게 - 현재 물건 무게) + 현재 물건 가치 = 최적해 2
 * 7. 둘을 비교해서 더 가치가 큰 값 2차원 배열에 저장
 * 8. 마지막 행, 마지막 열이 정답
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class ItemInfo{
		int size;
		int value;
	}
	
	static int itemCnt;
	static int bagSize;
	static ItemInfo[] items;
	static int[][] result;
	

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			sb.append("#").append(tc).append(" ");
			
			inputData();
			knapsack();
			
			//8. 마지막 행, 마지막 열이 정답
			sb.append(result[itemCnt][bagSize]).append("\n");
		}
		System.out.println(sb);
	}
	
	static void inputData() throws IOException {
		//2. 물건의 개수, 가방의 부피 입력 받기
		st = new StringTokenizer(br.readLine());
		itemCnt = Integer.parseInt(st.nextToken());
		bagSize = Integer.parseInt(st.nextToken());
		
		//3. 각 물건의 부피, 물건의 가치 입력 받기
		items = new ItemInfo[itemCnt+1];
		for (int index = 1; index <= itemCnt; index++) {
			st = new StringTokenizer(br.readLine());
			items[index] = new ItemInfo();
			items[index].size = Integer.parseInt(st.nextToken());
			items[index].value = Integer.parseInt(st.nextToken());
		}
		
		//4. DP를 이용하기 위해 각 최적해 값을 저장할 2차원 배열 생성 [물건수][부피수]
		result = new int[itemCnt+1][bagSize+1];
	}
	
	static void knapsack() {
		int inNowItemValue;
		int noNowItemValue;
		
		//첫번째 물건부터 n번째 물건까지 고려
		for (int itemIndex = 1; itemIndex <= itemCnt; itemIndex++) {
			//0kg부터 최대 부피까지 고려 (0kg에는 넣을 수 없으므로 통과)
			for (int size = 1; size <= bagSize; size++) {
				inNowItemValue = 0;
				noNowItemValue = 0;
				
				//해당 물건이 들어갈 수 있을 때
				if(size >= items[itemIndex].size) {
					//5. 물건을 넣는 경우 (이전 행, 현재 무게 - 현재 물건 무게) + 현재 물건 가치 = 최적해 2
					inNowItemValue += items[itemIndex].value; //현재 물건 가치 더하기
					
					int nowSize = size - items[itemIndex].size; //현재 물건을 제외했을 때 최적해
					inNowItemValue += result[itemIndex-1][nowSize];
				}
				
				//6. 물건을 넣지 않는 경우 (이전 행, 해당 무게) = 최적해 1
				noNowItemValue = result[itemIndex-1][size];
				
				//7. 둘을 비교해서 더 가치가 큰 값 2차원 배열에 저장
				result[itemIndex][size] = Math.max(inNowItemValue, noNowItemValue);
			}
		}
	}

}