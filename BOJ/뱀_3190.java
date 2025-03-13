import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀_3190 {
	
	static class Move {
		int time;
		String direct;
		
		Move(int time, String direct) {
			this.time = time;
			this.direct = direct;
		}
	}
	
	static int[][] map;
	static int time, idxM, L;
	static Move[] moves;
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		
		for (int k=0; k<K; ++k) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[r][c] = 1; // 사과는 1
		}
		
		L = Integer.parseInt(br.readLine());
		moves = new Move[L];
		for (int l=0; l<L; ++l) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			String d = st.nextToken();
			moves[l] = new Move(t, d);
		}
		
		time = 1;
		play();
		System.out.println(time);
	}
	
	public static void play() {
		
		int headR = 1;
		int headC = 1;
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[]{headR, headC});
		map[headR][headC] = 2;
		int idxD = 0;
		
		while (true) {
			headR += dr[idxD];
			headC += dc[idxD];
			if (!isValid(headR, headC) || map[headR][headC] == 2) {
				break;
			}
			
			if (map[headR][headC] != 1) { // 사과가 아니면
				int[] idx = q.poll();
				map[idx[0]][idx[1]] = 0;
			}
			map[headR][headC] = 2;
			q.add(new int[]{headR, headC});
			
			if (idxM < moves.length && time == moves[idxM].time) {
				if (moves[idxM].direct.equals("L")) {
					idxD = (4 + idxD - 1) % 4;
				}
				else {
					idxD = (4 + idxD + 1) % 4;
				}
				++idxM;
			}
			++time;
		}
	}
	
	public static boolean isValid(int r, int c) {
		return r >= 1 && r < map.length && c >= 1 && c <map[r].length;
	}
}
