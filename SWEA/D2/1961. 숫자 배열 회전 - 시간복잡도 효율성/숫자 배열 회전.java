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
			int[][] arr = new int[n][n];
			int[][] arr1 = new int[n][n];
			int[][] arr2 = new int[n][n];
			int[][] arr3 = new int[n][n];
			
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
					arr[i][j] = sc.nextInt();
			
			for(int i = 0; i < n; i++)
				for(int j = n - 1; j >= 0; j--)	
					arr1[i][n - j - 1] = arr[j][i];
			
			for(int i = 0; i < n; i++)
				for(int j = n - 1; j >= 0; j--)	
					arr2[i][n - j - 1] = arr1[j][i];
			
			for(int i = 0; i < n; i++)
				for(int j = n - 1; j >= 0; j--)	
					arr3[i][n - j - 1] = arr2[j][i];
			
			System.out.println("#" + test_case);
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++)
					System.out.print(arr1[i][j]);
				System.out.print(" ");
				
				for(int j = 0; j < n; j++)
					System.out.print(arr2[i][j]);
				System.out.print(" ");
				
				for(int j = 0; j < n; j++)
					System.out.print(arr3[i][j]);
				System.out.println();
			}
		}
		sc.close();
	}
}