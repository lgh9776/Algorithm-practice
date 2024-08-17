import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * 1.테스트 케이스 입력 받기
 * 2. 재료 수, 조합 수 입력 받기
 * 3. 조합 수만큼 반복
 * 	3-1. 조합 입력 받기
 * 	3-2. int형으로 변환 후 작은수를 key, 큰 수는 value에 넣음
 * 		 (HashMap<int, HashSet<Integer> 사용 -> 자동으로 중복 체크)
 * 4. 재료를 갖고 만들 수 있는 모든 경우의 수 부분집합으로 구하기
 * 5. (기재조건) 더 뽑을 수 없으면 
 * 	5-1. 해당 조합에 불가능한 조합 있는지 확인
 * 	5-2. 없으면 메뉴 수 증가 후 return
 * 6. 현재 요소 선택, 다음 요소에 대해 재귀호출
 * 7. 현재 요소 선택x, 다음 요소에 대해 재귀호출
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int foodCnt; //재료수
	static int notTogetherCnt; //조합 불가 수
	static int menuCnt;

	static boolean[] isSelected; //해당 재료 선택됐는지 여부
	static HashMap<Integer, HashSet<Integer>> notTogether; //조합 불가능
	
	
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        
        //1.테스트 케이스 입력 받기
        int test_case = Integer.parseInt(st.nextToken());
        for(int testCase = 1; testCase <= test_case; testCase++) {
        	sb.append("#").append(testCase).append(" ");
        	inputData();
        	menuCnt = 0;
        	
        	//4. 재료를 갖고 만들 수 있는 모든 경우의 수 부분집합으로 구하기
        	powerSet(0);
        	sb.append(menuCnt).append("\n");
        }
        System.out.println(sb);
    }
    
    static void inputData() throws Exception {
    	//2. 재료 수, 조합 수 입력 받기
    	st = new StringTokenizer(br.readLine());
    	foodCnt = Integer.parseInt(st.nextToken());
    	notTogetherCnt = Integer.parseInt(st.nextToken());
    	notTogether = new HashMap<>();
    	isSelected = new boolean[foodCnt];
    	
    	//3. 조합 수만큼 반복
    	for(int index = 0; index < notTogetherCnt; index++) {
    		//3-1. 조합 입력 받기
    		st = new StringTokenizer(br.readLine());
    		int first = Integer.parseInt(st.nextToken());
    		int second = Integer.parseInt(st.nextToken());
    		
    		int key = Math.min(first, second);
    		
    		//3-2. 작은수를 key, 큰 수는 value에 넣음
    		//해당 키가 존재하지 않으면
    		if(!notTogether.containsKey(key)) {
    			//리스트 생성
    			HashSet<Integer> item = new HashSet<>();
    			//큰 수를 요소로 추가
    			item.add(Math.max(first, second));
    			//map에 추가
    			notTogether.put(key, item);
    		}
    		//해당 키가 존재하면
    		else {
    			//해당 키의 set에 value 추가 (set이라 중복 제거)
    			notTogether.get(key).add(Math.max(first, second));
    		}
    	}
    }
    
    
    static void powerSet(int selectIndex) {
    	//5. (기재조건) 더 뽑을 수 없으면 
    	if(selectIndex == foodCnt) {
    		//5-1. 해당 조합에 불가능한 조합 있는지 확인
    		if(!isValidMeun()) {
    			return;
    		}
    		
    		//5-2. 없으면 메뉴 수 증가 후 return
    		menuCnt++;
    		return;
    	}
    	
    	//6. 현재 요소 선택, 다음 요소에 대해 재귀호출
    	isSelected[selectIndex] = true;
    	powerSet(selectIndex + 1);
    	
    	//7. 현재 요소 선택x, 다음 요소에 대해 재귀호출
    	isSelected[selectIndex] = false;
    	powerSet(selectIndex + 1);
    }
    
    static boolean isValidMeun() {
    	for(int index = 0; index < foodCnt; index++) {
    		//5-1-1. 포함되어 있는 요소를 기준
    		if(isSelected[index] == true) {
    			//5-1-2. 그 요소를 키로 가진 리스트 확인 (= 조합 불가능한 것)
    			if(notTogether.containsKey(index+1)) {
	    			for(int notEntry : notTogether.get(index + 1)) {
	    				//5-1-3. 조합 불가능한 요소가 true인게 있으면 false
	    				if(isSelected[notEntry - 1] == true) {
	    					return false;
	    				}
	    			}
    			}
    		}
    	}
    	return true;
    }
}