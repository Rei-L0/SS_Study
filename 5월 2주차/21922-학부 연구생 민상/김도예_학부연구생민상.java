import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N, M, ans;
	static int[][] map;
	static boolean[][] visit;
	static ArrayList<Node> list = new ArrayList<>();
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1}; // 상우하좌 
	static int[] change3 = {1, 0, 3, 2};
	static int[] change4 = {3, 2, 1, 0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			st =  new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) list.add(new Node(i, j));
			}
		}
		for(Node n : list) {
			wind(n.y, n.x, 0);
			wind(n.y, n.x, 1);
			wind(n.y, n.x, 2);
			wind(n.y, n.x, 3);
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visit[i][j]) ans++;
			}
		}
		System.out.println(ans);
	}
	static void wind(int y, int x, int d) {
		int ny = y;
		int nx = x;
    
		while(true) {
			visit[ny][nx] = true;

			ny = ny + dy[d];
			nx = nx + dx[d];
		
			if(ny < 0 || nx < 0 || ny >= N || nx >= M) break;
			if(map[ny][nx] == 0) map[ny][nx] = 5;
			else if(map[ny][nx] == 1) {
				if(d == 1 || d == 3) {
					visit[ny][nx] = true;
					break;
				}
			}
			else if(map[ny][nx] == 2) {
				if(d == 0 || d == 2) {
					visit[ny][nx] = true;
					break;
				}
			}
			else if(map[ny][nx] == 3) d = change3[d];
			else if(map[ny][nx] == 4) d = change4[d];
			else if(map[ny][nx] == 9) break;

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
