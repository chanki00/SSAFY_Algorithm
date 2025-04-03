import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 점심식사시간_2383 {
	
	static class Point {
		int r;
		int c;
		
		Point(int r, int c) { 
			this.r = r;
			this.c = c;
		}
	}
	
	static int[][] map;
	static List<Point> persons;
	static List<Point> stairs;
	static int chk;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; ++t) {
			int N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			persons = new ArrayList<>();
			stairs = new ArrayList<>();
			
			for (int i=0; i<N; ++i) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						persons.add(new Point(i, j));
					}
					else if (map[i][j] != 0) {
						stairs.add(new Point(i, j));
					}
				}
			}
			
			recursive(new boolean[persons.size()], 0);
			System.out.println(persons.size());
			System.out.println(chk);
			
		}
	}
	
	public static void recursive(boolean[] sel, int idx) {
		if (idx == sel.length) {
			// sel[i] 가 true이면 stairs[0] / sel[i]가 false면 staris[1];
			
			return;
		}
		
		recursive(sel, idx+1);
		sel[idx] = true;
		recursive(sel, idx+1);
		sel[idx] = false;
	}
	
	public static int getTime(boolean[] sel) {
		int time1 = 0;
		int time2 = 0;
		
		int cnt1 = 0;
		int cnt2 = 0;
		
		for (int i=0; i<sel.length; ++i) {
			
		}
		
		
		return time1 + time2;
	}
}
