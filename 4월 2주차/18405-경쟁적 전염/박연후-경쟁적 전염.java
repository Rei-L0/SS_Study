import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, k, s, x, y;

	static Queue[] q;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static int[][] board;

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
		k = Integer.parseInt(st.nextToken());

		board = new int[n][n];
		q = new ArrayDeque[k + 1];
		for (int i = 0; i < k + 1; i++)
			q[i] = new ArrayDeque<Pos>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] != 0)
					q[board[i][j]].add(new Pos(i, j));
			}
		}

		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		while (s-- > 0) {
			for (int i = 0; i < k + 1; i++) {
				q[i] = bfs(q[i], i);
			}
		}
		System.out.println(board[x - 1][y - 1]);
	}

	static Queue<Pos> bfs(Queue<Pos> q, int num) {
		Queue<Pos> nextQ = new ArrayDeque<>();

		while (!q.isEmpty()) {
			Pos now = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= n || ny >= n)
					continue;
				if (board[nx][ny] == 0) {
					nextQ.add(new Pos(nx, ny));
					board[nx][ny] = num;
				}
			}
		}
		return nextQ;
	}
}