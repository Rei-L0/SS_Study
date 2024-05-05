import java.util.*;
import java.io.*;

class prac {
	static boolean[] prime,visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		prime = new boolean[10001];
		Arrays.fill(prime,true); // true면 소수

		prime[0] = prime[1] = false;

		for (int i=2; i<= Math.sqrt(10000); i++) {
			if (prime[i]) {
				for (int j=i*i; j<=10000; j+=i) {
					prime[j] = false;
				}
			}
		}

		int t = Integer.parseInt(br.readLine());

		for (int tc=0; tc<t; tc++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			bfs(a,b);
			
			int ans = bfs(a,b);
			System.out.println(ans == -1 ? "Impossible" : ans);
		}
	}

	static int bfs(int start, int end) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(start,0));
		visited = new boolean[10000];
		visited[start] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.num == end) {
				return cur.cnt;
			}

			for (int i=0; i<4; i++) {
				for (int j=0; j<10; j++) {
					if (i == 0 && j == 0) continue;

					int tmp = changeNumber(cur.num, i, j);

					if (visited[tmp]) continue;
					if (!prime[tmp]) continue;

					q.offer(new Node(tmp,cur.cnt+1));
					visited[tmp] = true;
				}
			}
		}
		return -1;
	}

	static int changeNumber(int num, int i, int j) {
		StringBuilder sb = new StringBuilder(String.valueOf(num));
		sb.setCharAt(i, (char) (j+'0'));
		return Integer.parseInt(sb.toString());
	}

	static class Node{
		int num,cnt;
		public Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
}
