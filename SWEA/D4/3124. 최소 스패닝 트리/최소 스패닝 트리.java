import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 정점의 개수, 간선의 개수 입력 받기
 * 3. 간선 정보 입력 받기
 * 4. 가중치를 기준으로 간선 오름차순 정렬
 * 5. 최소 단위 서로소 집합으로 초기화 하기
 * 6. 가중치가 작은 간선부터 가져와 union
 * 	6-1. 루트가 같다면 pass
 * 7. union 가능하면 total가중치에 가중치 더하기
 * 8. 루트가 1개라면 1개의 트리 완성 -> break
 * 9. 가중치 출력
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Edge implements Comparable<Edge>{
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int vCnt, eCnt;
	static Edge[] edges;
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
			
			//4. 가중치를 기준으로 간선 오름차순 정렬
			Arrays.sort(edges);
			
			//5. 최소 단위 서로소 집합으로 초기화 하기
			makeSet();
			
			//6. 가중치가 작은 간선부터 가져와 union
			long totalWeight = 0;
			for (int index = 0; index < eCnt; index++) {
				if(union(edges[index].start, edges[index].end)) {
					//7. union 가능하면 total가중치에 가중치 더하기
					totalWeight += edges[index].weight;
				}
			}
			
			//9. 가중치 출력
			sb.append(totalWeight).append("\n");
		}
		System.out.print(sb);
	}
	
	static void inputData() throws IOException {
		//2. 정점의 개수, 간선의 개수 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		vCnt = Integer.parseInt(st.nextToken());
		eCnt = Integer.parseInt(st.nextToken());
		
		//3. 간선 정보 입력 받기
		edges = new Edge[eCnt];
		for (int index = 0; index < eCnt; index++) {
			st = new StringTokenizer(br.readLine().trim());
			edges[index] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
	}
	
	static boolean union(int first, int second) {
		int firstRoot = findSet(first);
		int secondRoot = findSet(second);
		
		//6-1. 루트가 같다면 pass
		if(firstRoot == secondRoot)
			return false;
		
		parents[firstRoot] += parents[secondRoot];
		parents[secondRoot] = firstRoot;
		return true;
	}
	
	static int findSet(int num) {
		if(parents[num] < 0){
			return num;
		}
		
		return parents[num] = findSet(parents[num]);
	}
	
	static void makeSet() {
		parents = new int[vCnt+1];
		for (int index = 1; index <= vCnt; index++) {
			parents[index] = -1;
		}
	}
}
