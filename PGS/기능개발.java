import java.util.ArrayList;
import java.util.List;

public class 기능개발 {

/*
- 진도가 100%이면 반영
- 뒤에 있는 애는 앞에 애 배포할 때 같이 배포
*/

    class Solution {
        public int[] solution(int[] progresses, int[] speeds) {
            int[] answer;

            List<Integer> list = new ArrayList<>();
            int cnt = 0;
            int idx = 0;
            while (cnt < progresses.length) {
                int currCnt = 0;
                int mul = (int) Math.ceil((100 - progresses[idx]) / (double) speeds[idx]);

                for (int i=idx; i<progresses.length; ++i) {
                    int nextVal = progresses[i] + speeds[i] * mul;
                    progresses[i] = nextVal >= 100 ? 100 : nextVal;
                }

                for (int i=idx; i<progresses.length; ++i) {
                    if (progresses[i] < 100) {
                        idx = i;
                        break;
                    }
                    ++currCnt;
                }

                list.add(currCnt);
                cnt += currCnt;
            }

            answer = new int[list.size()];
            for (int i=0; i<list.size(); ++i) {
                answer[i] = list.get(i);
            }

            return answer;
        }
    }
}
