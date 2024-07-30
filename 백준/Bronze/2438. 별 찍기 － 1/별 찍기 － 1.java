import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		for(int line = 1; line <= n; line++) {
			for(int starNum = 1; starNum <= line; starNum++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
