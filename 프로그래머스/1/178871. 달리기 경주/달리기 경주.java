import java.util.*;
/*
1. 현재 순위대로 점수 차등 부여 (3명 -> 3, 2, 1점)
2. callings에 있을 때마다 +1점
3. 총점이 가장 높은 순으로 순위
*/
class Solution {
    public String[] solution(String[] players, String[] callings) {
    	Map<String, Integer> map = new HashMap<>();
        
        //해시맵으로 점수(순위)와 선수 mapping
        for(int row = 0; row < players.length; row++){
            String p = players[row];
            map.put(p, row);
        }
        
        //callings에 따라 해시맵 값변경
        for(String c : callings){
            int rank = map.get(c); //이름 불리면 value 받아오기
            
            //이름 불린 것보다 1순위 앞의 것 value로 key찾기 (현 순위 참고)
            String before = players[rank - 1];
            
            //선수 순위 배열 수정
            players[rank - 1] = c;
            players[rank] = before;
            
            //해시맵 수정
            map.put(before, rank);
            map.put(c, rank - 1);
               
        }
        return players;
    }
}