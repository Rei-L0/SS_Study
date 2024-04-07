import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// prim
public class Main {

	static int N, M, s, e, min = Integer.MAX_VALUE;
	static boolean[] visit;
	static ArrayList<ArrayList<Node>> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		visit = new boolean[N+1];
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int h1 = Integer.parseInt(st.nextToken());
			int h2 = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			list.get(h1).add(new Node(h2, k));
			list.get(h2).add(new Node(h1, k));
		}
		prim();
		System.out.println(min == Integer.MAX_VALUE ? 0 : min);
	}
	static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		visit[s] = true;
		pq.addAll(list.get(s));
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			min = Math.min(min, node.c);
			visit[node.e] = true;
			if(node.e == e) {
				return;
			}
			for(Node n : list.get(node.e)) {
				if(!visit[n.e]) pq.offer(n);
			}
		}
	}
	static class Node implements Comparable<Node>{
		int e, c;

		public Node(int e, int c) {
			this.e = e;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return o.c - c;
		}
	}
}
