import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		//상단부
		for(int line = 0; line < n; line++) {
			for(int star = 0; star < 2*n-1 - line; star++) {
				if(star < line) {
					System.out.print(" ");
				}
				else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		
		//하단부
		for(int line = 1; line <= n - 1; line++) {
			for(int star = 0; star < n + line; star++) {
				if(star < n - line - 1) {
					System.out.print(" ");
				}
				else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
	}
}
