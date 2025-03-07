import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 미생물격리_2382 {
	
	static class Virus {
		int r;
		int c;
		int cnt;
		int direction;
		int num;
		
		Virus (int r, int c, int cnt, int direction, int num) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.direction = direction;
			this.num = num;
		}
	}
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0 ,-1, 1};
	static List<List<List<Integer>>> map;
	
	static int N, M, K;
	static Virus[] viruses;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 맵 크기
			M = Integer.parseInt(st.nextToken()); // 시간
			K = Integer.parseInt(st.nextToken()); // 미생물 수 
			map = new ArrayList<>();
			for (int i=0; i<N; ++i) {
				map.add(new ArrayList<>());
				for (int j=0; j<N; ++j) {
					map.get(i).add(new ArrayList<>());
				}
			}
			
			viruses = new Virus[K];			
			
			int num = 1;
			for (int k=0; k<K; ++k) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken())-1;
				viruses[k] = new Virus(r, c, cnt, d, num);
				map.get(r).get(c).add(num);
				++num;
			}
			
			for (int m=0; m<M; ++m) {
				play();
			}
			
			int result = 0;
			for (Virus virus : viruses) {
				result += virus.cnt;
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ").append(result);
			
			System.out.println(sb);
			
		}
	}
	
	public static void play() {
		for (int k=0; k<K; ++k) {
			if (viruses[k].cnt > 0) {
				move(k);
			}
		}
		for (int i=0; i<N; ++i) {
			for (int j=0; j<N; ++j) {
				if (map.get(i).get(j).size() >= 2) {
					merge(i, j, map.get(i).get(j));
				}
			}
		}
	}
	
	public static void move(int idx) {
		Virus curr = viruses[idx];
		map.get(curr.r).get(curr.c).remove(Integer.valueOf(curr.num));
		
		int nextR = curr.r + dr[curr.direction];
		int nextC = curr.c + dc[curr.direction];
		
		if (!isValid(nextR, nextC)) {
			if (curr.direction % 2 == 0) {
				curr.direction += 1;
			}
			else {
				curr.direction -= 1;
			}
			curr.cnt /= 2;
		}
		
		map.get(nextR).get(nextC).add(curr.num);
		curr.r = nextR;
		curr.c = nextC;
		
	}
	
	public static void merge(int r, int c, List<Integer> list) {
		int max = Integer.MIN_VALUE;
		int sum = 0;
		int num = 0;
		
		for (int i=0; i<list.size(); ++i) {
			Virus curr = viruses[list.get(i)-1];
			sum += curr.cnt;
			if (max < curr.cnt) {
				max = curr.cnt;
				num = curr.num;
			}
			curr.cnt = 0;
		}
		
		viruses[num-1].cnt = sum;
		
		map.get(r).get(c).clear();
		map.get(r).get(c).add(num);
	}
	
	public static boolean isValid(int r, int c) { // 약품 위치로 가면 false
		return r > 0 && r < N-1 && c > 0 && c < N-1;
	}
}
