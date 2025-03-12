import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 치킨배달_1566 {
	
	static class Point {
		int r;
		int c;
		
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M, min;
	static List<Point> houses, chickens;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		
		houses = new ArrayList<>();
		chickens = new ArrayList<>();
		
		for (int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; ++j) {
				int v = Integer.parseInt(st.nextToken());
				if (v == 1) {
					houses.add(new Point(i, j));
				}
				else if (v == 2) {
					chickens.add(new Point(i, j));
				}
			}
		}
		
		recursive(new int[M], 0, 0);
		
		System.out.println(min);
		
	}
	
	public static void recursive(int[] sel, int idx, int cnt) {
		if (cnt == sel.length) {
			// 거리 체크
			int dist = calcDist(sel);
			min = Math.min(min, dist);
			return;
		}
		
		if (idx == chickens.size()) {
			return;
		}
		
		recursive(sel, idx+1, cnt);
		sel[cnt] = idx;
		recursive(sel, idx+1, cnt+1);
	}
	
	public static int calcDist(int[] sel) {
		int sum = 0;
		for (Point house : houses) {
			int minD = Integer.MAX_VALUE;
			for (int idx : sel) {
				Point chicken = chickens.get(idx);
				minD = Math.min(minD, getDist(house, chicken));
			}
			sum += minD;
		}
		
		return sum;
	}
	
	public static int getDist(Point p1, Point p2) {
		return Math.abs(p1.r-p2.r) + Math.abs(p1.c-p2.c);
	}
}
