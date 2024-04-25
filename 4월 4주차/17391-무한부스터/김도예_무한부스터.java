import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 다익스트라~bfs 
 * 오른쪽, 아래쪽으로만 가야 함
 * 문제 똑바로 읽기...
 */

public class Main {

	static int N, M, ans, INF = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] cnt;
	static int[] dy = {1, 0};
	static int[] dx = {0, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cnt = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(cnt[i], INF);
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs();
		System.out.println(cnt[N-1][M-1]);
	}
	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(0, 0));
		cnt[0][0] = 0;
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int d = 0; d < 2; d++) {
				int ny = node.y;
				int nx = node.x;
				for(int i = 0; i < map[node.y][node.x]; i++) {
					ny = ny + dy[d];
					nx = nx + dx[d];
					if(ny < 0 || nx < 0 || ny >= N || nx >= M) break;
					if(cnt[ny][nx] > cnt[node.y][node.x] + 1) {
						cnt[ny][nx] = cnt[node.y][node.x] + 1;
						q.offer(new Node(ny, nx));	
					}
				}
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
