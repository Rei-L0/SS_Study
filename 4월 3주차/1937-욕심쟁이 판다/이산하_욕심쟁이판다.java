import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 큰 수에 visit 값이 더 큰 값이 들어가는 것이 아닌
// 시작점 (작은 수)에 그 깊이 값들을 저장
public class Main {

	static int N;
	static int[][] board;
	static int[][] visit;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, -1, 0, 1 };
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visit = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result = Math.max(result, dfs(i, j));
			}
		}
		System.out.println(result);
	}
	static int dfs(int sy, int sx) {
		if (visit[sy][sx] != 0)
			return visit[sy][sx];
		else {
			visit[sy][sx] = 1;

			for (int i = 0; i < 4; i++) {
				int yy = sy + dy[i];
				int xx = sx + dx[i];
				if (yy < 0 || xx < 0 || yy >= N || xx >= N)
					continue;
				if (board[yy][xx] > board[sy][sx]) {
					visit[sy][sx] = Math.max(visit[sy][sx], dfs(yy, xx) + 1);
				}
			}
			return visit[sy][sx];
		}
	}
}
