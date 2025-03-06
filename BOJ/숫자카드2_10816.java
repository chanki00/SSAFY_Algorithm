import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자카드2_10816 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] cards = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; ++i) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		int[] target = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; ++i) {
			target[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(cards);
		StringBuilder sb = new StringBuilder();
		for (int m=0; m<M; ++m) { //lower
			int lowL = 0;
			int lowR = cards.length;
			int low = 0;
			while (lowL < lowR) {
				low = (lowL+lowR)/2;
				if (target[m] <= cards[low]) {
					lowR = low;
				}
				else {
					lowL = low+1;
				}
			}
			
			int upL = 0;
			int upR = cards.length;
			int up = 0;
			while (upL < upR) { //upper
				up = (upL+upR)/2;
				if (target[m] < cards[up]) {
					upR = up;
				}
				else {
					upL = up+1;
				}
			}
			
			sb.append(upL-lowL).append(" ");
		}
		
		System.out.println(sb);
	}
}
