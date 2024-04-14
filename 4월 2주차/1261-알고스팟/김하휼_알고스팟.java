import java.util.*;
import java.io.*;

public class Main {
	
	static int N,M,INF;
	static int[][] graph;
	
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int[][] min_dis;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken()); 
		
		graph = new int[N][M];
		
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			for (int j=0; j<M; j++) {
				graph[i][j] = s.charAt(j) -'0';
			}
		}
		int res = dijkstra();
		System.out.println(res);
		
		
	}
	
	static int dijkstra() {
		PriorityQueue<algo> pq = new PriorityQueue<>();
		visited = new boolean[N][M];
		visited[0][0] = true;
		pq.offer(new algo(0,0,0));
		
		while (!pq.isEmpty()) {
			algo cur = pq.poll();
			
			if (cur.x == N-1 && cur.y == M-1) {
				return cur.cnt;
			}
			
			for (int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!check(nx,ny)) continue;
				
				if (visited[nx][ny]) continue;
				
				if (graph[nx][ny] == 1) {
					pq.offer(new algo(nx,ny,cur.cnt+1));
				}
				else if (graph[nx][ny] == 0) {
					pq.offer(new algo(nx,ny,cur.cnt));
				}
				visited[nx][ny] = true;
				
			}
		}
		return 0;
		
	}
	
	static boolean check(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < M) return true;
		else return false;
	}
	
	static class algo implements Comparable<algo>{
		int x, y, cnt;
		public algo(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(algo o) {
			return this.cnt - o.cnt;
		}
	}
}
