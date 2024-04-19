import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * dfs
 */
public class Main {
	static int R, C, K, cnt;
	static char[][] map;
	static boolean[][] visit;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		visit[R-1][0] = true;
		dfs(R-1, 0, 1);
		System.out.println(cnt);
	}
	static void dfs(int y, int x, int c) {
		if(c > K) return;
		if(y == 0 && x == C-1 && c == K) {
			cnt++;
			return;
		}
		for(int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny < 0 || nx < 0 || ny >= R || nx >= C || visit[ny][nx] || map[ny][nx] == 'T') continue;
			visit[ny][nx] = true;
			dfs(ny, nx, c+1);
			visit[ny][nx] = false;
		}
	}
}
