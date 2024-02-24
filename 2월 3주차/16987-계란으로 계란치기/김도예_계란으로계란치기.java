import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, max;
	static Egg[] eggs;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		eggs = new Egg[n];
		max = 0;
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		dfs(0);

		System.out.println(max);
	}
	static void dfs(int idx) {
		if(idx == n) {
			int cnt = 0;
			for(Egg e : eggs) {
				if(e.s <= 0) cnt++;
			}
			max = Math.max(cnt,  max);
			return;
		}
		if(eggs[idx].s <= 0) { // 손에 든 계란이 깨져있을 때, 다음으로 넘김
			dfs(idx+1);
			return;
		}
		boolean f = false; // false 이면 깰 계란이 없는 경우
		for(int i = 0; i < n; i++) { // 깰 계란 찾기
			if(eggs[i].s <= 0 || i == idx) { // 계란이 깨져있거나 지금 계란과 같은 계란을 고려하는 경우 넘어감
				continue;
			}
			f = true;
			eggs[idx].s -= eggs[i].w;
			eggs[i].s -= eggs[idx].w;
			dfs(idx+1);
			eggs[idx].s += eggs[i].w;
			eggs[i].s += eggs[idx].w;

		}
		if(!f) {
			dfs(idx + 1);
		}
	}
	static class Egg{
		int s, w;
		public Egg(int s, int w) {
			this.w = w;
			this.s = s;
		}
	}
}
