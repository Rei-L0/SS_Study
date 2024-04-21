import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int map[][];
	static int dp[][];
	static int n;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new int[n][n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				result=Math.max(result, move(i,j));
			}
		}
		
		System.out.println(result);
	}
	
	static int move(int x, int y) { //dfs
		if(dp[x][y]!=0) return dp[x][y];
		
		dp[x][y]=1;
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
		
			if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[x][y] < map[nx][ny]) {
                dp[x][y] = Math.max(dp[x][y], move(nx, ny) + 1);
            }
		}
		
		return dp[x][y];
	}
}
