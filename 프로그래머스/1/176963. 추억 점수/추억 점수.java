import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int row = 0; row < name.length; row++){
            map.put(name[row], yearning[row]);
        }
        
        int totalScore = 0;
        for(int row = 0; row < photo.length; row++){
            for(int col = 0; col < photo[row].length; col++){
                if(map.containsKey(photo[row][col])){
                    totalScore += map.get(photo[row][col]);
                }
            }
            answer[row] = totalScore;
            totalScore = 0;
        }
        
        return answer;
    }
}