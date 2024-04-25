import java.io.*;
import java.util.*;

/*
오른쪽이랑 밑으로만 갈 수 있는 bfs 
*/
 
class Main {
	static int N,M;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {0,1};
	static int[] dy = {1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new int[N][M];

		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = bfs();
		System.out.println(ans);
	}

	static int bfs() {
		Queue<Node> q = new ArrayDeque<>();
		visited = new boolean[N][M];
		visited[0][0] = true;
		q.offer(new Node(0,0,0));

		while (!q.isEmpty()) {
				Node cur = q.poll();

				if (cur.x == N-1 && cur.y == M-1) {
					return cur.cnt;
				}
	
				for (int i=0; i<2; i++) {
					for (int j=1;j<=graph[cur.x][cur.y]; j++) {
						int nx = cur.x + dx[i]*j;
						int ny = cur.y + dy[i]*j;
	
						if (!check(nx,ny)) continue;
            if (visited[nx][ny]) continue;
						
            visited[nx][ny] = true;
            q.offer(new Node(nx,ny,cur.cnt+1));
						
					}
				}
		}
		return 0;
	}

	static boolean check(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) return true;
		else return false;
	}

	static class Node{
		int x,y,cnt;
		public Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}	
	}
}
