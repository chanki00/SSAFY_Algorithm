import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 연구소_14502 {
	
	static int max;
	static int[][] map;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	static class Point {
		int r;
		int c;
		
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		max = Integer.MIN_VALUE;
		
		map = new int[N][M];
		for (int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
				}
			}
		}
		
		for (int i=0; i<N; ++i) {
			for (int j=0; j<M; ++j) {
				if (map[i][j] == 0) {
					dfs(i, j, 3);
				}
			}
		}
		
		System.out.println(max);
		
	}
	
	public static void dfs(int r, int c, int wallCnt) {
		if (wallCnt == 0) {
			// 바이러스 수 세기
			
			boolean[][] visited = new boolean[map.length][map[0].length];
			
			int cnt = 0;
			for (int i=0; i<map.length; ++i) {
				for (int j=0; j<map[i].length; ++j) {
					if (map[i][j] == 2 && !visited[i][j]) {
						bfs(i, j, visited);
					}
				}
			}
			
			for (int i=0; i<map.length; ++i) {
				for (int j=0; j<map[i].length; ++j) {
					if (map[i][j] == 0 && !visited[i][j]) {
						++cnt;
					}
				}
			}
			
			max = Math.max(max, cnt);
			
			return;
		}
		
		if (r == map.length) {
			return;
		}
		
		if (map[r][c] == 0) {
			// 벽 놓기
			map[r][c] = 1;
			if (c == map[r].length-1) {
				dfs(r+1, 0, wallCnt-1);
			}
			else {
				dfs(r, c+1, wallCnt-1);
			}
			map[r][c] = 0;
		}
		
		if (c == map[r].length-1) {
			dfs(r+1, 0, wallCnt);
		}
		else {
			dfs(r, c+1, wallCnt);
		}
		
		
		
	}
	
	public static void bfs(int r, int c, boolean[][] visited) { // virus 확산
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(r, c));
		visited[r][c] = true;
		
		while (!q.isEmpty()) {
			Point curr = q.poll();
			
			for (int d=0; d<4; ++d) {
				int nextR = curr.r + dr[d];
				int nextC = curr.c + dc[d];
				
				if (isValid(nextR, nextC) && map[nextR][nextC] == 0 && !visited[nextR][nextC]) {
					visited[nextR][nextC] = true;
					q.add(new Point(nextR, nextC));
				}
			}
		}
	}
	
	public static boolean isValid(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map[r].length;
	}
}
