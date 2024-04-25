import java.io.*;
import java.util.*;
public class Main {
	static int N,M,A,B,C;
	static long INF;
	static ArrayList<ArrayList<Node>> arr;
	static long[] min_dis;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		
		arr = new ArrayList<ArrayList<Node>>();
		INF = 10000*500000+1;
		long max_v = Long.MIN_VALUE;
		int ans = 0;
		
		for (int i=0; i<=N; i++) {
			arr.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr.get(u).add(new Node(v,c));
			arr.get(v).add(new Node(u,c));
		}
		
		long[] fromA = dijkstra(A);
		long[] fromB = dijkstra(B);
		long[] fromC = dijkstra(C);
		
		for (int i=1; i<=N; i++) {
			long tmp = Math.min(fromA[i], Math.min(fromB[i], fromC[i]));
			if (tmp > max_v) {
				max_v = tmp;
				ans = i;
			}
		}
		System.out.println(ans);
		
		
	}
	
	static long[] dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		min_dis = new long[N+1];
		Arrays.fill(min_dis, INF);
		min_dis[start] = 0;
		pq.offer(new Node(start,0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (min_dis[cur.node] < cur.cost) continue;
			
			for (Node next: arr.get(cur.node)) {
				if (min_dis[next.node] > cur.cost + next.cost) {
					min_dis[next.node] = cur.cost + next.cost;
					pq.offer(new Node(next.node, cur.cost+next.cost));
				}
			}
		}
		return min_dis;
	}
	
	
	static class Node implements Comparable<Node>{
		int node;
		long cost;
		public Node(int node, long cost) {
			this.node = node;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return (int) ((int)this.cost - o.cost);
		}
	}
}
