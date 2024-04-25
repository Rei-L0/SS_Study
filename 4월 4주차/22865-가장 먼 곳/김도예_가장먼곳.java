import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 
 * A, B, C 다익스트라 세 번 돌리기
 * 세 개의 다익스트라 값의 min 값 중 max 값을 구함
 */
public class Main {

	static int N, M, a, b, c, INF = Integer.MAX_VALUE;
	static int[][] dist;
	static ArrayList<ArrayList<Node>> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		dist = new int[3][N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			list.get(d).add(new Node(e, l));
			list.get(e).add(new Node(d, l));
		}
		for(int i = 0; i < 3; i++) {
			Arrays.fill(dist[i], INF);
		}
		dijk(a, dist[0]);
		dijk(b, dist[1]);
		dijk(c, dist[2]);

		int max = 0;
		int mi = 0;
		for(int i = 1; i <= N; i++) {
			int min = INF;
			for(int j = 0; j < 3; j++) {
				if(min > dist[j][i]) min = dist[j][i];
			}
			if(max < min) {
				max = min;
				mi = i;
			}
		}
		System.out.println(mi);
	}
	static void dijk(int start, int[] dist) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			for(Node n : list.get(node.e)) {
				if(dist[n.e] > dist[node.e] + n.c) {
					dist[n.e] = dist[node.e] + n.c;
					pq.offer(new Node(n.e, dist[n.e]));
				}
			}
		}
	}
	static class Node implements Comparable<Node>{
		int e, c;

		public Node(int e, int c) {
			this.e = e;
			this.c = c;
		}
		
		@Override
		public int compareTo(Node o) {
			return c - o.c;
		}
	}
}
