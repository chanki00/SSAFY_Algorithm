import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Contact_1238 {
	
	static int[][] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for (int t=1; t<=T; ++t) {
			graph = new int[101][101];
			visited = new boolean[101];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(st.nextToken()) / 2;
			int start = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for (int l=0; l<length; ++l) {
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				graph[s][e] = 1;
			}
			
			int max = bfs(start);
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(max);
			System.out.println(sb);
		}
	}
	
	public static int bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;
		
		int size = q.size();
		int max = 0;
		while (!q.isEmpty()) {
			max = 0;
			
			for (int s=0; s<size; ++s) {
				int curr = q.poll();
				
				max = Math.max(max, curr);
				
				for (int i=0; i<graph[curr].length; ++i) {
					if (!visited[i] && graph[curr][i] == 1) {
						q.offer(i);
						visited[i] = true;
					}
				}
			}
			
			size = q.size();
		}
		
		
		return max;
	}
}
