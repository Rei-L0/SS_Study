import java.io.*;
import java.util.*;
public class Main {
	static int R,C,K,ans;
	static char[][] arr;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new char[R][C];
		visited= new boolean[R][C];
		ans = 0;
		
		for (int i=0; i<R; i++) {
			String s = br.readLine();
			for (int j=0; j<C; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		
		visited[R-1][0] = true;
		dfs(R-1,0,1);
		System.out.println(ans);

	}
	
	static void dfs(int x, int y, int cnt) {
		if (x == 0 && y == C-1) {
			if (cnt == K) {
				ans++;
			}
			return;
		}
		
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (!check(nx,ny)) continue;
			if (arr[nx][ny] == 'T') continue;
			if (visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			dfs(nx,ny,cnt+1);
			visited[nx][ny] = false;		
		}	
	}
	
	static boolean check(int x, int y) {
		if (0 <= x && x < R && 0 <= y && y < C) return true;
		else return false;
	}

}
