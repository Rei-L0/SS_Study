import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int n;

	static int[] node;
	static boolean[] visit;

	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<Integer>());
		}

		node = new int[n];
		// 방문 여부 체크
		visit = new boolean[n];

		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			graph.get(s).add(e);
			graph.get(e).add(s);
			node[s]++;
			node[e]++;
		}

		ArrayDeque<Integer> q = new ArrayDeque<>();

		for (int i = 0; i < n; i++) {
			if (node[i] == 1) {
				// 초기 가장자리 정점 저장
				q.add(i);
			}
		}

		// 다음에 가장자리 정점이 2개 이하일 경우 그만 수행한다.
		while (q.size() > 2) {
			// 다음 가장자리 정점들 저장할 Q
			ArrayDeque<Integer> nextQ = new ArrayDeque<>();
			while (!q.isEmpty()) {
				int now = q.poll();
				node[now]--;
				visit[now] = true;
				for (int x : graph.get(now)) {
					if (visit[x])
						continue;
					node[x]--;
					if (node[x] == 1) {
						nextQ.add(x);
					}
				}
			}
			q = nextQ;
		}

		for (int i = 0; i < n; i++) {
			if (!visit[i]) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb);
	}

}