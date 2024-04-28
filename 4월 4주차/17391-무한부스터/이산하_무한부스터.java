import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_17391 {

	static int N, M;
	static int[][] board;
	static boolean[][] visit;
	static int[] dy = { 0, 1 };
	static int[] dx = { 1, 0 };
	static int result;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			Arrays.fill(visit[i], false);
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();
		System.out.println(result);
	}

	static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();

		queue.offer(new int[] { 0, 0, 0 }); // y, x, cnt

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int cy = current[0];
			int cx = current[1];
			int cnt = current[2];

			if (visit[cy][cx])
				continue;
			if (cy == N - 1 && cx == M - 1) {
				result = cnt;
				break;
			}
			visit[cy][cx] = true;
			int buster = board[cy][cx];

			for (int i = 0; i < 2; i++) {
				int yy, xx;
				for (int j = 1; j <= buster; j++) {
					yy = cy + dy[i] * j;
					xx = cx + dx[i] * j;
					if (yy < 0 || yy > N - 1 || xx < 0 || xx > M - 1)
						continue;
					if (visit[yy][xx])
						continue;
					queue.offer(new int[] { yy, xx, cnt + 1 });
				}
			}

		}

	}
}
