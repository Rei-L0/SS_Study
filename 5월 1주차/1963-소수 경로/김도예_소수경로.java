import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * bfs + 소수 판정
 */
public class Main {
	static char[] n1, n2;
	static int ans;
	static boolean[] select;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n1 = st.nextToken().toCharArray();
			n2 = st.nextToken().toCharArray();
			select = new boolean[10000];
			ans = Integer.MAX_VALUE;
			bfs();
			sb.append(ans == Integer.MAX_VALUE ? "Impossible" : ans).append("\n");
		}
		System.out.println(sb);
	}
	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		int f = Integer.valueOf(String.valueOf(n1));
		q.offer(new Node(n1, 0));
		select[f] = true;
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(equals(node.num, n2)) {
				ans = node.cnt;
				return;
			}
			for(int i = 0; i < 4; i++) {
				char[] nn = new char[4];
				for(int d = 0; d < 4; d++) {
					nn[d] = node.num[d];
				}
				for(int j = 0; j <= 9; j++) {
					if(j == n1[i] - '0' || (i == 0 && j == 0)) continue;
					nn[i] = (char)(j + '0');
					int now = Integer.valueOf(String.valueOf(nn));
					if(!equals(nn, node.num) && !select[now] && isPrime(now)){
						select[now] = true;
						q.offer(new Node(nn, node.cnt+1));
					}
				}
			}
		}
	}
	
	static class Node{
		char[] num;
		int cnt;

		public Node(char[] num, int cnt) {
			this.num = new char[4];
			for(int d = 0; d < 4; d++) {
				this.num[d] = num[d];
			}
			this.cnt = cnt;
		}
	}
	
	static boolean equals(char[] n1, char[] n2) {
		for(int i = 0; i < 4; i++) {
			if(n2[i] != n1[i]) return false;
		}
		return true;
	}
	
	static boolean isPrime(int n) {
		if(n == 1) return false;
		for(int i = 2; i < Math.sqrt(n)+1; i++) {
			if(n % i == 0) return false;
		}
		return true;
	}
}

