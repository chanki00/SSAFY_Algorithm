import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 줄기 세포는 생명력을 가짐 초기 줄기 세포는 비활성 상태 생명력 수치가 X인 줄기 세포는 X시간 동안 비활성, X시간 후 활성 상태 활성
 * 상태 후 X시간 후 죽음 세포가 죽어도 소멸은 X -> 그대로 그리드 셀 차지 활성화된 줄기 세포는 첫 1시간 후 상하좌우로 동시에 번식
 * -> 번식된 줄기 세포는 비활성 번식 위치에 이미 줄기 세포가 있을 경우 번식 X 두 개 이상의 줄기 세포가 하나의 셀에 번식하려 할 경우
 * 생명력 수치가 높은 줄기 세포가 셀을 차지한다. -> K시간 후 살아있는 줄기 세포(비활성 + 활성)의 총 개수는?
 * 
 */

public class 줄기세포배양_5653 {

	static class Cell {
		int r;
		int c;
		int life; // 기존 생명력
		int currLife; // 현재 생명력
		boolean isFirst = false; // 활성화 후 처음인지
		boolean isNew = true; // 방금 생성인지

		Cell(int r, int c, int life) {
			this.r = r;
			this.c = c;
			this.life = life;
			this.currLife = life;
		}

		@Override
		public String toString() {
			return "Cell [r=" + r + ", c=" + c + ", life=" + life + ", currLife=" + currLife + ", isFirst=" + isFirst
					+ ", isNew=" + isNew + "]";
		}
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int N, M, K;
	static Cell[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new Cell[2 * K + N][2 * K + M];

			Queue<Cell> before = new ArrayDeque<>(); // 비활성
			Queue<Cell> cellQ = new ArrayDeque<>(); // 활성
			Queue<Cell> after = new ArrayDeque<>(); // 죽은 세포

			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; ++j) {
					int life = Integer.parseInt(st.nextToken());
					if (life != 0) {
						map[K + i][K + j] = new Cell(K + i, K + j, life);
//						System.out.println((K+i) + " , " + (K+j));
						before.offer(map[K + i][K + j]);
						map[K+i][K+j].isNew = false;
					}
				}
			}

			for (int k = 0; k < K; ++k) {
//				System.out.println("전");
//				System.out.println("비활성: " + before.size());
//				System.out.println("활성: " + cellQ.size());
//				System.out.println("죽음: " + after.size());
//				System.out.println();

				List<Cell> list = new ArrayList<>();
				int cellQSize = cellQ.size();
				for (int i = 0; i < cellQSize; ++i) { // 활성화 세포 생명력 줄이기 (0되면 죽기), 처음이면 주변 번식
					Cell curr = cellQ.poll();
					--curr.currLife;

					if (curr.isFirst) {
						for (int d = 0; d < 4; ++d) {
							int nextR = curr.r + dr[d];
							int nextC = curr.c + dc[d];

							Cell next = map[nextR][nextC];
							if (next == null) {
								map[nextR][nextC] = new Cell(nextR, nextC, curr.life);
								list.add(map[nextR][nextC]);
							} else if (next.isNew) {
								if (next.life < curr.life) {
									list.remove(map[nextR][nextC]);
									map[nextR][nextC] = new Cell(nextR, nextC, curr.life);
									list.add(map[nextR][nextC]);
								}
							}

						}

						curr.isFirst = false;
					}

					if (curr.currLife == 0) {
						after.offer(curr);
					} else {
						cellQ.offer(curr);
					}
				}

				int beforeSize = before.size();
				for (int i = 0; i < beforeSize; ++i) { // 비활성 세포 생명력 줄이기 (0되면 활성)
					Cell curr = before.poll();
					--curr.currLife;
					if (curr.currLife == 0) {
						curr.isFirst = true;
						curr.currLife = curr.life;
						cellQ.offer(curr);
					} else {
						before.offer(curr);
					}
				}

				for (Cell c : list) {
					c.isNew = false;
//					System.out.println(c.r + ", " + c.c);
					before.offer(c);
				}

//				System.out.println((k+1) + " 후");
//				System.out.println("비활성: " + before.size());
//				System.out.println("활성: " + cellQ.size());
//				System.out.println("죽음: " + after.size());
//				System.out.println("-----------------------------");
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(before.size() + cellQ.size());
			System.out.println(sb);
		}
	}
}
