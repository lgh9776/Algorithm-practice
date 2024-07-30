import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		for(int line = 0; line < n; line++) {
			//별 찍기 - n번째 줄 n - line개 별 찍기
			for(int star = 0; star < n - line; star++) { 
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
