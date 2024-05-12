package boj1584;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * bfs + PQ
 * 안전 구역은 0, 위험 구역은 1, 죽음 구역은 2로 초기화함
 * PQ를 사용해 최단 경로를 구함 
 */
public class Main {
	static int[][] map = new int[501][501];
	static boolean[][] visit = new boolean[501][501];
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int ans = -1;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			makemap(x1, x2, y1, y2, 1);
		}
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			makemap(x1, x2, y1, y2, 2);
		}
		bfs();
		System.out.println(ans);
	}
	static void bfs() {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(0, 0, 0));
		visit[0][0] = true;
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(node.y == 500 && node.x == 500) {
				ans = node.c;
				return;
			}
			for(int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				if(ny < 0 || nx < 0 || ny > 500 || nx > 500 || visit[ny][nx] || map[ny][nx] == 2) continue;
				visit[ny][nx] = true;
				if(map[ny][nx] == 1) q.offer(new Node(ny, nx, node.c + 1));
				else q.offer(new Node(ny, nx, node.c));
			}
		}
	}
	static class Node implements Comparable<Node>{
		int y, x, c;

		public Node(int y, int x, int c) {
			this.y = y;
			this.x = x;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return c - o.c;
		}
	}
	static void makemap(int x1, int x2, int y1, int y2, int w) {
		int i, j, n, m;
		if(x1 < x2) {
			i = x1;
			n = x2;
		}
		else {
			i = x2;
			n = x1;
		}
		if(y1<y2) {
			j = y1;
			m = y2;
		}
		else {
			j = y2;
			m = y1;
		}
		for(int y = j; y <= m; y++) {
			for(int x = i; x <= n; x++) {
				map[y][x] = w;
			}
		}
	}
}
