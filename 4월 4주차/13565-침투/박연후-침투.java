import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, m;

	static boolean check;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int[][] board = new int[n][m];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				board[i][j] = s.charAt(j) - '0';
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0) {
					if (board[i][j] == 0) {
						bfs(new Pos(i, j), board);
					}
				}
			}
		}

		System.out.println(check ? "YES" : "NO");

	}

	static void bfs(Pos p, int[][] board) {
		Queue<Pos> q = new ArrayDeque<>();
		board[p.x][p.y] = 1;
		q.add(p);

		while (!q.isEmpty()) {
			Pos now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= m)
					continue;
				if (board[nx][ny] == 1)
					continue;
				if (nx == (n - 1))
					check = true;
				q.add(new Pos(nx, ny));
				board[nx][ny] = 1;
			}
		}
	}

}