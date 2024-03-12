import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, k, ans;

	static int[] cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		cnt = new int[1000001];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			cnt[x] = g;
		}

		int start = 0;
		int end = 2 * k;
		int res = 0;
		for (int i = start; i < Math.min(end, 1000001); i++) {
			res += cnt[i];
		}

		ans = Math.max(res, ans);
		while (end < 1000000) {
			ans = Math.max(res, ans);
			res -= cnt[start];
			start++;
			end++;
			res += cnt[end];
		}
		System.out.println(ans);
	}
}