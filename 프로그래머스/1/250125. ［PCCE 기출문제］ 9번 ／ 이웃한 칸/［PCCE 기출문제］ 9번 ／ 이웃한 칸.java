class Solution {
    public int solution(String[][] board, int h, int w) {
        int boardSize = board.length;
        int colorSameCnt = 0;
        int[] directHeight = {0, 1, -1, 0};
        int[] directWidth = {1, 0, 0, -1};
        
        for(int row = 0; row < 4; row++)
        	if(h + directHeight[row] >= 0 && h + directHeight[row] < boardSize && w + directWidth[row] >= 0 && w + directWidth[row] < boardSize)
        		if(board[h + directHeight[row]][w + directWidth[row]].equals(board[h][w]))
        			colorSameCnt++;
        	
        return colorSameCnt;
    }
}