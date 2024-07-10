import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		int bitChange;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			String bit = sc.next();
			String[] bitArr = bit.split("");
			bitChange = 0;
			
			for(int i = 0; i < bitArr.length - 1; i++)
			{
				if(bitArr[i].equals("1") && i == 0)
					bitChange++;
				
				if(!bitArr[i].equals(bitArr[i+1]))
					bitChange++;
			}
			System.out.println("#" + test_case + " " + bitChange);
		}
		sc.close();
	}
}