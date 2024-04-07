import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * bfs
 * 한 번에 생기는 여러 연합 계산 후 map 갱신
 * 연합이 생기지 않으면 끝
 */
public class Main {

	static int N, L, R, ans;
	static int[][] map;
	static boolean[][] visit;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static ArrayList<Node> list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(true) {
			visit = new boolean[N][N];
			boolean flag = false;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visit[i][j]) {
						bfs(i, j);
						if(list.size() != 1) {
							flag = true;
						}
						union();
					}
				}
			}
			if(!flag) break;
			ans++;
		}
		System.out.println(ans);
	}
	static void union() {
		int sum = 0;
		for(Node n : list) {
			sum += map[n.y][n.x];

		}
		int a = sum / list.size();
		for(Node n : list) {
			map[n.y][n.x] = a;
		}
	}
	static void bfs(int y, int x) {
		Queue<Node> q = new ArrayDeque<>();
		list = new ArrayList<>();
		q.offer(new Node(y, x));
		visit[y][x] = true;
		while(!q.isEmpty()) {
			Node node = q.poll();
			list.add(node);
			for(int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				if(ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx]) continue;
				int sub = Math.abs(map[node.y][node.x] - map[ny][nx]);
				if(sub < L || sub > R) continue;
				q.offer(new Node(ny, nx));
				visit[ny][nx] = true;
			}
		}
	}
	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
