import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// bfs + 시뮬 
public class B_21922 {

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int[][] direction = { { 0, 3, 2, 1 }, { 2, 1, 0, 3 }, { 1, 0, 3, 2 }, { 3, 2, 1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] board = new int[N][M];
		Queue<int[]> queue = new ArrayDeque<>();
		int[][] visit = new int[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visit[i], -1);
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 9) {
					for (int k = 0; k < 4; k++) {
						queue.offer(new int[] { i, j, k });
					}
				}
			}
		}
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int cy = current[0];
			int cx = current[1];
			int cd = current[2];
			if (visit[cy][cx] == cd)
				continue;

			if (board[cy][cx] == 0 || board[cy][cx] == 9) {

				visit[cy][cx] = cd;
				int yy = cy + dy[cd];
				int xx = cx + dx[cd];
				if (yy < 0 || yy > N - 1 || xx < 0 || xx > M - 1)
					continue;
				queue.offer(new int[] { yy, xx, cd });

			} else {
				if (board[cy][cx] == 1 && (cd == 1 || cd == 3)) {
					visit[cy][cx] = cd;
					continue;
				}

				if (board[cy][cx] == 2 && (cd == 0 || cd == 2)) {

					visit[cy][cx] = cd;

					continue;
				}

				visit[cy][cx] = cd;
				cd = direction[board[cy][cx] - 1][cd];
				int yy = cy + dy[cd];
				int xx = cx + dx[cd];
				if (yy < 0 || yy > N - 1 || xx < 0 || xx > M - 1)
					continue;
				queue.offer(new int[] { yy, xx, cd });

			}

		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visit[i][j] != -1)
					result += 1;
			}
		}
		System.out.println(result);

	}

}
