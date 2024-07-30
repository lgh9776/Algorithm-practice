import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		//상단부
		for(int line = 1; line <= n; line++) {
			for(int star = 1; star <= 2 * n; star++) {
				//각 line은 별, 공백 합쳐서 2n개 찍힘
				//star는 각 line의 시작, 끝부분에 line개 출력됨
				//star가 line보다 작거나 같을때 별 출력 (시작부분에서 line개 출력)
				//star가 전체 출력 범위(2n)에서 - line보다 클 때 별 출력 
				//양 끝 범위를 지정해줘서 별 찍음
				if(star <= line || star > 2 * n - line) {
					System.out.print("*");
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
		//하단부 -  n-1줄만 반복
		for(int line = n - 1; line > 0; line--) {
			for(int star = 1; star <= 2 * n; star++) {
				//상단부와 동일하게 별, 공백 합쳐서 2n개 찍힘
				//양쪽 끝 범위 나눠서 별 출력
				//이번에도 별은 시작과 끝에 line개 출력됨
				if(star <= line || star > 2 * n - line) {
					System.out.print("*");
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}

	}
}
