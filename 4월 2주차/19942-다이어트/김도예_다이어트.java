import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 완탐 백트래킹
 * 순조부 다시 봐야 될 듯
 */
public class Main {

	static int N, mp, mf, ms, mv, mc = Integer.MAX_VALUE;
	static Cook[] cook;
	static boolean[] select;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cook = new Cook[N+1];
		select = new boolean[N+1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		mp = Integer.parseInt(st.nextToken());
		mf = Integer.parseInt(st.nextToken());
		ms = Integer.parseInt(st.nextToken());
		mv = Integer.parseInt(st.nextToken());
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			cook[i] = new Cook(p, f, s, v, c);
		}
		cooking(1, 0, 0, 0, 0, 0);
		System.out.println(mc == Integer.MAX_VALUE ? -1 : mc);
		if(sb.length() > 0) System.out.println(sb);
	}
	static void cooking(int idx, int p, int f, int s, int v, int sum) {
		if(sum > mc) return;
		if(p >= mp && f >= mf && s >= ms && v >= mv) {
			if(sum < mc) {
				mc = sum;
				sb.setLength(0);
				for(int i = 1; i <= N; i++) {
					if(select[i]) sb.append(i).append(" ");
				}
			}
			return;
		}
		for(int i = idx; i <= N; i++) {
			if(select[i]) continue;
			select[i] = true;
			cooking(i, p + cook[i].p, f + cook[i].f, s + cook[i].s, v + cook[i].v, sum + cook[i].c);
			select[i] = false;
		}
	}
	static class Cook{
		int p, f, s, v, c;

		public Cook(int p, int f, int s, int v, int c) {
			this.p = p;
			this.f = f;
			this.s = s;
			this.v = v;
			this.c = c;
		}
	}
}

