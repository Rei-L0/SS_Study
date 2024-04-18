import java.io.*;
import java.util.*;

public class Main {
	static int N,V,E,A,B,INF,ans;
	static int[] house,min_dis;
	static ArrayList<ArrayList<Node>> arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		house = new int[N];
		arr = new ArrayList<ArrayList<Node>>();
		INF = 100*10000+1;
		ans = 0;
		
		for (int i=0; i<=V; i++) {
			arr.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			house[i] = Integer.parseInt(st.nextToken());
		}
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr.get(a).add(new Node(b,c));
			arr.get(b).add(new Node(a,c));
		}
		
		for (int i=0; i<N; i++) {
			int[] tmp = dijkstra(house[i]);
			int toKist = tmp[A];
			int toSR = tmp[B];
			int res = 0;
			
			if (toKist == INF) res += -1;
			else res += toKist;
			
			if (toSR == INF) res += -1;
			else res += toSR;
			
			ans += res;
		}
		System.out.println(ans);

	}
	
	static int[] dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		min_dis = new int[V+1];
		Arrays.fill(min_dis,INF);
		min_dis[start] = 0;
		pq.offer(new Node(start,0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(min_dis[cur.node] < cur.cost) continue;
			
			for (Node next: arr.get(cur.node)) {
				if (min_dis[next.node] > next.cost + cur.cost) {
					min_dis[next.node] = next.cost + cur.cost;
					pq.offer(new Node(next.node, next.cost+cur.cost));
				}
			}
		}
		return min_dis;
	}
	
	static class Node implements Comparable<Node>{
		int node, cost;
		
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
