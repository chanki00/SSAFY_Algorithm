import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * N*N 격자에 파이어볼 M개 발사 (1~N) i번 파이어볼의 위치는 (ri, ci), 질량은 mi, 방향은 di, 속력은 si 1. 모든
 * 파이어볼은 방향 di로 속력 si칸 만큼 이동한다. - 같은 칸에 여러 개 파이어볼이 있을 수 있음 2. 2개 이상이 있는 칸에서는 다음과
 * 같다. 1) 같은 칸에 있는 파이어볼은 하나로 합쳐진다. 2) 다시 4개로 나누어진다. 3) 나누어진 파이어볼의 질량 속력 방향은 다음과
 * 같다. 1. 질량은 (합친 질량) / 5 2. 속력은 (합친 속력) / 개수 3. 합쳐지는 파이어볼의 방향이 모두 홀수 또는 짝수이면
 * (0, 2, 4, 6) 아니면 (1, 3, 5, 7) 4. 질량이 0인 파이어볼은 사라진다.
 */

public class 마법사상어와파이어볼_20056 {

	static class FireBall {
		int r;
		int c;
		int m;
		int s;
		int d;

		FireBall() {
		}

		FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	static List<FireBall> fireballs;
	static int N, M, K;
	static List<FireBall>[][] map;

	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 격자 크기
		M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
		K = Integer.parseInt(st.nextToken()); // 이동 명령 횟수

		map = new ArrayList[N + 1][N + 1];
		for (int i=0; i<=N; ++i) {
			for (int j=0; j<=N; ++j) {
				map[i][j] = new ArrayList<>();
			}
		}

		fireballs = new ArrayList<>();
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireballs.add(new FireBall(r, c, m, s, d));
			map[r][c].add(fireballs.get(i));
		}

		for (int k = 0; k < K; ++k) {
			move();
		}

		int result = 0;
		for (FireBall fb : fireballs) {
			result += fb.m;
		}

		System.out.println(result);

	}

	public static void move() {
		
		for (int i = 1; i <= N; i++) {
		    for (int j = 1; j <= N; j++) {
		        map[i][j].clear();
		    }
		}
		
		for (int i=0; i<fireballs.size(); ++i) {
			FireBall fb = fireballs.get(i);
//			map[fb.r][fb.c].remove(fb);
//			fb.r = getNext((N + 1 + fb.r + dr[fb.d]*fb.s) % N);
//			fb.c = getNext((N + 1 + fb.c + dc[fb.d]*fb.s) % N);
			fb.r = getNext(fb.r + dr[fb.d]*fb.s);
            fb.c = getNext(fb.c + dc[fb.d]*fb.s);
			
//			int nextR = fb.r + dr[fb.d]*fb.s;
//			int nextC = fb.c + dc[fb.d]*fb.s;
//			
//			if (nextR >= 1 && nextR <= N & nextC >= 1 && nextC <= N) {
//				fb.r = nextR;
//				fb.c = nextC;
//			}
			
			map[fb.r][fb.c].add(fb);
			
		}
		
		merge();
	}
	
	public static int getNext(int curr) {
		if (curr > N) {
			return curr - N;
		}
		
		if (curr < 1) {
			return N + curr;
		}
		
		return curr;
	}

	public static void merge() {
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= N; ++j) {
				List<FireBall> list = new ArrayList<>();
				int mSum = 0;
				int sSum = 0;
				int prev = -1;
				int dir = 1;

				for (int k = 0; k < map[i][j].size(); ++k) {
					FireBall fb = map[i][j].get(k);
					list.add(fb);
					mSum += fb.m;
					sSum += fb.s;
				}

				if (list.size() > 1) {
					if (isEven(list)) {
						dir = 0;
					}
					for (int k=0; k<map[i][j].size(); ++k) {
						fireballs.remove(map[i][j].get(k));
					}
					map[i][j].clear();
					int nextM = mSum / 5;
					int nextS = sSum / list.size();
					if (nextM > 0) {
						for (int k=0; k<4; ++k) {
							FireBall nextFb = new FireBall(i, j, nextM,
									nextS, dir);
							map[i][j].add(nextFb);
							fireballs.add(nextFb);
							dir += 2;
						}
					}
				}

			}
		}
	}
	
	public static boolean isEven(List<FireBall> list) {
		boolean allEven = true;
		boolean allOdd = true;
		
		for (FireBall fb : list) {
			if (fb.d % 2 == 0) {
				allOdd = false;
			}
			else {
				allEven = false;
			}
		}
		
		return allOdd || allEven;
	}
}
