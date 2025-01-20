import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 1. 탑의 수 입력 받기
 * 2. 탑의 수만큼 높이 입력 받기, 인덱스와 함께 저장
 * 3. 현재 탑보다 낮은 탑들 제거 (이후에 모두 현재 탑에 수신되기 때문)
 * 4. 스택이 비어있으면 0 출력
 * 5. 비어있지 않으면 스택의 최상위에 있는 탑 인덱스 출력 
 * 
 */

public class Main {
	
	static class Top{
		int height;
		int index;
		
		public Top(int height, int index) {
			this.height = height;
			this.index = index;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		//1. 탑의 수 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int topCnt = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Stack<Top> tops = new Stack<>();
		
		for (int index = 0; index < topCnt; index++) {
			//2. 탑의 수만큼 높이 입력 받기, 인덱스와 함께 저장
			Top nowTop = new Top(Integer.parseInt(st.nextToken()), index);
			
			//3. 현재 탑보다 낮은 탑들 제거 (이후에 모두 현재 탑에 수신되기 때문)
			while(!tops.isEmpty() && tops.peek().height < nowTop.height) {
				tops.pop();
			}
			
			//4. 스택이 비어있으면 0 출력
			if(tops.isEmpty()) {
				sb.append(0).append(" ");
			}
		
			//5. 비어있지 않으면 스택의 최상위에 있는 탑 인덱스 출력
			else {
				sb.append(tops.peek().index + 1).append(" ");
				
				//신호 수신을 위해 같은 높이는 남겨뒀지만 이후에는 필요 없으므로 제거
				if(nowTop.height == tops.peek().height) {
					tops.pop();
				}
			}
			tops.push(nowTop);
		}
		System.out.println(sb);
	}

}
