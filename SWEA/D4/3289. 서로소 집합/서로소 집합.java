import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 테스트 케이스 입력 받기
 * 2. 원소의 개수, 연산의 개수 입력 받기
 * 3. 각 원소들이 루트가 되도록 초기화
 * 	  (parents 배열은 본인의 인덱스를 가짐)
 * 4. 연산의 개수만큼 반복
 * 	4-1. 어떤 연산을 할지 입력 받기
 * 	4-2. 연산할 원소 입력 받기
 * 5. 합집합 연산
 * 	5-1. 각 원소가 속한 집합의 대표자 찾기
 * 	5-2. 대표자가 같으면 false return
 * 	5-3. 대표자가 다르면 합집합 가능
 * 6. 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산
 * 	6-1. 같은 원소가 포함되어 있으면 1출력
 * 	6-2. 그렇지 않으면 0출력
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int elementCnt;
	static int calCnt;
	static int[] parents; //자신의 부모 인덱스를 저장
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		//1. 테스트 케이스 입력 받기
		int test_case = Integer.parseInt(st.nextToken());
		for(int testCase = 1; testCase <= test_case; testCase++) {
			sb.append("#").append(testCase).append(" ");
			
			//2. 원소의 개수, 연산의 개수 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			elementCnt = Integer.parseInt(st.nextToken());
			calCnt = Integer.parseInt(st.nextToken());
			
			//3. 각 원소들이 루트가 되도록 초기화 (parents 배열은 본인의 인덱스를 가짐)
			make();
			
			//4. 연산의 개수만큼 반복
			for (int count = 0; count < calCnt; count++) {
				st = new StringTokenizer(br.readLine().trim());
				//4-1. 어떤 연산을 할지 입력 받기
				int cal = Integer.parseInt(st.nextToken());
				
				//4-2. 연산할 원소 입력 받기
				int aNum = Integer.parseInt(st.nextToken());
				int bNum = Integer.parseInt(st.nextToken());
				
				//5. 합집합 연산
				if(cal == 0)
					union(aNum, bNum);
				
				//6. 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산
				else if(cal == 1) {
					int aRoot = findSet(aNum);
					int bRoot = findSet(bNum);
					
					//6-1. 같은 원소가 포함되어 있으면 1출력
					if(aRoot == bRoot)
						sb.append(1);
					//6-2. 그렇지 않으면 0출력
					else
						sb.append(0);
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	//최소 단위 집합을 초기화하는 메소드
	static void make() {
		parents = new int[elementCnt+1];
		for (int index = 1; index <= elementCnt; index++) {
			parents[index] = index;
		}
	}
	
	//합집합 메소드
	static void union(int num1, int num2) {
		//5-1. 각 원소가 속한 집합의 대표자 찾기
		int num1Root = findSet(num1);
		int num2Root = findSet(num2);
		
		//5-2. 대표자가 같으면 false return
		if(num1Root == num2Root)
			return;
		
		//5-3. 대표자가 다르면 합집합 가능
		//num2의 부모를 num1으로 
		parents[num2Root] = num1Root;
	}
	
	//원소가 포함된 집합(대표자) 찾기
	static int findSet(int num) {
		//본인이 본인의 부모이면 root
		if(num == parents[num])
			return num;

		//root를 찾을 때까지 findSet해서 찾으면 부모 배열의 num칸에 넣기 -> path 압축
		return parents[num] = findSet(parents[num]);
	}
	
}
