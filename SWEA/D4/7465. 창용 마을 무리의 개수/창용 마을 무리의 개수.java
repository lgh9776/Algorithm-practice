import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 사람 수, 관계 수 입력 받기
 * 3. 본인을 루트로 하는 집합 초기화 하기
 * 4. 1개의 관계씩 입력 받기
 * 5. 해당 관계 정의를 위해 합집합 메소드 호출
 * 	5-1. 합집합이 불가능하면 return (같은 루트를 갖지 않으면)
 * 6. 모든 관계에 대한 정의 후 전체 요소에 대해 path 압축
 * 7. 부모 인덱스를 저장하는 배열에서 루트의 개수 찾아 출력
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());

		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for (int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			//2. 사람 수, 관계 수 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			int peopleCnt = Integer.parseInt(st.nextToken());
			int knownCnt = Integer.parseInt(st.nextToken());
			
			//3. 본인을 루트로 하는 집합 초기화 하기
			make(peopleCnt);
			
			for (int cnt = 0; cnt < knownCnt; cnt++) {
				//4. 1개의 관계씩 입력 받기
				st = new StringTokenizer(br.readLine().trim());
				int aPerson = Integer.parseInt(st.nextToken());
				int bPerson = Integer.parseInt(st.nextToken());
				
				//5. 해당 관계 정의를 위해 합집합 메소드 호출
				union(aPerson, bPerson);
			}
			
			//6. 모든 관계에 대한 정의 후 전체 요소에 대해 path 압축
			for (int index = 1; index <= peopleCnt; index++) {
				findSet(index);
			}
			
			//7. 부모 인덱스를 저장하는 배열에서 루트의 개수 찾아 출력
			//모든 요소에 대해 path 압축을 해줘서 parents 배열에는 루트만 존재
			List<Integer> roots = new ArrayList<>();
			for (int index = 1; index <= peopleCnt; index++) {
				if(!roots.contains(parents[index]))
					roots.add(parents[index]);
			}
			
			sb.append(roots.size()).append("\n");
		}
		System.out.print(sb);
	}

	static void make(int cnt) {
		parents = new int[cnt+1];
		for (int index = 1; index <= cnt; index++) {
			parents[index] = index; //본인을 루트로 설정
		}
	}
	
	//인자로 들어온 요소가 포함된 집합의 대표자 return
	static int findSet(int num) {
		if(num == parents[num]) //루트를 찾음
			return num;
		
		//num칸에 있는 부모 인덱스를 넘기기
		//거슬러 올라가 루트를 찾으면 해당 경로에 있던 것들 모두 루트 인덱스 아래 붙이기
		return parents[num] = findSet(parents[num]);
	}
	
	static void union(int first, int second) {
		int firstRoot = findSet(first);
		int secondRoot = findSet(second);
		
		//5-1. 합집합이 불가능하면 return (같은 루트를 갖지 않으면)
		if(firstRoot == secondRoot){ //이미 같은 집합에 있으면
			return;
		}
		
		//합친후 true return
		parents[secondRoot] = firstRoot;
	}
}