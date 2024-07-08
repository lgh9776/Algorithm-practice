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
			int m = sc.nextInt() - 1;
			int[][] arr = new int[n][n];
			int a = 0;
			int b = 0;
			int big = 0;
						
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
					arr[i][j] = sc.nextInt();
			
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < n; j++)
				{
					a = 0;
					b = 0;
					for(int k = i - m; k <= i + m; k++)
						a += (k >= 0 && k < n) ? arr[k][j] : 0;
					
					for(int k = j - m; k <= j + m; k++)
						a += (k >= 0 && k < n) ? arr[i][k] : 0;
					
					for(int k = 1; k <= m; k++)
					{
						b += (i-k >= 0 && j-k >= 0) ? arr[i-k][j-k] : 0;
						b += (i+k < n && j+k < n) ? arr[i+k][j+k] : 0;
						b += (i-k >= 0 && j+k < n) ? arr[i-k][j+k] : 0;
						b += (i+k < n && j-k >= 0) ? arr[i+k][j-k] : 0;
					}
					
					a -= arr[i][j];
					b += arr[i][j];
					
					if (big < a)
						big = a;
					
					if (big < b)
						big = b;
				
				}
			}
			
			System.out.println("#" + test_case + " " + big);
		}
		sc.close();
	}
}
