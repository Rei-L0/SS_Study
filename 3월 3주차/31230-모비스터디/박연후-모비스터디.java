// 31230
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, e1, e2, cnt;
	static long d;

	static long[] dis1, dis2;

	static ArrayDeque<Integer> ans = new ArrayDeque<>();
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static ArrayList<ArrayList<Edge>> graph;

	static class Edge implements Comparable<Edge> {

		int to;
		long cost;

		public Edge(int to, long cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		e1 = Integer.parseInt(st.nextToken());
		e2 = Integer.parseInt(st.nextToken());

		dis1 = new long[n + 1];
		dis2 = new long[n + 1];

		graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Edge>());
		}

		Arrays.fill(dis1, Long.MAX_VALUE);
		Arrays.fill(dis2, Long.MAX_VALUE);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Edge(b, c));
			graph.get(b).add(new Edge(a, c));
		}

		dijk(e1, dis1);
		dijk(e2, dis2);

		for (int i = 0; i < n + 1; i++) {
			if (dis1[e2] == (dis1[i] + dis2[i]))
				ans.offer(i);
		}

		System.out.println(ans.size());
		while (!ans.isEmpty()) {
			System.out.print(ans.poll() + " ");
		}

	}

	static void dijk(int start, long[] distance) {
		distance[start] = 0;
		pq.offer(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (distance[now.to] < now.cost) {
				continue;
			}
			for (Edge next : graph.get(now.to)) {
				if (distance[next.to] > distance[now.to] + next.cost) {
					distance[next.to] = distance[now.to] + next.cost;
					pq.offer(new Edge(next.to, distance[next.to]));
				}
			}
		}
	}

}