import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//  다익스트라
public class Main {

	static int M, N, min;
	static int[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static boolean[][] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		dijk();
		System.out.println(min);
		
	}
	static void dijk() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, 0));
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(node.y == N-1 && node.x == M-1) {
				min = node.cnt;
				return;
			}
			for(int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				if(ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx]) continue;
				visit[ny][nx] = true;
				pq.offer(new Node(ny, nx, node.cnt + map[ny][nx]));
				
			}
		}
	}
	static class Node implements Comparable<Node>{
		int y, x, cnt;

		public Node(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return cnt - o.cnt;
		}
	}
}
