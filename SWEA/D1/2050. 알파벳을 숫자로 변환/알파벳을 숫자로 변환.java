import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);

        String abcStr = sc.next();
        char[] abcArr = abcStr.toCharArray();
        
        for(char c : abcArr) {
        	System.out.print(c - 'A' + 1 + " ");
        }
	}
}