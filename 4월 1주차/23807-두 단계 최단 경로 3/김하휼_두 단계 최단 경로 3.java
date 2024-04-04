import java.util.*;
import java.io.*;

/*

1. 문제가 좀 헷갈리긴 했음. 
2. 최댓값 범위 설정해주는 거 때문에 많이 틀렸음. 최단 경로 문제는 이런 부분 잘 생각해줘야 할듯 ! 

*/

public class test {
	static int N,M,P,start,end;
	static long INF,min_v;
	static ArrayList<ArrayList<Node>> graph;
	static int[] nums,nodes;
	static long[] min_dis;
	static boolean[] visited;
	static HashMap<Integer,long[]> hm;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		hm = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<ArrayList<Node>>();
		nums = new int[3];
		INF = (1000000L*300000L+1) ; // MAX값으로 초기화면 overflow 발생합니다요 
		min_v = (1000000L*300000L+1);
		
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v,w));
			graph.get(v).add(new Node(u,w));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		P = Integer.parseInt(br.readLine());
		nodes = new int[P];
		visited = new boolean[P];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<P; i++) {
			nodes[i] = Integer.parseInt(st.nextToken());
		}
		 // P만큼 반복문 돌면서 다익스트라 실행해줌 !. 실행한 값을 HashMap에 집어넣기. 
     // 넣는 이유는 이따 최단거리 구할 때 출력하기 위함. 그냥 이차원 배열 써도 됨. 
		hm.put(start, dijkstra(start));
		for (int i=0; i<P; i++) {
			hm.put(nodes[i],dijkstra(nodes[i]));
		}
				
		perm(0);
		System.out.println(min_v == (1000000L*300000L+1) ? -1 : min_v); // 정답 출력
     
	}
	
	static void perm(int cnt) { // 순열로 100P3 구해주기 
		if (cnt >= 3) { 
      
			long tmp = 0;		
			
			tmp += hm.get(start)[nums[0]];
			tmp += hm.get(nums[0])[nums[1]];
			tmp += hm.get(nums[1])[nums[2]];
			tmp += hm.get(nums[2])[end];

			min_v = Math.min(min_v, tmp);
			return;
		}

		
		for (int i=0; i<P; i++) {
			
			if (visited[i]) continue;
			
			nums[cnt] = nodes[i];
			visited[i] = true;
			perm(cnt+1);
			visited[i] = false;
			
		}
	}
	
	static long[] dijkstra(int start) { // 기본적인 다익스트라 
		PriorityQueue<Node> pq = new PriorityQueue<>();
		min_dis = new long[N+1];
		Arrays.fill(min_dis,INF);
		min_dis[start] = 0;
		pq.offer(new Node(start,0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (min_dis[cur.node] < cur.cost) continue;
			
			for (Node next: graph.get(cur.node)) {
				if (min_dis[next.node] > next.cost + cur.cost) {
					min_dis[next.node] = next.cost + cur.cost;
					pq.offer(new Node(next.node, next.cost+cur.cost));
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
			return (int) ((int) this.cost - o.cost);
		}
	}
}
