import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// prim
public class Main {

	static int N, M, max, min;
	static boolean[] visit;
	static ArrayList<Edge>[] edges;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		visit = new boolean[N+1];
		
		// 0 -> 1
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());
		if(sc == 0) {
			max = 1;
			min = 1;
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges[a].add(new Edge(b, c));
			edges[b].add(new Edge(a, c));
		}
    
		prim(1);
		System.out.println((int) (Math.pow(max, 2) - Math.pow(min, 2)));
	}
  
	static void prim(int start) {
		PriorityQueue<Edge> uppq = new PriorityQueue<>((n1, n2) -> n1.c - n2.c);
		uppq.addAll(edges[start]);
		int cnt = 1;
    visit[0] = true;
		visit[1] = true;
		while(!uppq.isEmpty()) {
			Edge edge = uppq.poll();
			if(visit[edge.next]) continue;
			visit[edge.next] = true;
			if(edge.c == 0) max++;
			for(Edge e : edges[edge.next]) {
				if(!visit[e.next]) uppq.offer(e);
			}
			cnt++;
			if(cnt == N) break;
		}

		Arrays.fill(visit, false);
		visit[0] = true;
		visit[1] = true;
		PriorityQueue<Edge> downpq = new PriorityQueue<>((n1, n2) -> n2.c - n1.c);
		downpq.addAll(edges[start]);
		cnt = 1;
		while(!downpq.isEmpty()) {
			Edge edge = downpq.poll();
			if(visit[edge.next]) continue;
			visit[edge.next] = true;
			if(edge.c == 0) min++;
			for(Edge e : edges[edge.next]) {
				if(!visit[e.next]) downpq.offer(e);
			}
			cnt++;
			if(cnt == N) break;
		}
		
	}
	static class Edge{
		int next, c;

		public Edge(int next, int c) {
			this.next = next;
			this.c = c;
		}
	}
}
