import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 랜선자르기_1654 {
	
	static int K, N;
	static int[] lan;
	static long maxLength;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		maxLength = 0;
		
		lan = new int[K];
		for (int i=0; i<K; ++i) {
			lan[i] = Integer.parseInt(br.readLine());
			maxLength = Math.max(maxLength, lan[i]);
		}
		
		long lo = 0;
		long hi = maxLength+1;
		while (lo < hi) {
			long mid = (lo+hi)/2;
			
			long count = getCount(mid);
			
			if (count < N) {
				hi = mid;
			}
			else {
				lo = mid+1;
			}
		}
		
		System.out.println(lo-1);
		
	}
	
	public static long getCount(long mid) {
		long count = 0;
		for (int i=0; i<lan.length; ++i) {
			count += (lan[i] / mid);
		}
		return count;
	}
}
