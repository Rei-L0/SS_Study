import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C, N;
	static char[][] map;
	static boolean[][] visit;
	static ArrayList<Node> list, checkNode;
	static int[] height;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		N = Integer.parseInt(br.readLine());
		height = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			csThrow(R - Integer.parseInt(st.nextToken()), i % 2);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	// 막대 던지기
	static void csThrow(int h, int d) {
		if(d == 0) {
			for(int i = 0; i < C; i++) {
				if(map[h][i] == 'x') {
					map[h][i] = '.';
					check(h, i);
					break;
				}
			}
		}
		else {
			for(int i = C-1; i >= 0; i--) {
				if(map[h][i] == 'x') {
					map[h][i] = '.';
					check(h, i);
					break;
				}
			}
		}
	}
	// 미네랄 사라진 후 바닥에서부터 bfs로 visit check 해서 끊긴 부분 확인
	static void check(int y, int x) {
		visit = new boolean[R][C];
		list = new ArrayList<>();
		for(int i = 0; i < C; i++) {
			if(map[R-1][i] == 'x') air(R-1, i);
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(!visit[i][j] && map[i][j] == 'x') {
					list.add(new Node(i, j));
				}
			}
		}
		if(!list.isEmpty()) {
			for(Node n : list) {
				map[n.y][n.x] = '.';
			}
			down();
		}
	}
	// visit check
	static void air(int y, int x){
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(y, x));
		visit[y][x] = true;
		while(!q.isEmpty()) {
			Node node = q.poll();
			for(int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				if(ny < 0 || nx < 0 || ny >= R || nx >= C || visit[ny][nx] || map[ny][nx] == '.') continue;
				visit[ny][nx] = true;
				q.offer(new Node(ny, nx));
			}
		}
	}
	static void down() {
		boolean flag = false;
		while(true) {
			for(Node node : list) {
				node.y = node.y + 1;
				if(node.y + 1 == R || map[node.y + 1][node.x] == 'x') {
					flag = true;
				}
			}
			if(flag) break;
		}
		for(Node node : list) {
			map[node.y][node.x] = 'x';
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

