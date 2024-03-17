import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 다익스트라
 몇 번째 주기에 갈 수 있는지 반복문 돌려서 확인하면 시간 초과 남
-> 이 부분 해결이 잘 안 돼서 다른 풀이 보고 풀었음
*/

public class Main {

	static int N, M;
	static long[] cost;
	static boolean[] visit;
	static ArrayList<ArrayList<Node>> list = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		visit = new boolean[N+1];
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(new Node(b, i));
			list.get(b).add(new Node(a, i));
		}
		// 1번에서 시작
		cost = new long[N+1];
		Arrays.fill(cost, Long.MAX_VALUE);
		dijk(1);
		System.out.println(cost[N]);
	}
	static void dijk(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		cost[start] = 0;
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			if(cost[n.end] < n.cost) continue;
			for(Node node : list.get(n.end)) {
				long nxtCost;
				if(n.cost <= node.cost) nxtCost = node.cost;
				else {
					nxtCost = (long)Math.ceil(((double)(n.cost - node.cost) / M)) * M + node.cost;
				}
				if(cost[node.end] > nxtCost) {
					cost[node.end] = nxtCost;
					
					pq.offer(new Node(node.end, nxtCost));
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
			return Long.compare(cost, o.cost);
		}
	}
}
