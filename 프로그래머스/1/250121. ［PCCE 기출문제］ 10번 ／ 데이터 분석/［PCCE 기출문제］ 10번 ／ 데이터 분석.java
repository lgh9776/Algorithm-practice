import java.util.*;

/*
 * 데이터가 담긴 배열을 주면
 * ext 값이 val_ext보다 작은 데이터만 따로 저장
 * 따로 저장한 데이터에서 sort_by 값 기준으로 정렬
 */

class Solution {
	int sortInt = 0;
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {   	
    	//0=code, 1=date, 2=maximum, 3=remain
    	int extInt = 0; //ext를 int형으로 표현
    	if(ext.equals("code"))
    		extInt = 0;
    	else if(ext.equals("date"))
    		extInt = 1;
    	else if(ext.equals("maximum"))
    		extInt = 2;
    	else if(ext.equals("remain"))
    		extInt = 3;
    	
    	int cnt = 0; //ext 값이 val_ext보다 작은 데이터 갯수
    	for(int[] d : data) {
    		if(d[extInt] < val_ext) {
    			cnt++;
    		}
    	}
    	
    	//ext 값이 val_ext보다 작은 데이터만 저장할 배열
    	int[][] smallThanExt = new int[cnt][4];
    	int row = 0;
    	for(int[] d : data) {
    		if(d[extInt] < val_ext) {
    			smallThanExt[row] = d;
    			row++;
    		}
    	}
    	
    	//0=code, 1=date, 2=maximum, 3=remain
    	if(sort_by.equals("code"))
    		sortInt = 0;
    	else if(sort_by.equals("date"))
    		sortInt = 1;
    	else if(sort_by.equals("maximum"))
    		sortInt = 2;
    	else if(sort_by.equals("remain"))
    		sortInt = 3;
    	
    	//val_ext보다 작은 데이터만 저장한 배열을 sort_by 값 기준으로 정렬
    	Arrays.sort(smallThanExt,(o1, o2) -> o1[sortInt] - o2[sortInt]);
    	
        return smallThanExt;
    }
}