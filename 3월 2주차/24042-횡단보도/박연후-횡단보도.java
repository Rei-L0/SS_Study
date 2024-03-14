import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final long INF = Long.MAX_VALUE;

	static int n, m;

	static long[] dis;
	static boolean[] visited;

	static ArrayList<ArrayList<Edge>> graph;
	static PriorityQueue<Edge> pq = new PriorityQueue<>();

	static class Edge implements Comparable<Edge> {
		int e;
		long time;

		public Edge(int e, long time) {
			this.e = e;
			this.time = time;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.time, o.time);
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		dis = new long[n + 1];
		visited = new boolean[n + 1];

		Arrays.fill(dis, INF);

		for (int i = 0; i < n + 1; i++)
			graph.add(new ArrayList<Edge>());

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			graph.get(s).add(new Edge(e, i + 1));
			graph.get(e).add(new Edge(s, i + 1));
		}

		solve();
		System.out.println(dis[n]);
	}

	// 다익스트라
	// 노드에 시간 표시
	// 현재 노드 시간이 다음으로 가는 간선의 시간보다 클 경우
	// 현재 노드 시간과 작거나 같을 때 까지 m을 더해준다.
	static void solve() {
		pq.add(new Edge(1, 0));
		dis[1] = 0;

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (visited[now.e])
				continue;
			if (now.e == n)
				return;
			visited[now.e] = true;
			for (Edge next : graph.get(now.e)) {
				if (visited[next.e])
					continue;
				long time = next.time;
				if (time < dis[now.e]) {
					while (time < dis[now.e]) {
						time += m;
					}
				}
				if (dis[next.e] > time) {
					dis[next.e] = time;
					pq.add(new Edge(next.e, dis[next.e]));
				}
			}
		}

	}
}