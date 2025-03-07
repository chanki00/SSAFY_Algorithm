import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 예산_2512 {
	
	static int[] local;
	static int M, max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		local = new int[N];
		
		max = Integer.MIN_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<local.length; ++i) {
			local[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, local[i]);
		}
		
		M = Integer.parseInt(br.readLine());
		
		int hi = max+1;
		int lo = 0;
		
		while (lo < hi) {
			int mid = (hi + lo) / 2;
			if (M < chk(mid)) {
				hi = mid;
			}
			else {
				lo = mid + 1;
			}
		}
		
		System.out.println(lo-1);
		
	}
	
	public static int chk(int mid) {
		int sum = 0;
		for (int i=0; i<local.length; ++i) {
			sum += local[i] > mid ? mid : local[i];
		}
		
		return sum;
	}
	
	
}
