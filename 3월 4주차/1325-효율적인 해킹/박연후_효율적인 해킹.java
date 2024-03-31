import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int n, m, maxVal;

	static int[] cnt;

	static List<List<Integer>> graph = new ArrayList<>();

	static int[] ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		cnt = new int[n + 1];
		ans = new int[n + 1];
		for (int i = 0; i < n + 1; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int e = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			graph.get(e).add(s);
		}

		for (int i = 1; i < n + 1; i++) {
			bfs(i);
		}

		for (int i = 1; i < n + 1; i++) {
			if (maxVal < ans[i])
				maxVal = ans[i];
		}

		for (int i = 1; i < n + 1; i++) {
			if (maxVal == ans[i])
				sb.append(i).append(" ");
		}

		System.out.println(sb);

	}

	static void bfs(int x) {
		boolean[] visit = new boolean[n + 1];
		visit[x] = true;
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(x);

		while (!q.isEmpty()) {
			int now = q.poll();
			for (int next : graph.get(now)) {
				if (visit[next])
					continue;
				q.add(next);
				visit[next] = true;
				ans[next]++;
			}
		}
	}

}