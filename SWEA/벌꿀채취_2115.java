import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * N*N, 각 칸의 숫자는 꿀의 양
 * 1. 가로로 연속된 M개의 벌통 선택 -> 일꾼 2명 - 일꾼이 선택한 벌통은 겹치면 안 됨
 * 2. 최대 C만큼 채취 -> 벌통에 있는 모든 꿀은 한 번에 채취해야 함 -> M개의 벌통 합이 C보다 크면 선택해서 채취(일부만 채취는 불가능하니까)
 * 3. 이익 = 각 꿀통에 있는 꿀 양의 제곱만큼
 * -> 최대 수익은 얼마인가?
 */

public class 벌꿀채취_2115 {
	
	static class Point {
		int r;
		int c;
		
		Point() {}
		
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M, C;
	static int[][] map;
	static int max, result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			result = Integer.MIN_VALUE;
			
			map = new int[N][N];
			for (int i=0; i<N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			recursive(new Point[2], 0, 0, new boolean[N][N]);
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(result);
			System.out.println(sb);
			
		}
	}
	
	public static void recursive(Point[] arr, int curr, int idx, boolean[][] used) {
		if (idx == arr.length) {
			// 체크
			
			int sum = 0;
			for (int i=0; i<arr.length; ++i) {
				max = 0;
				List<Integer> idxList = new ArrayList<>();	
				getProfit(arr[i], 0, 0, idxList, 0);
				sum += max;
			}
			
			result = Math.max(result, sum);
			return;
		}
		
		for (int r=curr; r<N; ++r) {
			Point p = new Point();
			p.r = r;
			for (int c=0; c<N-M+1; ++c) {
				if (isPossible(r, c, used)) {
					for (int i=c; i<c+M; ++i) {
						used[r][i] = true;
					}
					p.c = c;
					arr[idx] = p;
					
					recursive(arr, r, idx+1, used);
					
					for (int i=c; i<c+M; ++i) {
						used[r][i] = false;
					}
				}
			}
		}
		
	}
	
	public static void getProfit(Point p, int sum, int cnt, List<Integer> list, int idx) {
		if (sum > C) {
			return;
		}
		
		if(idx == M) {
			int profit = 0;
			for (int gap : list) {
				profit += Math.pow(map[p.r][p.c+gap], 2);
			}
			max = Math.max(max, profit);
			return;
		}
		
		getProfit(p, sum, cnt, list, idx+1);
		list.add(idx);
		getProfit(p, sum+map[p.r][p.c+idx], cnt+1, list, idx+1);
		list.remove(list.size()-1);
	}
	
	public static boolean isPossible(int r, int c, boolean[][] used) {
		for (int i=c; i<c+M; ++i) {
			if (used[r][i]) {
				return false;
			}
		}
		return true;
	}
}
