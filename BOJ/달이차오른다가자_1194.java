import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 달이차오른다가자_1194 {
    static class Point {
        int r;
        int c;
        int key;
        int cnt;
        Point(int r, int c, int key, int cnt) {
            this.r = r;
            this.c = c;
            this.key = key;
            this.cnt = cnt;
        }
    }

    static Point start;
    static char[][] map;
    static boolean[][][] visited;
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        result = -1;

        map = new char[N][M];
        for (int i=0; i<N; ++i) {
            String s = br.readLine();
            for (int j=0; j<M; ++j) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == '0') {
                    start = new Point(i, j, 0, 0);
                }
            }
        }
        // 열쇠 6개에 대해 각각 가졌으면 1 없으면 0 (해당 비트)
        visited = new boolean[2 << 6][N][M];
        bfs();
        System.out.println(result);
    }

    public static void bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.add(start);
        visited[start.key][start.r][start.c] = true;

        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        while (!q.isEmpty()) {
            Point curr = q.poll();

            if (map[curr.r][curr.c] == '1') {
                result = curr.cnt;
                break;
            }

            for (int d=0; d<4; ++d) {
                int nextR = curr.r + dr[d];
                int nextC = curr.c + dc[d];
                int nextKey = curr.key;

                if (isValid(nextR, nextC) && !visited[nextKey][nextR][nextC] && map[nextR][nextC] != '#') {
                    if (map[nextR][nextC] >= 'a' && map[nextR][nextC] <='z') {
                        // 열쇠일 경우
                        nextKey = curr.key | (1 << map[nextR][nextC] - 'a');
                    }

                    else if (map[nextR][nextC] >= 'A' && map[nextR][nextC] <= 'Z') {
                        //벽일 경우
                        if ((nextKey & (1 << (map[nextR][nextC] - 'A'))) == 0) { // 열쇠가 없으면
                            continue;
                        }
                    }
                    
                    visited[nextKey][nextR][nextC] = true;
                    q.add(new Point(nextR, nextC, nextKey, curr.cnt + 1));
                }
            }
        }
    }

    public static boolean isValid(int r, int c) {
        return r >= 0 && r < map.length && c >= 0 && c < map[r].length;
    }
}
