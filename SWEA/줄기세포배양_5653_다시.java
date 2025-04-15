import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// r, c를 key로 갖고 Cell을 value로 갖는 맵

public class 줄기세포배양_5653_다시 {

	static class Cell {
		int r;
		int c;
		int life;
		int currLife;
		boolean isNew = true;
		boolean isFirst = false;

		Cell(int r, int c, int life) {
			this.r = r;
			this.c = c;
			this.life = life;
			this.currLife = life;
		}

		@Override
		public String toString() {
			return "Cell [r=" + r + ", c=" + c + ", life=" + life + ", currLife=" + currLife + ", isNew=" + isNew
					+ ", isFirst=" + isFirst + "]";
		}
	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	static Map<String, Cell> cellMap, newMap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			cellMap = new HashMap<>();
			Queue<Cell> before = new ArrayDeque<>(); // 비활성
			Queue<Cell> cellQ = new ArrayDeque<>(); // 활성
			Queue<Cell> after = new ArrayDeque<>(); // 죽은 세포

			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; ++j) {
					int life = Integer.parseInt(st.nextToken());
					if (life != 0) {
						String key = getKey(i, j);
						cellMap.put(key, new Cell(i, j, life));
						before.offer(cellMap.get(key));
					}
				}
			}

			for (int k = 0; k < K; ++k) {
//				System.out.println("전");
//				System.out.println("비활성: " + before.size());
//				System.out.println("활성: " + cellQ.size());
//				System.out.println("죽음: " + after.size());
//				System.out.println();
				
				newMap = new HashMap<>();
				int cellQSize = cellQ.size();
				for (int i = 0; i < cellQSize; ++i) {
					Cell curr = cellQ.poll();
					--curr.currLife;
					
					System.out.println(curr);
					
					if (curr.isFirst) {
						for (int d = 0; d < 4; ++d) {
							int nextR = curr.r + dr[d];
							int nextC = curr.c + dc[d];
							String key = getKey(nextR, nextC);

							Cell next = cellMap.getOrDefault(key, null);

							if (next == null) {
//							cellMap.put(key, new Cell(nextR, nextC, curr.life));
								newMap.put(key, new Cell(nextR, nextC, curr.life));
							} else if (newMap.containsKey(key)) {
								if (next.life < curr.life) {
//								cellMap.put(key, new Cell(nextR, nextC, curr.life));
									newMap.put(key, new Cell(nextR, nextC, curr.life));
								}
							}
						}
						
						curr.isFirst = false;
					}
					
					if (curr.currLife == 0) {
						after.offer(curr);
					}
					else {
						cellQ.offer(curr);
					}
				}
				
				int beforeSize = before.size();
				for (int i=0; i<beforeSize; ++i) {
					Cell curr = before.poll();
					--curr.currLife;
					
					if (curr.currLife == 0) {
						curr.isFirst = true;
						curr.currLife = curr.life;
						cellQ.offer(curr);
					}
					else {
						before.offer(curr);
					}
				}
				
				for (String key : newMap.keySet()) {
					Cell c = newMap.get(key);
					cellMap.put(key, c);
					before.offer(c);
//					System.out.println(c.r + ", " + c.c);
				}
				
				System.out.println((k+1) + " 후");
				System.out.println("비활성: " + before.size());
				System.out.println("활성: " + cellQ.size());
				System.out.println("죽음: " + after.size());
				System.out.println("-----------------------------");
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(before.size() + cellQ.size());
			System.out.println(sb);

		}
	}

	public static String getKey(int r, int c) {
		return r + ", " + c;
	}
}
