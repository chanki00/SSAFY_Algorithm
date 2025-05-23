import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 원재의메모리복구하기_1289 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t=1; t<=T; ++t) {

            String s = br.readLine();

            int count = 0;
            char prev = '0';
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) != prev) {
                    prev = s.charAt(i);
                    ++count;
                }
            }

            System.out.println("#" + t + " " + count);
        }
    }
}
