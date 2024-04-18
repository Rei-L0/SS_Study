import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, ans;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static int[][] num, dp;

	static PriorityQueue<Pos> pq = new PriorityQueue<>(Comparator.comparing(o -> -o.num));

	static class Pos {
		int x;
		int y;
		int num;

		public Pos(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		num = new int[n][n];
		dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				num[i][j] = Integer.parseInt(st.nextToken());
				pq.add(new Pos(i, j, num[i][j]));
			}
		}

		solve();

		System.out.println(ans);
	}

	static void solve() {
		while (!pq.isEmpty()) {
			Pos now = pq.poll();
			int max = 0;
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;
				if (num[nx][ny] > num[now.x][now.y])
					max = Math.max(max, dp[nx][ny]);
			}
			dp[now.x][now.y] = max + 1;
			ans = Math.max(ans, dp[now.x][now.y]);
		}
	}
}