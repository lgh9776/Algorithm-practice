import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int[][] map;
	static int studentCnt;
	static List<Integer> candidate;
	
	public static void main(String[] args) throws IOException {
		
		inputData();
		
		for (int k = 0; k < studentCnt; k++) {
			for (int i = 0; i < studentCnt; i++) {
				for (int j = 0; j < studentCnt; j++) {
					if(map[i][k] > 0 && map[k][j] > 0) {
						int newRelDist = map[i][k] + map[k][j];
						if(map[i][j] == 0) {
							map[i][j] = newRelDist;
						}
						else {
							map[i][j] = newRelDist < map[i][j] ? newRelDist : map[i][j];
						}
					}
				}
			}
		}
		
		int minScore = Integer.MAX_VALUE;
		for (int i = 0; i < studentCnt; i++) {
			// 해당 회원의 가장 큰 점수 구하기
			int maxScore = Integer.MIN_VALUE;
			for (int j = 0; j < studentCnt; j++) {
				maxScore = maxScore < map[i][j] ? map[i][j] : maxScore;
			}
			
			// 해당 회원의 가장 큰 점수가 전체 회원의 가장 큰 점수 보다 작으면 갱신
			// 현재 후보들 지우고 후보 등록
			if(minScore == maxScore) {
				candidate.add(i+1);
			}
			else if(minScore > maxScore) {
				minScore = maxScore;
				candidate.clear();
				candidate.add(i+1);
			}			
		}
		sb.append(minScore).append(" ").append(candidate.size()).append("\n");
		for(int idx : candidate) {
			sb.append(idx).append(" ");
		}
		System.out.println(sb);
	}
	
	static void inputData() throws IOException {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		studentCnt = Integer.parseInt(st.nextToken());
		
		map = new int[studentCnt][studentCnt];
		st = new StringTokenizer(br.readLine());
		int rel1 = Integer.parseInt(st.nextToken());
		int rel2 = Integer.parseInt(st.nextToken());
		while(rel1 != -1 && rel2 != -1) {
			map[rel1-1][rel2-1] = 1;
			map[rel2-1][rel1-1] = 1;
			
			st = new StringTokenizer(br.readLine());
			rel1 = Integer.parseInt(st.nextToken());
			rel2 = Integer.parseInt(st.nextToken());
		}
		
		// 본인에 대한 값 설정
		for (int i = 0; i < studentCnt; i++) {
			map[i][i] = 1;
		}
		candidate = new ArrayList<>();
	}
}