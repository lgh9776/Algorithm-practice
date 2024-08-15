/*
 * 1. 재귀함수를 호출하기 전 출력
 * 2. 재귀함수 호출 (0부터 finish만큼 depth에 들어가기 위해 0을 인자로 넘김)
 * 3. (전처리) level 별 tab 계산 (깊어질수록 tab이 증가 -> 편하게 계산하기 위해 level을 0부터 시작)
 *     3-1. 탈출조건과 관계없이 출력할 부분 탈출조건 전에 작성
 *     3-2. 탈출(기재)조건 작성
 *     3-3. 탈출조건 시 출력되지 않는 전처리 부분 작성
 * 4. (재귀호출) 재귀함수 호출
 * 5. (후처리) 기재조건을 만나 거슬러 올라온 후 출력할 후처리 부분 작성
 */
import java.util.Scanner;

public class Main {
	static int finish = 0;
	
	static void permutation(int level) {
        //3. (전처리) level 별 tab 계산 (깊어질수록 tab이 증가 -> 편하게 계산하기 위해 level을 0부터 시작)
		String tab = "";
		for(int index = 0; index < level; index++) {
			tab += "____";
		}
		
        //3-1. 탈출조건과 관계없이 출력할 부분 탈출조건 전에 작성
		System.out.println(tab + "\"재귀함수가 뭔가요?\"");
		
        //3-2. 탈출(기재)조건 작성
		if(level == finish) {
			System.out.println(tab + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			System.out.println(tab + "라고 답변하였지.");
			return;
		}
		
        //3-3. 탈출조건 시 출력되지 않는 전처리 부분 작성
		System.out.println(tab + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		System.out.println(tab + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		System.out.println(tab + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
		
        //4. (재귀호출) 재귀함수 호출
		permutation(level+1);
		
        //5. (후처리) 기재조건을 만나 거슬러 올라온 후 출력할 후처리 부분 작성
		System.out.println(tab + "라고 답변하였지.");	
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		finish = sc.nextInt();
        //1. 재귀함수를 호출하기 전 출력
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        //2. 재귀함수 호출 (0부터 finish만큼 depth에 들어가기 위해 0을 인자로 넘김)
		permutation(0);
	}
	
	
}
