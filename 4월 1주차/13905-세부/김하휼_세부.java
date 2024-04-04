import java.util.*;
import java.io.*;

public class Main {
	
	static int N,M,s,e;
	static long INF;
	static boolean[] visited;
	static long[] ans,min_dis;
	static ArrayList<ArrayList<Pos>> graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<ArrayList<Pos>>();
		INF = Long.MAX_VALUE;
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Pos(v,w));
			graph.get(v).add(new Pos(u,w));
		}
		dijkstra();
		System.out.println(min_dis[e] == INF ? 0 : min_dis[e]);
	}
	
	public static void dijkstra() { // 다익스트라로 최단경로를 구해주는 것이 아닌, 최솟값들중 최댓값을 출력 !! 
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		visited = new boolean[N+1];
		min_dis = new long[N+1];
		min_dis[s] = INF;
		pq.offer(new Pos(s,0));
		
		while (!pq.isEmpty()) {
			Pos cur = pq.poll();
			
			if (visited[cur.node]) continue;
			visited[cur.node] = true;
			
			for (Pos next: graph.get(cur.node)) {
				min_dis[next.node] = Math.max(min_dis[next.node], Math.min(min_dis[cur.node], next.cost));
				pq.offer(new Pos(next.node, next.cost));
			}
		}
	}
}

class Pos implements Comparable<Pos>{
	int node;
	long cost;
	public Pos(int node, long cost) {
		this.node = node;
		this.cost = cost;
	}
	@Override
	public int compareTo(Pos o) {
		return (int) ((int) o.cost - this.cost);
	}
	@Override
	public String toString() {
		return "Pos [node=" + node + ", cost=" + cost + "]";
	}
	
}
