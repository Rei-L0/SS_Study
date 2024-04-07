import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// (틀림 51%) 출발지에서 목적지로 갈 수 없는 경우에 대한 언급은 없지만
// 그럴 경우가 있으므로 그럴 땐 0을 출력해줘야 함.
public class B_13905 {

	static int houseCnt, bridgeCnt, startPoint, endPoint;
	static List<List<Node>> houseList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		houseCnt = Integer.parseInt(st.nextToken());
		bridgeCnt = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		startPoint = Integer.parseInt(st.nextToken());
		endPoint = Integer.parseInt(st.nextToken());

		houseList = new ArrayList<>();
		for (int i = 0; i < houseCnt + 1; i++) {
			houseList.add(new ArrayList<>()); // 0 dummy
		}

		for (int i = 0; i < bridgeCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			houseList.get(s).add(new Node(e, c));
			houseList.get(e).add(new Node(s, c));
		}

		System.out.println(dijkstra());
	}

	static int dijkstra() {
		boolean[] visit = new boolean[houseCnt + 1]; // 0 dummy

		Queue<Node> pqueue = new PriorityQueue<>((n1, n2) -> n2.c - n1.c);

		pqueue.offer(new Node(startPoint, 0));

		int result = Integer.MAX_VALUE;
		while (!pqueue.isEmpty()) {
			Node current = pqueue.poll();
			if (visit[current.e])
				continue;
			visit[current.e] = true;

			if (current.c != 0)
				result = Math.min(result, current.c);
			if (current.e == endPoint)
				return result;
			for (Node next : houseList.get(current.e)) {
				if (visit[next.e])
					continue;
				pqueue.offer(next);
			}
		}
		return 0;
	}

	static class Node {
		int e;
		int c;

		public Node(int e, int c) {
			super();
			this.e = e;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Node [e=" + e + ", c=" + c + "]";
		}

	}
}
