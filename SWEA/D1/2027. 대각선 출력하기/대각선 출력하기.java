/*
 * 1. row, col을 5로 이중 반복
 * 2. 대각선(row = col일 경우)일 때 #출력
 * 3. 나머지는 +출력
 */

public class Solution {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		
		//1. row, col을 5로 이중 반복
		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 5; col++) {
				//2. 대각선(row = col일 경우)일 때 #출력
				if(row == col) {
					sb.append("#");
					continue;
				}
				//3. 나머지는 +출력
				sb.append("+");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}