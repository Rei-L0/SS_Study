import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// MST 문제
// 각 서버를 노드라고 생각하고 크루스칼 알고리즘 적용
public class Main {

	static int t, r, c, ans;

	static int[] p;

	static PriorityQueue<Edge> edges;

	static class Edge implements Comparable<Edge> {
		int s;
		int e;
		int w;

		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		t = Integer.parseInt(st.nextToken());
		for (int z = 0; z < t; z++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			ans = 0;

			p = new int[r * c];
			for (int i = 0; i < r * c; i++) {
				p[i] = i;
			}

			edges = new PriorityQueue<>();

			for (int i = 0; i < r; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < c - 1; j++) {
					int node = i * c + j;
					int w = Integer.parseInt(st.nextToken());
					edges.add(new Edge(node, node + 1, w));
				}
			}

			for (int i = 0; i < r - 1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < c; j++) {
					int node = i * c + j;
					int w = Integer.parseInt(st.nextToken());
					edges.add(new Edge(node, node + c, w));
				}
			}

			while (!edges.isEmpty()) {
				Edge now = edges.poll();
				if (union(now.s, now.e)) {
					ans += now.w;
				}
			}
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static int find(int x) {
		if (x == p[x])
			return x;
		return p[x] = find(p[x]);
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y)
			return false;
		p[x] = p[y];
		return true;
	}

}