import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		for(int line = 0; line < n; line++) {
			//공백 - line번째 줄 line개 출력
			for(int space = 0; space < line; space++) {
				System.out.print(" ");
			}
			
			//별 찍기 - line번째 줄 2*(n-line) - 1개 별 찍기
			for(int star = 0; star < 2 * (n - line) - 1; star++) { 
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
