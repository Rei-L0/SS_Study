import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
 * 시간마다 퍼지게 할 시 최악의 경우 200 * 200 * 10000 으로 시간 초과
 * Node 클래스에 시간을 기록해가며 bfs
 * 바이러스가 존재하지 않거나(0 이거나)
 * 같은 시간에 퍼진 바이러스가 현재 바이러스 번호보다 높은 바이러스라면 갱신
 */
public class Main {

	static int N, K, S, X, Y;
	static Node[][] map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static ArrayDeque<Node> q = new ArrayDeque<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new Node[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = new Node(0, Integer.parseInt(st.nextToken()), i, j);
				if(map[i][j].k > 0) q.offer(map[i][j]);
			}
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		bfs();
		System.out.println(map[X][Y].k);
	}
	static void bfs() {
		while(!q.isEmpty()) {
			Node node = q.poll();

			if(node.s == S) continue;
			for(int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];

				if(ny > N || nx > N || ny <= 0 || nx <= 0) continue;
				if(map[ny][nx].k == 0 || (map[ny][nx].k > map[node.y][node.x].k && map[ny][nx].s == node.s + 1)) {
					map[ny][nx].k = map[node.y][node.x].k;
					map[ny][nx].s = node.s + 1;
					q.offer(map[ny][nx]);
				}
			}
		}
	}

	static class Node{
		int s, k, x, y;

		public Node(int s, int k, int y, int x) {
			this.s = s;
			this.k = k;
			this.x = x;
			this.y = y;
		}
	}
}
