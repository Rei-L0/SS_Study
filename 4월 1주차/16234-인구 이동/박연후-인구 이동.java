import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, lo, hi;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static StringTokenizer st;
	static StringBuilder sb;

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
		lo = Integer.parseInt(st.nextToken());
		hi = Integer.parseInt(st.nextToken());

		int[][] nation = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				nation[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int time = 0;
		while (bfs(nation)) {
			time++;
		}
		System.out.println(time);
	}

	static boolean bfs(int[][] arr) {
		boolean check = false;
		boolean[][] visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visit[i][j])
					continue;
				Queue<Pos> q = new ArrayDeque<>();
				Queue<Pos> res = new ArrayDeque<>();
				q.add(new Pos(i, j));
				res.add(new Pos(i, j));
				visit[i][j] = true;
				int cnt = arr[i][j];
				while (!q.isEmpty()) {
					Pos now = q.poll();
					int people = arr[now.x][now.y];
					for (int d = 0; d < 4; d++) {
						int nx = now.x + dx[d];
						int ny = now.y + dy[d];
						if (nx < 0 || ny < 0 || nx >= n || ny >= n)
							continue;
						if (visit[nx][ny])
							continue;
						int diff = Math.abs(people - arr[nx][ny]);
						if (lo <= diff && diff <= hi) {
							check = true;
							q.add(new Pos(nx, ny));
							res.add(new Pos(nx, ny));
							cnt += arr[nx][ny];
							visit[nx][ny] = true;
						}
					}
				}
				if (res.size() > 1) {
					int afterMove = cnt / res.size();
					while (!res.isEmpty()) {
						Pos now = res.poll();
						arr[now.x][now.y] = afterMove;
					}
				}
			}
		}
		return check;
	}

}