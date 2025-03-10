import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baaaaaaaaaduk2_Easy_16988 {

	static int[][] map;
	static int max;
	
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		max = Integer.MIN_VALUE;
		
		map = new int[N][M];
		for (int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		recursive(0, 0, 2);
		System.out.println(max);
	}
	
	public static void recursive(int r, int c, int cnt) {
		if (cnt == 0) {
			// 죽은 돌 체크
			boolean[][] visited = new boolean[map.length][map[0].length];
			int count = 0;
			for (int i=0; i<map.length; ++i) {
				for (int j=0; j<map[i].length; ++j) {
					if (map[i][j] == 2 && !visited[i][j]) {
						count += chk(i, j, visited);
					}
				}
			}
			
			max = Math.max(max, count);
			return;
		}
		
		for (int i=r; i<map.length; ++i) {
			for (int j=c; j<map[i].length; ++j) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					recursive(i, j, cnt-1);
					map[i][j] = 0;
				}
			}
			c = 0;
		}
	}
	
	public static int chk(int r, int c, boolean[][] visited) {
		Queue<Point> q = new ArrayDeque<>();
		visited[r][c] = true;
		q.add(new Point(r, c));
		
		int count = 0;
		boolean isPossible = true;
		
		while (!q.isEmpty()) {
			Point curr = q.poll();
			++count;
			
			for (int d=0; d<4; ++d) {
				int nextR = curr.r + dr[d];
				int nextC = curr.c + dc[d];
				
				if (isValid(nextR, nextC) && !visited[nextR][nextC]) {
					if (map[nextR][nextC] == 0) {
						isPossible = false;
					}
					else if (map[nextR][nextC] == 2) {
						visited[nextR][nextC] = true;
						q.add(new Point(nextR, nextC));
					}
				}
				
			}
		}
		
		return isPossible ? count : 0;
	}
	
	public static boolean isValid(int r, int c) {
		return r >= 0 && r < map.length && c >= 0 && c < map[r].length;
	}

}
