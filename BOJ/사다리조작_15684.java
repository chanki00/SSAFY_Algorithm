import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사다리조작_15684 {
	
	static int N, M, H;
	static boolean[][] used;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 세로선 개수
		M = Integer.parseInt(st.nextToken()); // 가로선 개수
		H = Integer.parseInt(st.nextToken()); // 가로선 놓을 수 있는 위치 개수
		
		used = new boolean[H+1][N+1]; //[가로선 위치][세로선 도착 위치]
		for (int m=0; m<M; ++m) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			used[a][b+1] = true;
		}
		
		
	}
	
	public static void bt() {
		
		
		
	}
	
	public static boolean chk(int currCol) {
		int moveCol = currCol;
		for (int h=1; h<H; ++h) {
			if (used[moveCol][h+1]) {
				++moveCol;
			}
			else if (used[moveCol][h]) {
				--moveCol;
			}
		}
		
		return moveCol == currCol; 
	}
}
