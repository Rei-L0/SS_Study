import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int r, c, lx, ly, rx, ry;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static char[][] board;
	static boolean[][] remove;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		board = new char[r][c];
		remove = new boolean[r][c];

		lx = r;
		ly = c;

		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				board[i][j] = s.charAt(j);
			}
		}

		removeIsland();

		for (int i = lx; i <= rx; i++) {
			for (int j = ly; j <= ry; j++) {
				if (remove[i][j]) {
					sb.append('.');
				} else {
					sb.append(board[i][j]);
				}
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

	static void removeIsland() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (board[i][j] == '.')
					continue;
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
						cnt++;
						continue;
					}
					if (board[nx][ny] == '.')
						cnt++;
				}
				if (cnt < 3) {
					check(i, j);
				} else {
					remove[i][j] = true;
				}
			}
		}
	}

	static void check(int x, int y) {
		lx = Math.min(lx, x);
		ly = Math.min(ly, y);
		rx = Math.max(rx, x);
		ry = Math.max(ry, y);
	}

}