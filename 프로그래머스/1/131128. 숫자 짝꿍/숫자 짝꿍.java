class Solution {
    public String solution(String X, String Y) {
        int[] x = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] y = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        
        //각 문자열에 있는 수 찾기
        for(int col = 0; col < X.length(); col++){
        	x[X.charAt(col) - '0']++;
        }
        
        for(int col = 0; col < Y.length(); col++){
        	y[Y.charAt(col) - '0']++;
        }
        
        StringBuilder answer = new StringBuilder();
        for(int col = 9; col >= 0; col--)
            for(int row = 0; row < Math.min(x[col], y[col]); row++)
                answer.append(col);
        
        if(answer.length() == 0)
           return "-1";
        else if(answer.charAt(0) - '0' == 0) //정렬된 배열의 첫 인덱스가 0이면 다 0
           return "0";
        return answer.toString();
    }
}