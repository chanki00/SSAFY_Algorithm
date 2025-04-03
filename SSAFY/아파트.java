
/**
 * 현재가 노랑 -> 노, 파 가능
 * 현재가 파랑 -> 노 가능
 */

public class 아파트 {
	public static void main(String[] args) {
		//f(N) = f(N-1) + f(N-1)의 노란색 개수
		int[] memo = new int[9];
		memo[1] = 2;
		memo[2] = 3; // f(n)의 노란색 개수는 f(n-1)임
		
		for (int i=3; i<9; ++i) {
			memo[i] = memo[i-1] + memo[i-2];
		}
		
		System.out.println(memo[8]);
	}
}

/*
 * 노랑(n) = 노랑(n-1) + 파랑(n-1)
 * 파랑(n) = 노랑(n-1)
 * 
 * f(n) = 노랑(n) + 파랑(n)
 * f(n) = f(n-1) + f(n-2)
 */

// 노노노 노노파 노파노 파노노 파노파