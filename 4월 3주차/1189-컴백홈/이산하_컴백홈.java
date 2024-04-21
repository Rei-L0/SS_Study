import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1189 {

	static int R, C, K;
	static char[][] map;
	static int[][] visited;
	static int answer;
	
	static int[] dy = {1, -1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {
    BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
	
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new int[R][C];
		
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int j=0; j<C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		visited[R-1][0] = 1;
		dfs(R-1, 0, 1);
		
		System.out.println(answer);

	}
	
	static void dfs(int y, int x, int depth) {
		//도착 
		if(y == 0 && x == C-1) {
			if(depth == K)
				answer++;
			return;
		}
		
		for(int i=0; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny<0 || ny>=R || nx<0 ||nx>=C)
				continue;
			if(visited[ny][nx] == 1 || map[ny][nx] == 'T')
				continue;
			visited[ny][nx] = 1;
			dfs(ny, nx, depth+1);
			visited[ny][nx] = 0;
			
		}
		
	}

}
