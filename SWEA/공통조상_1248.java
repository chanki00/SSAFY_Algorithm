import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Node에 depth도 표시하자
 */

public class 공통조상_1248 {

    static class Node {
        int num;
        int parent;
        int childNum = 0;

        Node(int num, int parent) {
            this.num = num;
            this.parent = parent;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; ++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); // 정점 수
            int E = Integer.parseInt(st.nextToken()); // 간선 수
            int find1 = Integer.parseInt(st.nextToken());
            int find2 = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            Node[] nodes = new Node[V + 1];

            for (int i = 0; i < E; ++i) {
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                if (nodes[p] == null) {
                    nodes[p] = new Node(p, -1);
                    ++nodes[p].childNum;
                }
                else {
                    ++nodes[p].childNum;
                }

                if (nodes[c] == null) {
                    nodes[c] = new Node(c, p);
                }
                else {
                    nodes[c].parent = p;
                }
            }

            int p1 = find1;
            int p2 = find2;
            int count = nodes[p1].childNum + nodes[p2].childNum;

            while (p1 != p2) {
                if (p1 > p2) {
                    p1 = nodes[p1].parent;
                    count += nodes[p1].childNum;
                }
                else {
                    p2 = nodes[p2].parent;
                    count += nodes[p2].childNum;
                }
            }

            count -= (nodes[p1].childNum - 1);

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t).append(" ").append(p1).append(" ").append(count);
            System.out.println(sb);
        }
    }
}
