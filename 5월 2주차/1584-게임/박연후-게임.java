import java.io.*;
import java.util.*;

public class Main {

	static final int SAFE = 0, DANGER = 1, DEATH = 2;

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, m;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static int[][] status = new int[501][501], board = new int[501][501];

	static class Info implements Comparable<Info> {
		int x;
		int y;
		int h;

		public Info(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}

		@Override
		public int compareTo(Main.Info o) {
			return Integer.compare(this.h, o.h);
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 501; i++) {
			Arrays.fill(board[i], Integer.MAX_VALUE);
		}

		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
				for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
					status[x][y] = DANGER;
				}
			}
		}

		m = Integer.parseInt(br.readLine());

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
				for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
					status[x][y] = DEATH;
				}
			}
		}

		PriorityQueue<Info> pq = new PriorityQueue<>();
		pq.add(new Info(0, 0, 0));
		board[0][0] = 0;

		while (!pq.isEmpty()) {
			Info cur = pq.poll();
			int x = cur.x;
			int y = cur.y;
			for (int i = 0; i < 4; i++) {
				int h = cur.h;
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= 501 || ny >= 501)
					continue;
				if (status[nx][ny] == DEATH)
					continue;
				else if (status[nx][ny] == DANGER) {
					h++;
				}
				if (board[nx][ny] > h) {
					board[nx][ny] = h;
					pq.offer(new Info(nx, ny, h));
				}
			}
		}
		System.out.println(board[500][500] == Integer.MAX_VALUE ? -1 : board[500][500]);
	}

}