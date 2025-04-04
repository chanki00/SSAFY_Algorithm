import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 키순서_5643_BFS {
	
	static int N, M;
	static int[] cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; ++t) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			cnt = new int[N+1];
			
			List<List<Integer>> upGraph = new ArrayList<>();
			List<List<Integer>> downGraph = new ArrayList<>();
			
			for (int i=0; i<=N; ++i) {
				upGraph.add(new ArrayList<>());
				downGraph.add(new ArrayList<>());
			}
			
			for (int m=0; m<M; ++m) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				upGraph.get(s).add(e);
				downGraph.get(e).add(s);
			}
			
			
			for (int i=1; i<=N; ++i) {
				bfs(upGraph, i);
				bfs(downGraph, i);
				--cnt[i];
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(getResult());
			System.out.println(sb);
		}
	}
	
	private static int getResult() {
		int result=0;
		for (int c : cnt) {
			if (c == N) {
				++result;
			}
		}
		return result;
	}

	public static void bfs(List<List<Integer>> graph, int start) {
		boolean[] visited = new boolean[N+1];
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		int count = 0;
		
		while (!q.isEmpty()) {
			int curr = q.poll();
//			if (visited[curr]) continue;
			++count;
//			visited[curr] = true;
			for (int next : graph.get(curr)) {
				if (!visited[next]) {
					q.add(next);
					visited[next] = true;
				}
			}
		}
		
		cnt[start] += count;
	}
}
