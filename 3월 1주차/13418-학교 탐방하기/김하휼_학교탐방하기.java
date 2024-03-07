import java.util.*;
import java.io.*;

// 시작할 때 0번째 지점 방문 체크하고 prim 돌아야 함. 안그럼 한바퀴 그대로 돔 

public class test {
	static int N,M;
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
	static boolean[] visited;
	static int[] first;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		first = new int[2];
		
		for (int i=0; i<M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (i ==0) {
				first[0] = y;
				first[1] = c;
			}
			graph.get(x).add(new Edge(y,c));
			graph.get(y).add(new Edge(x,c));
		}
		
		
		visited = new boolean[N+1];
		int res = prim1();
		
		visited = new boolean[N+1];
		int tmp = prim2();

		System.out.println((res*res) - (tmp*tmp));
		
	}
	public static int prim1() { // 피로도 최악을 구함 
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
		pq.offer(new Edge(first[0],first[1]));
		visited[0] = true;
		int cnt = 0;
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (visited[cur.end]) continue;
			
			visited[cur.end]= true;
			if (cur.cost == 0) {
				cnt++;
			}
			
			for (Edge next: graph.get(cur.end)) {
				if (!visited[next.end]) {
					pq.offer(new Edge(next.end,next.cost));
				}
			}
		}
		return cnt;
	}
	
	public static int prim2() { // 피로도 최적을 구함 
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2) -> o2.cost - o1.cost);
		pq.offer(new Edge(first[0],first[1]));
		visited[0] = true;
		int cnt = 0;
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (visited[cur.end]) continue;
			
			visited[cur.end]= true;
			if (cur.cost == 0) {
				cnt++;
			}
			
			for (Edge next: graph.get(cur.end)) {
				if (!visited[next.end]) {
					pq.offer(new Edge(next.end,next.cost));
				}
			}
		}
		return cnt;
	}
}

class Edge { // 오름차순
	int end,cost;
	
	public Edge(int end,int cost) {
		this.end = end;
		this.cost = cost;
	}
  
}
