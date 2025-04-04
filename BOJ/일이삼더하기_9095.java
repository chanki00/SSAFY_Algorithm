import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 일이삼더하기_9095 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; ++t) {
			int N = Integer.parseInt(br.readLine());
			int[] memo = new int[N + 1];

			memo[0] = 1;
			memo[1] = 1;
			// memo[2] = 2;
			// memo[3] = 4;
			for (int i = 2; i <= N; ++i) {
				if (i >= 2) {
					memo[i] += memo[i - 2];
				}

				if (i >= 3) {
					memo[i] += memo[i - 3];
				}

				memo[i] += memo[i - 1];
			}

			System.out.println(memo[N]);
		}
	}
}