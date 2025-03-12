import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 직사각형탈출_16973 {
	
	static class Point {
		int r;
		int c;
		int dist;
		
		Point(int r, int c, int dist) { 
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	
	static class Rectangle {
		Point leftTop;
		
		Rectangle(Point leftTop) {
			this.leftTop = leftTop;
		}
	}
	
	static int[][] map;
	static int H, W;
	static Rectangle rectangle;
	static Point end;
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		for (int i=1; i<=N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j=1; j<=M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		int Sr = Integer.parseInt(st.nextToken());
		int Sc = Integer.parseInt(st.nextToken());
		
		int Fr = Integer.parseInt(st.nextToken());
		int Fc = Integer.parseInt(st.nextToken());
		
		rectangle = new Rectangle(new Point(Sr, Sc, 0));
		end = new Point(Fr, Fc, 0);
		
		int result = bfs();
		System.out.println(result);
		
	}
	
	public static int bfs() {
		boolean[][] visited = new boolean[map.length][map[0].length];
		Queue<Point> q = new ArrayDeque<>();
		q.add(rectangle.leftTop);
		visited[rectangle.leftTop.r][rectangle.leftTop.c] = true;
		
		while (!q.isEmpty()) {
			Point curr = q.poll();
			
			if (curr.r == end.r && curr.c == end.c) {
				return curr.dist;
			}
			
			for (int d=0; d<4; ++d) {
				int nextR = curr.r + dr[d];
				int nextC = curr.c + dc[d];
				
				if (isValid(nextR, nextC) && !visited[nextR][nextC] && chk(nextR, nextC, d)) {
					visited[nextR][nextC] = true;
					q.add(new Point(nextR, nextC, curr.dist+1));
				}
			}
		}
		
		return -1;
	}
	
	public static boolean chk(int r, int c, int flag) { // 0 오른쪽, 1 아래, 2 왼쪽, 3 위
		if (flag == 0 || flag == 2) { // 오른쪽 || 왼쪽
			if (flag == 0) {
				c += W-1;
			}
			for (int h=0; h<H; ++h) {
				if (map[r][c] == 1) {
					return false;
				}
				++r;
			}
		}
		else { // 아래 || 위
			if (flag == 1) {
				r += H-1;
			}
			for (int w=0; w<W; ++w) {
				if (map[r][c] == 1) {
					return false;
				}
				++c;
			}
		}
//		else if (flag == 2) { // 왼쪽
//			for (int w=0; w<W; ++w) {
//				if (map[r][c] == 1) {
//					return false;
//				}
//				++c;
//			}
//		}
//		else { // 위
//			for (int w=0; w<W; ++w) {
//				if (map[r][c] == 1) {
//					return false;
//				}
//				++c;
//			}
//		}
		
		return true;
	}
	
	public static boolean isValid(int r, int c) {
		return r >= 1 && (r+H-1) < map.length && c >= 1 && (c+W-1) < map[r].length;
	}
}
