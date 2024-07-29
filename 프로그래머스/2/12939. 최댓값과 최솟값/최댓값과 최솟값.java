class Solution {
    public String solution(String s) {
        String[] strArr = s.split(" ");
        Integer[] strToInt = new Integer[strArr.length];
        
        int index = 0;
        for(String num : strArr) {
        	strToInt[index] = Integer.parseInt(num);
        	index++;
        }
        
        int max = strToInt[0], min = strToInt[0];
        for(Integer num : strToInt) {
        	if(max < num)
        		max = num;
        	
        	if(min > num)
        		min = num;
        }
        
        return min + " " + max;
    }
}
