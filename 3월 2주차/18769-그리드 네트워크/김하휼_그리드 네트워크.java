import java.io.*;
import java.util.*;

// index에러 why ?? 

public class prac { 
	static int R,C;
	static ArrayList<ArrayList<Node>> arr = new ArrayList<ArrayList<Node>>();
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 

		int T = Integer.parseInt(br.readLine());

		for (int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			visited = new boolean[R*C];

			pq = new PriorityQueue<>();
			for (int i=0; i<R*C; i++) {
				arr.add( new ArrayList<>());
			}

			for (int i=0; i<R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<C-1; j++) {
					int c = Integer.parseInt(st.nextToken());
					arr.get(i*C+j+1).add(new Node(i*C+j,c));
					arr.get(i*C+j).add(new Node(i*C+j+1,c));
				}
			}


			for (int i=0; i<R-1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<C; j++) {
					int c = Integer.parseInt(st.nextToken());
					arr.get((i+1)*C+j).add(new Node(i*C+j,c));
					arr.get(i*C+j).add(new Node((i+1)*C+j,c));

				}
			}
			prim();
			
		}
		System.out.println(sb);

	}

	public static void prim() {

		pq.offer(new Node(0,0));
		int ans = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (visited[cur.end]) continue;

			visited[cur.end] = true;
			ans += cur.cost;

			for (Node next: arr.get(cur.end)) {
				if (!visited[next.end]) {
					pq.offer(new Node(next.end,next.cost));
				}
			}
		}
		sb.append(ans).append("\n");
	}
}

class Node implements Comparable<Node>{
	int end,cost;

	public Node(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
	
}
