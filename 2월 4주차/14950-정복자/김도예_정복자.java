import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Prim
public class Main {

	static int n, m, t, ans;
	static boolean[] visit;
	static List<ArrayList<Edge>> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		visit = new boolean[n+1];
		for(int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.get(a).add(new Edge(b, c));
			list.get(b).add(new Edge(a, c));
		}
		prim();
		System.out.println(ans);
	}
	static void prim() {
		PriorityQueue<Edge> pq = new PriorityQueue<>((n1, n2) -> n1.c - n2.c);
		int cnt = 0;
		pq.addAll(list.get(1));
		visit[1] = true;
		while(!pq.isEmpty()) {
			if(cnt == n) break;
			Edge now = pq.poll();
			if(visit[now.end]) continue;
			ans += now.c + (cnt * t);
			visit[now.end] = true;
			cnt++;
			for(Edge e : list.get(now.end)) {
				if(!visit[e.end]) pq.add(e);
			}
		}
	}
	static class Edge {
		int end, c;

		public Edge(int end, int c) {
			this.end = end;
			this.c = c;
		}
	}
}
