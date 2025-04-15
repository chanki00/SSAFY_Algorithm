import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 점심식사시간_2383 {

    static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static int[][] map;
    static List<Point> persons;
    static List<Point> stairs;
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; ++t) {
            int N = Integer.parseInt(br.readLine());

            persons = new ArrayList<>();
            stairs = new ArrayList<>();

            min = Integer.MAX_VALUE;

            map = new int[N][N];
            for (int i=0; i<N; ++i) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j=0; j<N; ++j) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] == 1) {
                        persons.add(new Point(i, j));
                    }
                    else if (map[i][j] != 0) {
                        stairs.add(new Point(i, j));
                    }
                }
            }

            recursive(new ArrayList<>(), new ArrayList<>(), 0);

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t).append(" ").append(min);
            System.out.println(sb);
        }
    }

    //list1 = stairs.get(0) , list2 = stairs.get(1)
    public static void recursive(List<Point> list1, List<Point> list2, int idx) {
        if (idx == persons.size()) {
            // 시간 체크
            chk(list1, list2);
            return;
        }

        list1.add(persons.get(idx));
        recursive(list1, list2, idx+1);
        list2.add(list1.remove(list1.size()-1));
        recursive(list1, list2, idx+1);
        list2.remove(list2.size()-1);
    }

    public static void chk(List<Point> list1, List<Point> list2) {
        int time1 = play(list1, stairs.get(0));
        int time2 = play(list2, stairs.get(1));

        min = Math.min(min, Math.max(time1, time2));
    }

    public static int play(List<Point> people, Point stair) {
        List<Integer> dist = new ArrayList<>();
        for (Point p : people) {
            dist.add(getDistance(stair, p));
        }

        dist.sort((o1, o2) -> Integer.compare(o1, o2));

        List<Integer> useStair = new ArrayList<>();
        int time = 0;
        int idx = 0;

        while (idx < dist.size() || !useStair.isEmpty()) {
            ++time;
            for (int i = 0; i < useStair.size(); ) {
                if (useStair.get(i) == time) {
                    useStair.remove(i);
                } else {
                    i++;
                }
            }

            while (idx < dist.size() && dist.get(idx) + 1 <= time && useStair.size() < 3) {
                useStair.add(time + map[stair.r][stair.c]);
                idx++;
            }
        }

        return time;
    }


    public static int getDistance(Point stair, Point person) {
        return Math.abs(stair.r - person.r) + Math.abs(stair.c - person.c);
    }
}
