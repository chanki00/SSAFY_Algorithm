/**
 * 아마도 시간초과??......
 */
public class 주식가격 {
	class Solution {
	    public int[] solution(int[] prices) {
	        int[] answer = new int[prices.length];
	        
	        for (int i=0; i<prices.length-1; ++i) {
	            ++answer[i];
	            for (int j=i+1; j<prices.length; ++j) {
	                if (prices[i] > prices[j]) {
	                    break;
	                }
	                ++answer[i];
	                if (j == prices.length-1) {
	                    --answer[i];
	                }
	            }
	        }
	        
	        return answer;
	    }
	}
}
