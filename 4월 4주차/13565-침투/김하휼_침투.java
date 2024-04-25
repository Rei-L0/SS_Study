import java.io.*;
import java.util.*;
public class Main {
	static int N,M;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			for (int j=0; j<M; j++) {
				graph[i][j] = s.charAt(j)- '0';
			}
		}
		
		boolean flag = false;
		for (int i=0; i<M; i++) {
			if(graph[0][i] == 0) {
				if (bfs(0,i)) {
					flag = true;
					break;
				}
			}
		}
		System.out.println(flag ? "YES" : "NO");
	}
	
	static boolean bfs(int x, int y) {
		Queue<Node> q = new ArrayDeque<Node>();
		visited[x][y] = true;
		q.offer(new Node(x,y));
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.x == N-1) {
				return true;
			}
			
			for (int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!check(nx,ny)) continue;
				if (visited[nx][ny]) continue;
				if (graph[nx][ny] == 1) continue;
				
				q.offer(new Node(nx,ny));
				visited[nx][ny] = true;
			}
		}
		return false;
	}
	
	static boolean check(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) return true;
		else return false;
	}
	
	static class Node{
		int x,y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
