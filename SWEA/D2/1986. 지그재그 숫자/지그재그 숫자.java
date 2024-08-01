import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = sc.nextInt();
			
			if(n % 2 == 0) {
				n = (n / 2) * -1;
			}
			else {
				n = (n+1)/2;
			}
			
			System.out.printf("#%d %d\n", test_case, n);
		}
	}
}