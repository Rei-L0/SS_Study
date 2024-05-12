import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 조합
public class Main {

	static int N;
	static boolean ans;
	static char[][] map;
	static ArrayList<Node> teacher;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		teacher = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j] == 'T') teacher.add(new Node(i, j));
			}
		}
		comb(0);
		System.out.println(ans ? "YES" : "NO");
	}
	static void comb(int depth) {
		if(depth == 3) {
			if(res()) ans = true;
			return;
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 'X') {
					map[i][j] = 'O';
					comb(depth + 1);
					map[i][j] = 'X';
				}
			}
		}
	}
	static boolean res() {
		for(Node t : teacher) {
			for(int d = 0; d < 4; d++) {
				int ny = t.y;
				int nx = t.x;
				while(true) {
					ny = ny + dy[d];
					nx = nx + dx[d];
					if(ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 'O') break;
					if(map[ny][nx] == 'S') {
						return false;
					}
				}
			}
		}
		return true;
	}
	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
