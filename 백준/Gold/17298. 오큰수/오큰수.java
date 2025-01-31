import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 기준값보다 오른쪽, 클 것, 가장 왼쪽
 * 
 * 1. 수열의 크기 입력 받기
 * 2. 출력 배열 초기화
 * 3. 수열의 값 입력 받기
 * 4. 스택이 비어있으면 입력값 push
 * 5. top보다 작으면 push
 * 6. 스택의 top보다 입력값이 크면
 * 	6-1. pop 후 출력 배열의 해당 아이템 인덱스에 입력값 저장
 * 	6-2. 입력값&인덱스 push
 * 7. 출력 배열 sb에 넣어서 한번에 출력
 * 
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Item{
		int index;
		int num;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 수열의 크기 입력 받기
		st = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(st.nextToken());
		
		//2. 출력 배열 초기화
		int[] output = new int[size];
		for (int i = 0; i < size; i++) {
			output[i] = -1;
		}
		
		Stack<Item> s = new Stack<Item>();
		
		//3. 수열의 값 입력 받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < size; i++) {
			Item item = new Item();
			
			item.index = i;
			item.num = Integer.parseInt(st.nextToken());
			
			// 4. 스택이 비어있으면 입력값 push
			if(s.isEmpty()) {
				s.push(item);
				continue;
			}
			
			// 5. top보다 작으면 push
			if(s.peek().num >= item.num) {
				s.push(item);
				continue;
			}
			
			// 6. 스택의 top보다 입력값이 크면
			while(!s.isEmpty() && s.peek().num < item.num) {
				// 6-1. pop 후 출력 배열의 해당 아이템 인덱스에 입력값 저장
				Item popItem = s.pop();
				output[popItem.index] = item.num;
			}
			// 6-2. 입력값&인덱스 push
			s.push(item);
		}
		
		// 7. 출력 배열 sb에 넣어서 한번에 출력
		for (int num : output) {
			sb.append(num).append(" ");
		}
		
		System.out.println(sb);
	}
}