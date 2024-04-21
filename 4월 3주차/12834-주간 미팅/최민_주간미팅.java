import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// di = (집-KIST의 최단 거리) + (집-씨알푸드의 최단 거리)
	//달할 수 없는 경우의 최단 거리는 -1
	static class Edge implements Comparable<Edge>{
		int node;
		int w;
		public Edge(int node, int w) {
			super();
			this.node = node;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return this.w-o.w;
		}
	}
	static List<List<Edge>> graph = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 팀원 수
        int V = Integer.parseInt(st.nextToken()); // 장소의 수
        int E = Integer.parseInt(st.nextToken()); // 도로의 수

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken()); // KIST의 위치
        int B = Integer.parseInt(st.nextToken()); // 씨알푸드의 위치
        
        int[] homes = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { //팀원들의 집 위치
            homes[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=0; i<=V; i++) { 
        	graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, l));
            graph.get(b).add(new Edge(a, l));
        }
        
        //최단 거리 구하기
        int[] kist = dijkstra(A,V);
        int[] food = dijkstra(B,V);
        
        int INF = Integer.MAX_VALUE;
        int sum=0;
        for(int home : homes) { //모든 사람의 최단 거리 합 구하기
        	 if (kist[home] == INF || food[home] == INF) {
                 sum = -1;
                 break;
             }
        	 sum+=kist[home]+food[home];
        }
        
        System.out.println(sum);
	}
	
	static int[] dijkstra(int start, int V) {
		int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        
        while(!pq.isEmpty()) {
        	Edge e = pq.poll();
        	
        	if(dist[e.node]<e.w) continue;
        	
        	for(Edge n : graph.get(e.node)) {
        		int newDist = dist[e.node]+n.w;
        		 if (newDist < dist[n.node]) {
                     dist[n.node] = newDist;
                     pq.offer(new Edge(n.node, newDist));
                 }
        	}
        }
        return dist;

	}
}
