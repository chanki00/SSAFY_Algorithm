import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 유기농배추_1012 {
	static int[][] map;
	static boolean[][] visited;
	
	static class Point {
		int r;
		int c;
		
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t=1; t<=T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 가로
			int N = Integer.parseInt(st.nextToken()); // 세로
			int K = Integer.parseInt(st.nextToken()); // 배추 갯수
			// N * M
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for (int k=0; k<K; ++k) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				map[c][r] = 1;
			}
			
			int count = 0;
			for (int i=0; i<N; ++i) {
				for (int j=0; j<M; ++j) {
					if (map[i][j] == 1 && !visited[i][j]) {
						++count;
						bfs(i, j);
					}
				}
			}
			
			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void bfs(int r, int c) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(r, c));
		visited[r][c] = true;
		
		while (!q.isEmpty()) {
			Point curr = q.poll();
			
			for (int d=0; d<4; ++d) {
				int nextR = curr.r + dr[d];
				int nextC = curr.c + dc[d];
				
				if (isValid(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC] == 1) {
					q.add(new Point(nextR, nextC));
					visited[nextR][nextC] = true;
				}
			}
			
		}
	}
	
	public static boolean isValid(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map[r].length;
	}
}
