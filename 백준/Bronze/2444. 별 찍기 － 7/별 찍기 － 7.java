import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		//삼각형 출력 - 1 ~ n까지
		for(int line = 1; line <= n; line++) {
			//공백 - line번째 줄 n - line개 출력
			for(int space = 0; space < n - line; space++) {
				System.out.print(" ");
			}
			
			//별 찍기 - line번째 줄 2*(n-line) - 1개 별 찍기
			for(int star = 0; star < 2 * line - 1; star++) { 
				System.out.print("*");
			}
			System.out.println();
		}
		
		//역삼각형 출력 - n-1 ~ 1까지
		for(int line = n - 1; line > 0; line--) {
			//공백 - line번째 줄 line개 출력
			for(int space = 0; space < n - line; space++) {
				System.out.print(" ");
			}
			
			//별 찍기 - line번째 줄 2* line - 1개 별 찍기
			for(int star = 0; star < 2 * line - 1; star++) { 
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
