import java.io.*;
import java.util.*;
/*
 로직을 잘못 생각한거 같아서 다시 한 번 생각해보고 풀어보겠습니다..
 */
public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int r, c, n;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static char[][] board;

	static class Pos {

		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;

		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		board = new char[r][c];
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				board[i][j] = str.charAt(j);
			}
		}

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int h = Integer.parseInt(st.nextToken());
			boolean left = (i % 2 == 0);
			destroy(r - h, left);
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	static void destroy(int h, boolean left) {
		if (left) {
			for (int i = 0; i < c; i++) {
				if (board[h][i] == 'x') {
					board[h][i] = '.';
					break;
				}
			}
		} else {
			for (int i = c - 1; i >= 0; i--) {
				if (board[h][i] == 'x') {
					board[h][i] = '.';
					break;
				}
			}
		}
		drop();
	}

	static void drop() {
		int[] hubo = findDropHubo();
		int gap = r;
		for (int i = 0; i < c; i++) {
			if (hubo[i] == -1)
				continue;
			int badak = (r - 1);
			for (int j = r - 1; j > hubo[i]; j--) {
				if (board[j][i] == 'x') {
					badak = j - 1;
				}
			}
			gap = Math.min(gap, badak - hubo[i]);
		}
		if (gap == r) {
			return;
		}
		for (int i = 0; i < c; i++) {
			Queue<Character> q = new ArrayDeque<>();
			for (int j = hubo[i]; j >= 0; j--) {
				q.offer(board[j][i]);
				board[j][i] = '.';
			}
			int now = hubo[i] + gap;
			while (!q.isEmpty()) {
				if (board[now][i] == 'x' && q.peek() == '.') {
					q.poll();
					now--;
					continue;
				}
				board[now][i] = q.poll();
				now--;
			}
		}
	}

	static int[] findDropHubo() {
		boolean[][] visit = new boolean[r][c];
		int[] hubo = new int[c];
		Arrays.fill(hubo, -1);
		for (int i = r - 1; i >= 0; i--) {
			for (int j = 0; j < c; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				if (!visit[i][j] && i == (r - 1)) {
					Queue<Pos> q = new ArrayDeque<>();
					q.offer(new Pos(i, j));
					visit[i][j] = true;
					while (!q.isEmpty()) {
						Pos p = q.poll();
						for (int k = 0; k < 4; k++) {
							int nx = p.x + dx[k];
							int ny = p.y + dy[k];
							if (nx < 0 || ny < 0 || nx >= r || ny >= c || visit[nx][ny]) {
								continue;
							}
							if (board[nx][ny] == 'x') {
								q.offer(new Pos(nx, ny));
								visit[nx][ny] = true;
							}
						}
					}
				}
				if (!visit[i][j]) {
					Queue<Pos> q = new ArrayDeque<>();
					q.offer(new Pos(i, j));
					visit[i][j] = true;
					hubo[j] = Math.max(i, hubo[j]);
					while (!q.isEmpty()) {
						Pos p = q.poll();
						for (int k = 0; k < 4; k++) {
							int nx = p.x + dx[k];
							int ny = p.y + dy[k];
							if (nx < 0 || ny < 0 || nx >= r || ny >= c || visit[nx][ny]) {
								continue;
							}
							if (board[nx][ny] == 'x') {
								q.offer(new Pos(nx, ny));
								visit[nx][ny] = true;
								hubo[ny] = Math.max(nx, hubo[ny]);
							}
						}
					}
				}
			}
		}
		return hubo;
	}

}