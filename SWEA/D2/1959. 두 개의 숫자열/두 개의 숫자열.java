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
			int m = sc.nextInt();
			int[] arr1 = new int[n];
			int[] arr2 = new int[m];
			int max = 0;
			int sum = 0;
			
			for(int i = 0; i < n; i++)
				arr1[i] = sc.nextInt();

			for(int i = 0; i < m; i++)
				arr2[i] = sc.nextInt();
			
			int moveArrLen = n < m ? n : m;
			int fixArrLen = n > m ? n : m;
			for(int i = 0; i < fixArrLen; i++)
			{
				for(int j = 0; j < moveArrLen; j++)
				{
					if(i + moveArrLen > fixArrLen)
						break;
					
					if(moveArrLen == n)
						sum += arr1[j] * arr2[i + j]; 
					else
						sum += arr2[j] * arr1[i + j];
				}
				max = max < sum ? sum : max;
				sum = 0;
			}
			System.out.println("#" + test_case + " " + max);
		}
		sc.close();
	}
}