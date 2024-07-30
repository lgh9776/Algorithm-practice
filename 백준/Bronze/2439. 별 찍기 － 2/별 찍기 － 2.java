import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		for(int line = 1; line <= n; line++) {
			//공백 출력 - n번째 줄 n -line개 출력
			for(int space = 0; space < n - line; space++) {
				System.out.print(" ");
			}
			
			//별 찍기 - n번째 줄 n개 별 찍기
			for(int star = 1; star <= line; star++) { 
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
