import java.io.*;
import java.util.*;

// 맞왜틀 5번째에 맞춤. 원인은 Node 클래스에 cost 타입을 long으로 설정 안해줬기 때문,,  
class Main { 
	static int N,M,A,B;
	static long INF;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static long[] min_dis1,min_dis2;
	static ArrayList<Integer> ans = new ArrayList<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}

		INF = Long.MAX_VALUE;

		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b,c));
			graph.get(b).add(new Node(a,c));
		}
		
		dijkstra1(A);
		dijkstra2(B);

		long shortest = min_dis1[B];

		for(int i=1; i<N+1; i++) {

			long fromA = min_dis1[i];
			long fromB = min_dis2[i];
			if (fromA+fromB == shortest) {
				ans.add(i);
			}
		}
		
		Collections.sort(ans);
		for (int x:ans) {
			sb.append(x).append(" ");
		}
		System.out.println(ans.size());
		System.out.println(sb);
	}

	public static void dijkstra1(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		min_dis1 = new long[N+1];

		Arrays.fill(min_dis1, INF);
		pq.offer(new Node(start,0));
		min_dis1[start] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (min_dis1[cur.v] < cur.cost) continue;

			for (Node next: graph.get(cur.v)) {
				if (min_dis1[next.v] > cur.cost + next.cost) {
					min_dis1[next.v] = cur.cost + next.cost;
					pq.offer(new Node(next.v,cur.cost+next.cost));
				}
			}
		}
	}
	
	public static void dijkstra2(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		min_dis2 = new long[N+1];

		Arrays.fill(min_dis2, INF);
		pq.offer(new Node(start,0));
		min_dis2[start] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (min_dis2[cur.v] < cur.cost) continue;

			for (Node next: graph.get(cur.v)) {
				if (min_dis2[next.v] > cur.cost + next.cost) {
					min_dis2[next.v] = cur.cost + next.cost;
					pq.offer(new Node(next.v,cur.cost+next.cost));
				}
			}
		}
	}
}

class Node implements Comparable<Node>{
	int v;
	long cost;
	
	public Node(int v, long cost) {
		this.v = v;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return (int) ((int) this.cost - o.cost);
	}
}
