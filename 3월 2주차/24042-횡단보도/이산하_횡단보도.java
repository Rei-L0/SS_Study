import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
  예제 테케 2번 계속 틀림 -> 도예 풀이 봄
 */

public class B_24042 {

  static int N, M;
	static long[] distance;

	static ArrayList<ArrayList<Node>> nodes = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i = 0; i <= N; i++) {
			nodes.add(new ArrayList<>());
		}

		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes.get(a).add(new Node(b, i));
			nodes.get(b).add(new Node(a, i));
		}
		// 1번에서 시작
		distance = new long[N+1];
		Arrays.fill(distance, Long.MAX_VALUE);
		dijk();
		System.out.println(distance[N]);
	}
	static void dijk() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
		distance[1] = 0;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(distance[now.nodeN] < now.cost) continue;
			for(Node next : nodes.get(now.nodeN)) {
				long nxtCost;
				if(now.cost <= next.cost) nxtCost = next.cost;
				else {
					nxtCost = (long)Math.ceil(((double)(now.cost - next.cost) / M)) * M + next.cost;
				}
				if(distance[next.nodeN] > nxtCost) {
					distance[next.nodeN] = nxtCost;
					
					pq.offer(new Node(next.nodeN, nxtCost));
				}
			}
		}
	}
	static class Node implements Comparable<Node>{
		int nodeN;
		long cost;

		public Node(int nodeN, long cost) {
			this.nodeN = nodeN;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(cost, o.cost);
		}
	}
}
