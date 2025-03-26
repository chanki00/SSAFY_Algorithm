import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 서로소집합_3289 {
	
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			make(n);
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			
			for (int i=0; i<m; ++i) {
				st = new StringTokenizer(br.readLine());
				int o = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (o == 0) {
					union(a, b);
				}
				else {
					int r = find(a) == find(b) ? 1 : 0;
					sb.append(r);
				}
			}
			
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
		
		if (pa < pb) {
			parents[pb] = pa;
		}
		else {
			parents[pa] = pb;
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
