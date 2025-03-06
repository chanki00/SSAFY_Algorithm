import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 나무자르기_2805 {
	
	static int N, M, maxTree;
	static int[] trees;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maxTree = 0;
		
		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; ++i) {
			trees[i] = Integer.parseInt(st.nextToken());
			maxTree = Math.max(maxTree, trees[i]);
		}
		
		
		int l = 0;
		int r = maxTree;
		int mid = 0;
		while (l < r) {
			mid = (l+r)/2;
			long profit = calc(mid);
			if (profit < M) {
				r = mid;
			}
			else {
				l = mid+1;
			}
		}
		
		
		System.out.println(l-1);
		
	}
	
	public static long calc(int mid) {
		long sum = 0;
		for (int i=0; i < trees.length; ++i) {
			long profit = trees[i] - mid;
			sum += profit > 0? profit : 0;
		}
		return sum;
	}
}
