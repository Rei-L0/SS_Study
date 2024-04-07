import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, X, Y, INF = Integer.MAX_VALUE;
	static long min;
	static int[] p;
	static HashMap<Integer, long[]> map = new HashMap<>();
	static ArrayList<ArrayList<Node>> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}
		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		int pn = Integer.parseInt(br.readLine());
		p = new int[pn];
		map.put(X, dijk(X));
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < pn; i++){
			p[i] = Integer.parseInt(st.nextToken());
			map.put(p[i], dijk(p[i]));
			//System.out.println(p[i] + " \n" + Arrays.toString(map.get(p[i])));
		}
		
		min = Long.MAX_VALUE;
		for(int i = 0; i < pn; i++) {
			for(int j = 0; j < pn; j++) {
				if(i == j) continue;
				for(int k = 0; k < pn; k++) {
					if(i == k || j == k) continue;
					long sum = map.get(X)[p[i]] + map.get(p[i])[p[j]] + map.get(p[j])[p[k]] + map.get(p[k])[Y];
					if(min > sum) {
//						System.out.println(p[i] + " " + p[j] + " " + p[k]);
//						System.out.println( map.get(X)[p[i]] +" " + map.get(p[i])[p[j]] + " " +map.get(p[j])[p[k]] + " " +map.get(p[k])[Y]);
						min = sum;
					}
				}
			}
		}
		System.out.println(min >= INF ? -1 : min);
	}
	static long[] dijk(int start) {
		long[] cost = new long[N+1];
		Arrays.fill(cost, INF);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		cost[start] = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(node.cost > cost[node.end]) continue;
			for(Node n : list.get(node.end)) {
				if(cost[n.end] > cost[node.end] + n.cost) {
					cost[n.end] = cost[node.end] + n.cost;
					pq.offer(new Node(n.end, cost[n.end]));
				}
			}
		}
		return cost;
	}
	static class Node implements Comparable<Node>{
		int end;
		long cost;
		
		public Node(int end, long cost) {
			this.end = end;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Node [end=" + end + ", cost=" + cost + "]";
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
	}
}
