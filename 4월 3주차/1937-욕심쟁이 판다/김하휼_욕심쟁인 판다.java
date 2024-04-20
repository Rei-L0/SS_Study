import java.io.*;
import java.util.*;
 
class Main {
	static int N,max_v;
	static int[][] graph,dp;
	static boolean[][] visited;

	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());

		graph = new int[N][N];
		dp = new int[N][N];
		max_v = Integer.MIN_VALUE;

		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		} 

		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				max_v = Math.max(dfs(i,j),max_v);
			}
		}
		System.out.println(max_v);
	}

	static int dfs(int x, int y) {

		if (dp[x][y] != 0) {
			return dp[x][y];
		}

		dp[x][y] = 1;

		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (!check(nx, ny)) continue;
			if (graph[x][y] >= graph[nx][ny])  continue;

			dp[x][y] = Math.max(dp[x][y], dfs(nx,ny)+1);
			dfs(nx,ny);
		}	

		return dp[x][y];
	}

	static boolean check(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < N) return true;
		else return false;
	}

}
