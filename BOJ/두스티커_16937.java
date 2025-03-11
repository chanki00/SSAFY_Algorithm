import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 두스티커_16937 {
	static class Sticker {
		int h;
		int w;
		
		Sticker(int h, int w) {
			this.h = h;
			this.w = w;
		}
	}
	
	static int max, H, W;
	static Sticker[] stickers;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(br.readLine());
		
		stickers = new Sticker[N];
		max = 0;
		
		for (int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			stickers[i] = new Sticker(h, w);
		}
		
		for (int i=0; i<N; ++i) {
			for (int j=i+1; j<N; ++j) {
				chk(stickers[i], stickers[j]);
			}
		}
		
		System.out.println(max);
		
	}
	
	public static void chk(Sticker s1, Sticker s2) {
		int h1 = s1.h;
		int w1 = s1.w;
		int h2 = s2.h;
		int w2 = s2.w;
		
		if (!(isValid(h1, w1, h2, w2) || isValid(w1, h1, h2, w2) 
				|| isValid(h1, w1, w2, h2) || isValid(w1, h1, w2, h2))) {
			return;
		}
		
		max = Math.max(max, h1*w1 + h2*w2);
	}
	
	public static boolean isValid(int h1, int w1, int h2, int w2) {
		return (h1 <= H && h2 <= H && w1 <= W && w2 <= W) 
				&& ((h2 <= H-h1 && w2 <= W) || (h2 <= H && w2 <= W-w1));
	}
}
