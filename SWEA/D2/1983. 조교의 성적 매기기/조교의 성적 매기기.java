import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 학생 수, 학생의 번호 입력 받기 (totalStudent, studentNum)
 * 3. 학생 성적 입력 받기 
 * 	3-1. 비율대로 계산
 * 	3-2. 총점 저장, 정렬할 복사본 생성
 * 4. 총점으로 내림차순 정렬하기
 * 5. score에서 findScore번째 점수 찾아서
 * 	5-1. findScore번째 점수와 일치한 점수가 몇번째에 있는지 보고 grade 출력
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			//2. 학생 수, 학생의 번호 입력 받기 (totalStudent, studentNum)
			st = new StringTokenizer(br.readLine());
			int studentCnt = Integer.parseInt(st.nextToken());
			int findStudentScore = Integer.parseInt(st.nextToken());
			
			//3. 학생 성적 입력 받기
			double[] score = new double[studentCnt]; //점수 저장
			double[] sortedScore = new double[studentCnt]; //정렬된 점수
			for(int count = 0; count < studentCnt; count++) {
				st = new StringTokenizer(br.readLine());
				
				//3-1. 비율대로 계산
				double midExam = Integer.parseInt(st.nextToken()) * 0.35;
				double finalExam = Integer.parseInt(st.nextToken()) * 0.45;
				double hw = Integer.parseInt(st.nextToken()) * 0.2;
				double totalScore = midExam + finalExam + hw;
				
				//3-2. 총점 저장, 정렬할 복사본 생성
				score[count] = totalScore;
				sortedScore[count] = totalScore;
			}
			//4. 총점으로 내림차순 정렬하기
			Arrays.sort(sortedScore);
			
			//5. score에서 findScore번째 점수 찾아서
			double findScore = score[findStudentScore-1];
			//5-1. findScore번째 점수와 일치한 점수가 몇번째에 있는지 보고 grade 출력
			int index;
			for(index = 0; index < studentCnt; index++) {
				if(sortedScore[index] == findScore)
					break;
			}
			
			if(index+1 <= (studentCnt / 10))
				sb.append("D0").append("\n");
			else if(index+1 <= (studentCnt / 10 * 2))
				sb.append("C-").append("\n");
			else if(index+1 <= (studentCnt / 10 * 3))
				sb.append("C0").append("\n");
			else if(index+1 <= (studentCnt / 10 * 4))
				sb.append("C+").append("\n");
			else if(index+1 <= (studentCnt / 10 * 5))
				sb.append("B-").append("\n");
			else if(index+1 <= (studentCnt / 10 * 6))
				sb.append("B0").append("\n");
			else if(index+1 <= (studentCnt / 10 * 7))
				sb.append("B+").append("\n");
			else if(index+1 <= (studentCnt / 10 * 8))
				sb.append("A-").append("\n");
			else if(index+1 <= (studentCnt / 10 * 9))
				sb.append("A0").append("\n");
			else if(index+1 <= (studentCnt / 10 * 10))
				sb.append("A+").append("\n");

		}
		System.out.print(sb);
	}
}
