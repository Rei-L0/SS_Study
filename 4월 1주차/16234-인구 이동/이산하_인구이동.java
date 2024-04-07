import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, L, R;
	static int[][] board, new_board;
	static boolean[][] visit;
	static int[] dy = new int[] { -1, 0, 1, 0 };
	static int[] dx = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		board = new int[N][N];
		new_board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int t = 0;
		while (true) {

			visit = new boolean[N][N];
			boolean check = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visit[i][j])
						continue;
					if (bfs(i, j) > 1) {
						check = true;
					}
				}
			}
			if (!check)// 연합한 국가들이 없었다면
			{
				break;
			}
			for (int i = 0; i < N; i++) {
				board[i] = new_board[i].clone();
			}

			t += 1;
		}
		System.out.println(t);

	}

	static int bfs(int sy, int sx) {

		Queue<int[]> queue = new ArrayDeque<>(); // bfs를 위한 queue
		Queue<int[]> queue2 = new ArrayDeque<>(); // 나라 상태 변화를 위한 queue - 인구 이동할 나라들을 담을 queue
		queue.offer(new int[] { sy, sx });
		queue2.offer(new int[] { sy, sx });
		visit[sy][sx] = true;

		int sumPeople = 0;
		int countryCnt = 0;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int ny = now[0];
			int nx = now[1];

			sumPeople += board[ny][nx];
			countryCnt += 1;

			for (int i = 0; i < 4; i++) {
				int yy = ny + dy[i];
				int xx = nx + dx[i];

				if (yy < 0 || yy > N - 1 || xx < 0 || xx > N - 1)
					continue;
				if (visit[yy][xx])
					continue;

				int gap = Math.abs(board[ny][nx] - board[yy][xx]);

				if (gap >= L && gap <= R) {
					visit[yy][xx] = true;
					queue.offer(new int[] { yy, xx });
					queue2.offer(new int[] { yy, xx });
				}
			}
		}

		// 연합할 국가들이 없다면 기존의 값 담아줌.
		if (countryCnt == 1) {

			new_board[sy][sx] = board[sy][sx];

		} else if (countryCnt > 1) { // 연합할 국가들이 있다면 new_board에 값 담아줌

			int movePeople = sumPeople / countryCnt;
			while (!queue2.isEmpty()) {
				int[] now = queue2.poll();

				new_board[now[0]][now[1]] = movePeople;

			}

		}
		return countryCnt;
	}
}
