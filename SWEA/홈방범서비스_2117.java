import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 홈방범서비스_2117 {
	
	static class House {
		int r;
		int c;
		House(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;
	static int[][] map;
	static List<House> houses;
	static int max;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			houses = new ArrayList<>();
			max = Integer.MIN_VALUE;
			
			for (int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						houses.add(new House(i, j));
					}
				}
			}
			
			for (int i=0; i<N; ++i) {
				for (int j=0; j<N; ++j) {
					int k=1;
					while (getPrice(k) <= houses.size()*M) {
						int cnt = getCnt(i, j, k);
						int profit = cnt*M - getPrice(k);
						if (profit >= 0) {
							max = Math.max(max, cnt);
						}
						
						++k;
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(max);
			System.out.println(sb);
		}
	}
	
	public static int getCnt(int r, int c, int k) {
		int cnt = 0;
		
		for (int i=0; i<houses.size(); ++i) {
			House curr = houses.get(i);
			if (getDist(r, c, curr) < k) {
				++cnt;
			}
		}
		
		return cnt;
	}
	
	public static int getDist(int r, int c, House h) {
		return Math.abs(h.r-r) + Math.abs(h.c-c);
	}
	
	public static int getPrice(int k) {
		return k*k + (k-1)*(k-1);
	}
}
