import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n, e, v1, v2;
	static ArrayList<Node>[] list;
	static int[][] weights; // weights[1][v1] : 1에서 v1까지의 최단 경로
	static boolean[] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		list = new ArrayList[n + 1];
		weights = new int[n + 1][n + 1];
		for(int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[start].add(new Node(end, weight));
			list[end].add(new Node(start, weight));
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

    // 시작점으로 사용할 것들만 MAX_VALUE 로 채움
		for(int i = 0; i < n + 1; i++) {
			if(i == 1 || i == v1 || i == v2) Arrays.fill(weights[i], Integer.MAX_VALUE);			
		}
    // 경우의 수
		// case1: 1 -> v1 -> v2 -> n
		// case2: 1 -> v2 -> v1 -> n
    
		dijk(1);
		dijk(v1);
		dijk(v2);
		
		int case1 = 0;
		int case2 = 0;
		
		int[] answer = new int[6]; // 짝수 인덱스는 case1, 나머지는 case2

		answer[0] = weights[1][v1];
		answer[1] = weights[1][v2];
		answer[2] = weights[v1][v2];
		answer[3] = weights[v1][v2];
		answer[4] = weights[v2][n];
		answer[5] = weights[v1][n];
		
		for(int i = 0; i < 6; i++) {
			if(i % 2 == 0) case1 += answer[i];
			else case2 += answer[i];
		}
		for(int i = 0; i < 6; i++) {
			if(answer[i] == Integer.MAX_VALUE) {
				if(i % 2 == 0) case1 = Integer.MAX_VALUE;
				else case2 = Integer.MAX_VALUE;
			}
		}
		int ans = Math.min(case1, case2);
		if(case1 == Integer.MAX_VALUE && case2 == Integer.MAX_VALUE) ans = -1;

		System.out.println(ans);
	}
	static void dijk(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		weights[start][start] = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int nx = node.end;
			for(Node n : list[node.end]) {
				if(weights[start][n.end] > weights[start][nx] + n.weight) {
					weights[start][n.end] = weights[start][nx] + n.weight;
					pq.add(new Node(n.end, weights[start][n.end]));
				}
			}
		}
	}
	static class Node implements Comparable<Node>{
		int end, weight;

		public Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
		
	}
}
