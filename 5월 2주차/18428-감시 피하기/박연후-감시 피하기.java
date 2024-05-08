import java.io.*;
import java.util.*;

public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;

	static String ans = "NO";

	static char[][] board;

	static List<Pos> avail = new ArrayList<>();

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

		n = Integer.parseInt(br.readLine());

		board = new char[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = st.nextToken().charAt(0);
				if (board[i][j] == 'X') {
					avail.add(new Pos(i, j));
				}
			}
		}

		makeWall(0, 0, new int[3]);

		System.out.println(ans);
	}

	static void makeWall(int start, int len, int[] arr) {
		if (len == 3) {
			char[][] map = new char[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = board[i][j];
				}
			}
			for (int i = 0; i < 3; i++) {
				Pos wall = avail.get(arr[i]);
				map[wall.x][wall.y] = 'O';
			}
			gamsi(map);
			return;
		}
		for (int i = start; i < avail.size(); i++) {
			arr[len] = i;
			makeWall(start + 1, len + 1, arr);
		}
	}

	static void gamsi(char[][] map) {
		int[] dx = { 0, 0, 1, -1 };
		int[] dy = { 1, -1, 0, 0 };

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] == 'T') {
					for (int d = 0; d < 4; d++) {
						int x = i;
						int y = j;
						while (true) {
							int nx = x + dx[d];
							int ny = y + dy[d];
							if (nx < 0 || ny < 0 || nx >= n || ny >= n)
								break;
							if (map[nx][ny] == 'O')
								break;
							if (map[nx][ny] == 'S')
								return;
							x = nx;
							y = ny;
						}
					}
				}
			}
		}

		ans = "YES";
	}

}