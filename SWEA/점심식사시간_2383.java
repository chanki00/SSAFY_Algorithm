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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t=1; t<=T; ++t) {
            int N = Integer.parseInt(br.readLine());

            persons = new ArrayList<>();
            stairs = new ArrayList<>();

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
        }
    }

    //list1 = stairs.get(0) , list2 = stairs.get(1)
    public static void recursive(List<Point> list1, List<Point> list2, int idx) {
        if (idx == persons.size()) {
            // 시간 체크
            return;
        }

        list1.add(persons.get(idx));
        recursive(list1, list2, idx+1);
        list2.add(list1.remove(list1.size()-1));
        recursive(list1, list2, idx+1);
        list2.remove(list2.size()-1);
    }

    public static void chk(List<Point> list1, List<Point> list2) {
        list1.sort((o1, o2) -> Integer.compare(getDistance(stairs.get(0), o1), getDistance(stairs.get(0), o2)));
        list1.sort((o1, o2) -> Integer.compare(getDistance(stairs.get(1), o1), getDistance(stairs.get(1), o2)));

        int time1 = 0;
        int cnt1 = 0;
        for (int i=0; i<list1.size(); ++i) {

        }

    }

    public static int getDistance(Point stair, Point person) {
        return Math.abs(stair.r - person.r) + Math.abs(stair.c - person.c);
    }
}
