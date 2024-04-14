import java.io.*;
import java.util.*;

public class Main {
	static int N, K, S, X, Y;
	static int[][] graph;
	static PriorityQueue<Node> pq;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		graph = new int[N][N];
		pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] != 0) {
					pq.add(new Node(i, j, graph[i][j]));
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		for (int i = 0; i < S; i++) {
			bfs();
		}

		System.out.println(graph[X - 1][Y - 1]);

	}

	static void bfs() {
		ArrayList<Node> store = new ArrayList<>();

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (!check(nx, ny))
					continue;
				if (graph[nx][ny] != 0)
					continue;

				graph[nx][ny] = cur.num;
				store.add(new Node(nx, ny, graph[nx][ny]));
			}

		}
		for (Node next : store) {
			pq.offer(new Node(next.x, next.y, next.num));
		}
		store.clear();
	}

	static boolean check(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < N)
			return true;
		else
			return false;
	}

	static class Node implements Comparable<Node> {
		int x, y, num;

		public Node(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}

		@Override
		public int compareTo(Node o) {
			return this.num - o.num;
		}
	}
}
