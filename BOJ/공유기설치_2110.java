import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공유기설치_2110 {
	
	static int[] house;
	static int C;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
	
		house = new int[N];
		for (int i=0; i<N; ++i) {
			house[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house);
		
		int l = 1;
		int r = house[N-1] - house[0] + 1;
	
		while (l < r) {
			int mid = (l+r) / 2;
			
			if (chk(mid) < C) {
				r = mid;
			}
			else {
				l = mid+1;
			}
		}
		
		System.out.println(l-1);
	}
	
	public static int chk(int v) {
		int prev = -1;
		int count = 0;
		
		for (int i=0; i<house.length; ++i) {
			if (prev == -1 || house[i] - prev >= v) {
				prev = house[i];
				++count;
			}
		}
		
		return count;
	}
}
