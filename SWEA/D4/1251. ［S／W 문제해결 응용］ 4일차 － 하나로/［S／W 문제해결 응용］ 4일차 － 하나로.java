import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 섬의 개수 입력 받기
 * 3. 섬의 row좌표들 입력 받기
 * 4. 섬의 col좌표들 입력 받기
 * 5. 환경 부담 세율 실수 입력 받기
 * 6. 각 정점에서 갈 수 있는 간선 정보 가중치와 함께 리스트에 추가
 * 7. 가중치가 작은 간선부터 오름차순 정렬
 * 8. 각 섬들을 최소 단위 집합으로 초기화 (make-set)
 * 9. 정렬된 간선을 차례대로 꺼내오기
 * 10. union으로 간선의 양 끝 정점이 같은 집합이 아니면 start에 합치기 
 * 	10-1. 총합 환경 부담금에 더하기
 * 11. 같은 집합이면 pass
 * 12. parents에 모두 같은 부모를 가지면 return
 * 13. 총합 환경 부담금 반올림해서 출력 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Island{
		int x;
		int y;
		
		public Island(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		double distanceTax;
		
		public Edge(int start, int end, double distanceTax) {
			this.start = start;
			this.end = end;
			this.distanceTax = distanceTax;
		}

		@Override
		public int compareTo(Edge o) {
			//가중치로 정렬할 수 있게
			return Double.compare(this.distanceTax, o.distanceTax);
		}
	}
	
	static int islandCnt;
	static Island[] islands;
	static Edge[] islandEdge;
	static double tax;
	static double totalTax;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		int test_case = Integer.parseInt(st.nextToken());
		for (int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			inputData();
			
			//간선 정보 저장
			edgeData();
			
			//8. 각 섬들을 최소 단위 집합으로 초기화 (make-set)
			makeSet();
			
			//9. 정렬된 간선을 차례대로 꺼내오기
			for (int cnt = 0; cnt < islandEdge.length; cnt++) {
				//10. union으로 간선의 양 끝 정점이 같은 집합이 아니면 start에 합치기 
				if(union(islandEdge[cnt].start, islandEdge[cnt].end)) {
					//10-1. 총합 환경 부담금에 더하기 
					totalTax += islandEdge[cnt].distanceTax;
				}
				
				//11. 같은 집합이면 pass
				//12. parents에 모두 같은 부모를 가지면 return
			}
			
			//13. 총합 환경 부담금 반올림해서 출력 
			sb.append(Math.round(totalTax)).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. 섬의 개수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		islandCnt = Integer.parseInt(st.nextToken());
		parents = new int[islandCnt];
		totalTax = 0;
		
		//3. 섬의 x좌표들 입력 받기
		islands = new Island[islandCnt];
		st = new StringTokenizer(br.readLine().trim());
		for (int index = 0; index < islandCnt; index++) {
			islands[index] = new Island(Integer.parseInt(st.nextToken()), 0);
		}
		
		//4. 섬의 y좌표들 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		for (int index = 0; index < islandCnt; index++) {
			islands[index].y = Integer.parseInt(st.nextToken());
		}
		
		//5. 환경 부담 세율 실수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		tax = Double.parseDouble(st.nextToken());
	}

	static void edgeData() {
		//6. 각 정점에서 갈 수 있는 간선 정보 가중치와 함께 리스트에 추가
		islandEdge = new Edge[islandCnt*(islandCnt-1)/2];
		int cnt = 0;
		for(int start = 0; start < islandCnt-1; start++) {
			for(int end = start+1; end < islandCnt; end++) {
				//해저터널 길이 계산 
				long calDistance = (long) (Math.pow(islands[start].x-islands[end].x, 2) + Math.pow(islands[start].y-islands[end].y, 2));
				//환경 부담금
				double calDistanceTax = tax * calDistance;
				islandEdge[cnt] = new Edge(start, end, calDistanceTax);
				cnt++;
			}
		}
		
		//7. 가중치가 작은 간선부터 오름차순 정렬
		Arrays.sort(islandEdge);
	}
	
	static void makeSet() {
		for (int cnt = 0; cnt < islandCnt; cnt++) {
			parents[cnt] = -1;	
		}
	}
	
	//요소가 속한 집합의 루트 반환
	static int findSet(int element) {
		if(parents[element] < 0)
			return element;
		return parents[element] = findSet(parents[element]);
	}
	
	static boolean union(int first, int second) {
		int firstRoot = findSet(first);
		int secondRoot = findSet(second);
		
		if(firstRoot == secondRoot)
			return false;
		
		parents[firstRoot] += parents[secondRoot];
		parents[secondRoot] = firstRoot;
		return true;
	}
}
