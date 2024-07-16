class Solution {
    public String solution(int[] food) {
    	String matchFoodArr = "";
    	
    	//준비한 음식의 1/2로 1명 분량의 음식 배열 완성
    	for(int row = 1; row < food.length; row++)
    		for(int idx = 0; idx < food[row] / 2; idx++)
    			matchFoodArr += (row + "");

    	//1명 분량의 음식 배열 reverse 구하기
    	char[] reverseFoodArr = matchFoodArr.toCharArray();
    	StringBuilder reverseStr = new StringBuilder(new String(reverseFoodArr));
    	
    	//중앙에 물 추가
    	matchFoodArr += "0";
    	
    	//반대편 추가
    	matchFoodArr += reverseStr.reverse().toString();

        return matchFoodArr;
    }
}