import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// bfs
public class Main {

	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static Queue<Node> q = new ArrayDeque<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		for(int i = 0; i < M; i++) {
			if(map[0][i] == 0) q.offer(new Node(0, i));
		}
		System.out.println(bfs() ? "YES" : "NO");
	}
	static boolean bfs() {
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(node.y == N-1) {
				return true;
			}
			for(int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				if(ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || map[ny][nx] == 1) continue;
				visit[ny][nx] = true;
				q.offer(new Node(ny, nx));
			}
		}
		return false;
	}
	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
