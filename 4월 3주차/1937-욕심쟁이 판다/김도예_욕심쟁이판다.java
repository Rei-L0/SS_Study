import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * dfs + dp
 */
public class Main {

	static int N, max, cnt;
	static int[][] map;
	static int[][] dp;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				max = Math.max(max, dfs(i, j));
			}
		}
		System.out.println(max);
	}
	static int dfs(int y, int x) {
		if(dp[y][x] != 0) return dp[y][x];
		dp[y][x] = 1;
		for(int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
			if(map[ny][nx] > map[y][x]) {
				dp[y][x] = Math.max(dp[y][x], dfs(ny, nx) + 1);
			}
		}
		return dp[y][x];
	}
}

