import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
    	Queue<String> goalQueue = new LinkedList<String>();
    	Queue<String> card1Queue = new LinkedList<String>();
    	Queue<String> card2Queue = new LinkedList<String>();
    	
    	for(int index = 0; index < goal.length; index++)
    		goalQueue.add(goal[index]);
    	
    	for(int index = 0; index < cards1.length; index++)
    		card1Queue.add(cards1[index]);
    	
    	for(int index = 0; index < cards2.length; index++)
    		card2Queue.add(cards2[index]);
    	
    	for(int index = 0; index < goal.length; index++) {
    		if(goalQueue.peek().equals(card1Queue.peek())) { //card1Queue의 head가 첫번째가 goal의 첫 단어라면
    			card1Queue.poll(); //card1Queue에서 단어를 뽑고 다음 goal 단어 찾기
    			goalQueue.poll();
    			continue;
    		}
    		else if(goalQueue.peek().equals(card2Queue.peek())) {
    			card2Queue.poll();
    			goalQueue.poll();
    			continue;
    		}
    		else
    			return "No";
    	}
    	if(goalQueue.isEmpty())
    		return "Yes";
    	else
    		return "No";
    }
}