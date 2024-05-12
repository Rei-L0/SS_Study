import java.util.*;
import java.io.*;

class Main {
  
	static int N,M;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  StringTokenizer st;

	  graph = new int[501][501];
	  N = Integer.parseInt(br.readLine());
	  for (int k=0; k<N; k++) {
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		for (int i = Math.min(x1,x2); i<= Math.max(x1,x2); i++) {
			for (int j= Math.min(y1,y2); j <= Math.max(y1,y2); j++) {
				graph[i][j] = 1;
			}
		}
	  }
	
	  M = Integer.parseInt(br.readLine());
	  for (int k=0; k<M; k++) {
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		for (int i = Math.min(x1,x2); i<= Math.max(x1,x2); i++) {
			for (int j= Math.min(y1,y2); j <= Math.max(y1,y2); j++) {
				graph[i][j] = -1;
			}
		}
	  }

	  long ans = bfs();
	  System.out.println(ans);
	}

	static long bfs() {
		Deque<Node> q = new ArrayDeque<>();
		visited = new boolean[501][501];
		visited[0][0] = true;
		q.offer(new Node(0,0,0));

		while (!q.isEmpty()){
			Node cur = q.poll();

			if (cur.x == 500 && cur.y == 500) {
				return cur.hp;
			}

			for (int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (!check(nx, ny)) continue;
				if (visited[nx][ny]) continue;
				if (graph[nx][ny] == -1) continue;

				if (graph[nx][ny] == 0) {
					q.offerFirst(new Node(nx,ny,cur.hp));
					visited[nx][ny] = true;
				}
				else if (graph[nx][ny] == 1) {
					q.offer(new Node(nx,ny,cur.hp+1));
					visited[nx][ny] = true;
				}
			}
		}
		return -1;
	}

	static boolean check(int x, int y) {
		if (0 <= x && x < 501 && 0 <= y && y < 501) return true;
		else return false;
	}	

	static class Node{
		int x, y;
		long hp;
		public Node(int x, int y, long hp) {
			this.x = x;
			this.y = y;
			this.hp = hp;
		}
	}
}
