import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 사이즈 입력 받기
 * 2. 방문해야할 좌표 입력 받기
 * 3. z모양 탐색을 위해 재귀 호출 (좌측 상단부터 시작)
 * 4. 좌표가 목표 좌표와 같은지 확인
 * 	4-1. 동일하면 숫자 출력 후 return (결과!)
 * 5. 현재 구간에 목표 좌표가 있으면
 * 	5-1. 해당 구간을 4등분해서 각 구간에 목표 좌표가 있는지 확인
 * 	5-2. 목표 좌표가 없으면 연산을 줄이기 위해 해당 구간의 크기를 더하고 넘김
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static int mapSize;
	static int pointRow, pointCol;
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		inputData();
		
		//3. z모양 탐색을 위해 재귀 호출
		devideSpace(0, 0, mapSize);
	}
	
	static void inputData() {
		//1. 사이즈 입력 받기
		int size = Integer.parseInt(st.nextToken());
		mapSize = (int) Math.pow(2, size);
		
		//2. 방문해야할 좌표 입력 받기
		pointRow = Integer.parseInt(st.nextToken());
		pointCol = Integer.parseInt(st.nextToken());
	}
	
	static void devideSpace(int row, int col, int size) {
		//4. 좌표가 목표 좌표와 같은지 확인
		if(row == pointRow && col == pointCol) {
			//4-1. 동일하면 숫자 출력 후 return (결과!)
			System.out.println(count);
			return;
		}
		
		int half = size / 2;
		//5. 현재 구간에 목표 좌표가 있으면
		if (row <= pointRow && pointRow < row + size && col <= pointCol && pointCol < col + size) {
			//5-1. 해당 구간을 4등분해서 각 구간에 목표 좌표가 있는지 확인
			devideSpace(row, col, half);
			devideSpace(row, col+half, half);
			devideSpace(row+half, col, half);
			devideSpace(row+half, col+half, half);
		} else {
			//5-2. 목표 좌표가 없으면 연산을 줄이기 위해 해당 구간의 크기를 더하고 넘김
			count += (size * size);
		}
	} 
}
