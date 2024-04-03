import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, s, e;

	static int[] node;

	static List<List<Edge>> graph;

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Edge implements Comparable<Edge> {
		int e;
		int w;

		public Edge(int e, int w) {
			super();
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(o.w, this.w);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		graph = new ArrayList<List<Edge>>();
		node = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph.get(start).add(new Edge(end, w));
			graph.get(end).add(new Edge(start, w));
		}
		System.out.println(solve());
	}

	static int solve() {
		PriorityQueue<Edge> q = new PriorityQueue<>();
		q.add(new Edge(s, 1_000_001));

		while (!q.isEmpty()) {
			Edge now = q.poll();
			for (Edge next : graph.get(now.e)) {
				if (node[next.e] < Math.min(now.w, next.w)) {
					node[next.e] = Math.min(now.w, next.w);
					q.add(new Edge(next.e, node[next.e]));
				}
			}
		}

		return node[e];
	}
}