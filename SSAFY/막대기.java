
public class 막대기 {
	
	static int N = 6;
	
	public static void main(String[] args) {
		int[] memo = new int[N+1];
		memo[1] = 2;
		memo[2] = 5;
		for (int i=3; i<=N; ++i) {
			memo[i] = memo[i-1] * 2 + memo[i-2];
		}
		
		System.out.println(memo[N]);
	}
}
