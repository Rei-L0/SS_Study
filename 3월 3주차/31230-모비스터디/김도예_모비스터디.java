import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 다익스트라
 * 처음에 가중치 갱신 조건을 작거나 같을 때로 두고 어쩌고 풀었는데 바로 시간 초과
 * -> 여기서 막혀서 그냥 코드 봄!
 * pq를 static으로 선언하지 않고 다익스트라 시행할 때마다 새로 만들면 시간 초과
 * 다익스트라 중간에 아래 조건 안 넣으면 시간 초과
 * if(dist[n.end] < n.cost) continue;
 */

public class Main {

	static int N, M, A, B;
	static long min;
	static ArrayList<Integer> ans = new ArrayList<>();
	static ArrayList<ArrayList<Node>> list = new ArrayList<>();
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

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
		long[] dist1 = new long[N+1];
		long[] dist2 = new long[N+1];
		
		Arrays.fill(dist1, Long.MAX_VALUE);
		Arrays.fill(dist2, Long.MAX_VALUE);
		dijk(A, dist1);
		dijk(B, dist2);
		
		for(int i = 1; i <= N; i++) {
			if(dist1[B] == dist1[i] + dist2[i]) {
				ans.add(i);
			}
		}
		sb.append(ans.size()).append("\n");
		for(int i = 0; i < ans.size(); i++) {
			sb.append(ans.get(i)).append(" ");
		}
		System.out.println(sb);
	}
	static void dijk(int start, long[] dist) {
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			if(dist[n.end] < n.cost) continue;
			for(Node node : list.get(n.end)) {
				if(dist[node.end] > dist[n.end] + node.cost) {
					dist[node.end] = dist[n.end] + node.cost;
					pq.offer(new Node(node.end, dist[node.end]));
				}
			}
		}
	}
	static class Node implements Comparable<Node>{
		int end;
		long cost;

		public Node(int end, long cost) {
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
	}
}
