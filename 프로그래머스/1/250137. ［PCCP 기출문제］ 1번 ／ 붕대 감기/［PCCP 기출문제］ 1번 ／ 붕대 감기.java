/*
 * 1. 게임시간 증가
 * 2. 공격 체크
 * 3. 공격 받았을 시 체력 감소, 연속 성공 초기화 > 반복문 탈출
 *    현재 체력 <= 0, 게임 끝(-1)
 * 4. 공격x, 최대 체력 이상이면 초과 부분 처리 및 탈출
 * 5. 체력, 연속 성공 증가
 * 6. 연속 성공 시간 채울 시 보너스 체력 회복, 연속 성공 초기화
 */

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int time = 0; //게임 플레이 타임
        int continueHealTime = 0; //연속 성공 시간
        int hp = health; //현재 체력 , 최대 체력으로 시작
        int attackRow = 0;
        
    	while(attackRow < attacks.length) {
            time++;
    		//공격 받으면 체력 감소, 연속 성공 초기화
        	if(attacks[attackRow][0] == time) {
        		hp -= attacks[attackRow][1];
        		continueHealTime = 0;
        		attackRow++;
        		
        		//게임 오버 체크
        		if(hp <= 0)
        			return -1;
        		continue;
        	}
        	
        	//최대 체력 이상이면 초과 부분 처리 및 넘김
        	if(hp >= health) {
        		hp = health;
        		continue;
        	}
        	 
        	//체력, 연속 성공 시간 증가
        	hp += bandage[1];
        	continueHealTime++;
        	
        	//연속 성공 시간 채울 시 보너스 체력 회복
        	if(continueHealTime >= bandage[0]) {
        		hp += bandage[2];
        		continueHealTime = 0;
        	}
        }
    	return hp;
    }
}