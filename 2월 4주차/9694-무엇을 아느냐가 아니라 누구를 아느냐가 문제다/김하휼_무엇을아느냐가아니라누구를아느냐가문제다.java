import java.io.*;
import java.util.*;

public class P9694 {
	static int N,M,INF;
	static ArrayList<ArrayList<Node>> graph;
	static int[] min_dis, pre_node;
	static ArrayList<Integer> path;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=t; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<ArrayList<Node>>();
			path = new ArrayList<Integer>();
			INF = Integer.MAX_VALUE;

			for (int i=0; i<M; i++) {
				graph.add(new ArrayList<>());
			}
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				graph.get(x).add(new Node(y,z));
				graph.get(y).add(new Node(x,z));
			}
			
			int[] ans = dijkstra(0);
			
			sb.append("Case #").append(tc).append(":").append(" "); 
			
			if (ans[M-1] == INF) sb.append(-1); // 최고위원까지 도착 못했으면 -1	
			else {
				// now가 최고위원인 이유는 한신이부터 시작하면 자신을 방문한 정점이 없기 때문에 무한반복 돔. 
				path.add(M-1); // path는 한신이가 만난 순서를 저장하기 위한 리스트
				int now = M-1; // 현재는 최고위원
				
				while (now != 0) { // 최고위원 -> 한신이일때까지
					now = pre_node[now]; // 현재 값은 pre_node에 저장된 자신의 인덱스 값. 즉 자신을 방문앴덤 정점. 
					path.add(now); // 그 정점을 path에 추가해줌
				}
				
				Collections.reverse(path); // 뒤에서부터 돌았기 때문에 reverse 해줌
				for (int x: path) {
					sb.append(x).append(" ");
				}
			}
      
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static int[] dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>((o1,o2) -> o1.cost - o2.cost);
		min_dis = new int[M];
		pre_node = new int[M];
		Arrays.fill(min_dis, INF);
		
		min_dis[start] = 0;
		pq.offer(new Node(0,0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (min_dis[cur.node] < cur.cost) continue;
					
			for (Node next: graph.get(cur.node)) {
				if (min_dis[next.node] > cur.cost + next.cost) {
					min_dis[next.node] = cur.cost + next.cost;
					// 최단 경로를 찾아서 갈 때 해당 정점을 방문하기 전 정점을 저장. 
					// ex) 0 -> 2로 가고 2 -> 4로 갈 때, pre_node = [0,0,0,2,2]
					pre_node[next.node] = cur.node; 
					pq.offer(new Node(next.node,cur.cost + next.cost));
				}
			}
		}
		return min_dis;
	}
}

class Node{
	int node;
	int cost;
	public Node(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}
}
