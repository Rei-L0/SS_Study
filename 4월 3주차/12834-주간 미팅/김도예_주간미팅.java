import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 다익스트라 두 번
 */
public class Main {
	static int N, V, E, A, B, INF = Integer.MAX_VALUE;
	static int[] home, da, db;
	static ArrayList<ArrayList<Node>> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		home = new int[N];
		da = new int[V+1];
		db = new int[V+1];
		Arrays.fill(da, INF);
		Arrays.fill(db, INF);
		for(int i = 0; i <= V; i++) {
			list.add(new ArrayList<>());
		}
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			home[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}
		dijk(A, da);
		dijk(B, db);
		int sum = 0;
		for(int i = 0; i < N; i++) {
			if(da[home[i]] == INF) sum += -1;
			else sum += da[home[i]];
			if(db[home[i]] == INF) sum += -1;
			else sum += db[home[i]];
		}

		System.out.println(sum);
	}
	static void dijk(int start, int[] d) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		d[start] = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			for(Node n : list.get(node.e)) {
				if(d[n.e] > d[node.e] + n.d) {
					d[n.e] = d[node.e] + n.d; 
					pq.add(new Node(n.e, d[n.e]));
				}
			}
		}
	}
	static class Node implements Comparable<Node>{
		int e, d;

		public Node(int e, int d) {
			this.e = e;
			this.d = d;
		}
		
		@Override
		public int compareTo(Node o) {
			return d - o.d;
		}
	}
}
