import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 프로세서연결하기_1767 {

    static class Point {
        int r;
        int c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[][] map;
    static boolean[][] used;
    static List<Point> list;
    static int[] dr = {0, 1, 0 ,-1};
    static int[] dc = {1, 0, -1 ,0};
    static int min;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; ++t) {
            int N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            used = new boolean[N][N];
            list = new ArrayList<>();
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            for (int i=0; i<N; ++i) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; ++j) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] != 0 && i > 0 && i < N-1 && j > 0 && j < N-1) {
                        list.add(new Point(i, j));
                    }
                }
            }

            dfs(0, 0, 0);

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t).append(" ").append(min);
            System.out.println(sb);
        }
    }

    public static void dfs(int idx, int length, int cnt) {
        if (idx == list.size()) {
            if (cnt > max) {
                max = cnt;
                min = length;
            }
            else if (cnt == max) {
                min = Math.min(min, length);
            }
            return;
        }
        
        Point p = list.get(idx);
        for (int d=0; d<4; ++d) {
            if (isPossble(p.r, p.c, d)) {
                int l = getLength(p.r, p.c, d, true);
                dfs(idx+1, length+l, cnt+1);
                getLength(p.r, p.c, d, false);
            }
        }

        dfs(idx+1, length, cnt);
    }
    
    public static boolean isPossble(int r, int c, int d) {
        int nextR = r+dr[d];
        int nextC = c+dc[d];

        while (isValid(nextR, nextC)) {
            if (map[nextR][nextC] != 0 || used[nextR][nextC]) { // 중간에 장애물 있음
                return false;
            }
            nextR += dr[d];
            nextC += dc[d];
        }
        
        return true;
    }

    public static int getLength(int r, int c, int d, boolean flag) {
        int nextR = r+dr[d];
        int nextC = c+dc[d];

        int cnt = 0;

        while (isValid(nextR, nextC)) {
            used[nextR][nextC] = flag;
            nextR += dr[d];
            nextC += dc[d];
            ++cnt;
        }

        return cnt;
    }

    public static boolean isValid(int r, int c) {
        return r >= 0 && r < map.length && c >= 0 && c < map[r].length;
    }
}
