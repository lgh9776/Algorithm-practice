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
			int[][][] arr = new int[4][n][n];
			
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
					arr[0][i][j] = sc.nextInt();

			for(int k = 1; k < 4; k++)
				for(int i = 0; i < n; i++)
					for(int j = n - 1; j >= 0; j--)	
						arr[k][i][n - j - 1] = arr[k - 1][j][i];
			
			System.out.println("#" + test_case);
			
			for(int i = 0; i < n; i++)
			{
				for(int k = 1; k < 4; k++)
				{
					for(int j = 0; j < n; j++)
						System.out.print(arr[k][i][j]);
					System.out.print(" ");
				}
				System.out.println();
			}
		}
		sc.close();
	}
}