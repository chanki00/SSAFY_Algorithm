import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 창용마을무리의개수_7465 {
	
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			make(N);
			
			for (int m=0; m<M; ++m) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			
			Set<Integer> set = new HashSet<>();
			for (int i=1; i<=N; ++i) {
				set.add(find(i));
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(set.size());
			System.out.println(sb);
			
		}
	}
	
	public static void make(int n) {
		parents = new int[n+1];
		for (int i=1; i<=n; ++i) {
			parents[i] = i;
		}
	}
	
	public static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if (pa == pb) {
			return false;
		}
		
		if (pa > pb) {
			parents[pa] = pb;
		}
		else {
			parents[pb] = pa;
		}
		
		return true;
	}
	
	public static int find(int x) {
		if (parents[x] == x) {
			return x;
		}
		
		return parents[x] = find(parents[x]);
	}
}
