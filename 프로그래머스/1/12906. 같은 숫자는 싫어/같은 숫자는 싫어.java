import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
    	List<Integer> notContinue = new ArrayList<>();
    	int exNum = -1;
    	
        for(int idx = 0; idx < arr.length; idx++) {
        	if(exNum == arr[idx])
        		continue;
        	
        	notContinue.add(arr[idx]);
        	exNum = arr[idx];
        }
        
        int[] answer = new int[notContinue.size()];
        for(int idx = 0; idx < answer.length; idx++) {
        	answer[idx] = notContinue.get(idx);
        }
        
        return answer;
    }
}