package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
// visit 함수를 써보쟝
public class B_4179 {

	static int R, C, answer = Integer.MAX_VALUE;
	static String[][] board;
	static boolean [][] visit;
	// static Pos jihun, fire;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static Queue<Pos> jihunQueue = new ArrayDeque<>();
	static Queue<Pos> fireQueue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new String[R][C];
		for (int i = 0; i < R; i++) {
			String[] input = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				board[i][j] = input[j];
				if (board[i][j].equals("J")) {
					jihunQueue.offer(new Pos(i, j, 0));
				} else if (board[i][j].equals("F")) {
					fireQueue.offer(new Pos(i, j, -1));
				}
			}
		}
		while (true) {
			// 지훈 이동
			move_jihun();

			// 불 이동
			spread_fire();

			if (jihunQueue.isEmpty() || answer != Integer.MAX_VALUE)
				break;

		}
		if (answer == Integer.MAX_VALUE) {
			System.out.println("IMPOSSIBLE");
		} else {
			System.out.println(answer);
		}
	}

	static void move_jihun() {

		Queue<Pos> tempQueue = new ArrayDeque<>();
		while (!jihunQueue.isEmpty()) {
			Pos nowJihun = jihunQueue.poll();
			int y = nowJihun.y;
			int x = nowJihun.x;
			
			if (board[y][x].equals("F"))
				continue;
			for (int i = 0; i < 4; i++) {
				int yy = y + dy[i];
				int xx = x + dx[i];
				if (yy < 0 || yy >= R || xx < 0 || xx >= C) {
					answer = Math.min(answer, nowJihun.cnt + 1);
					continue;
				}
				if (!board[yy][xx].equals(".")) continue;
					board[y][x] = ".";
					board[yy][xx] = "J";
					tempQueue.offer(new Pos(yy, xx, nowJihun.cnt + 1));
			

			}
		}

		while (!tempQueue.isEmpty()) {
			jihunQueue.offer(tempQueue.poll());
		}

	}

	static void spread_fire() {
		Queue<Pos> tempQueue = new ArrayDeque<>();
		while (!fireQueue.isEmpty()) {
			Pos nowFire = fireQueue.poll();
			int y = nowFire.y;
			int x = nowFire.x;

			for (int i = 0; i < 4; i++) {
				int yy = y + dy[i];
				int xx = x + dx[i];
				if (yy < 0 || yy >= R || xx < 0 || xx >= C || board[yy][xx].equals("#"))
					continue;

				board[yy][xx] = "F";
				tempQueue.offer(new Pos(yy, xx, -1));

			}
		}
		while (!tempQueue.isEmpty()) {
			fireQueue.offer(tempQueue.poll());
		}

	}

	static class Pos {
		int y;
		int x;
		int cnt;

		Pos(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Pos [y=" + y + ", x=" + x + ", cnt=" + cnt + "]";
		}

	}
}
