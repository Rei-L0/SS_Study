import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 구현
 * 겹칠 때 삭제하는 게 까다로웠음
 * => 그냥 newmap 씀!
 */
public class Main {

	static int R, C, jy, jx;
	static char[][] map, newmap;
	static boolean over;
	static ArrayList<Node> list = new ArrayList<>();
	static int[] dy = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};  
	static int[] dx = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
	static StringBuilder sb = new StringBuilder();
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
				if(map[i][j] == 'I') {
					jy = i;
					jx = j;
				}
			}
		}
		String str = br.readLine();
		for(int o = 0; o < str.length(); o++) {
			jsmove(jy, jx, str.charAt(o) - '0');
			if(over) {
				System.out.println("kraj " + (o+1));
				System.exit(0);
			}
			newmap = new char[R][C];
			for(int i = 0; i < R; i++) Arrays.fill(newmap[i], '.');
			newmap[jy][jx] = 'I';
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(map[i][j] == 'R') {
						int my = 0, mx = 0;
						int min = Integer.MAX_VALUE;
						for(int d = 1; d <= 9; d++) {
							int ny = i + dy[d];
							int nx = j + dx[d];
							if(ny < 0 || nx < 0 || ny >= R || nx >= C) continue;
							int di = dist(ny, nx);
							if(min > di) {
								min = di;
								my = ny;
								mx = nx;
							}
						}
						move(my, mx);
						if(over) {
							System.out.println("kraj " + (o+1));
							System.exit(0);
						}
					}
				}
			}
			remove();
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					map[i][j] = newmap[i][j];
				}
			}
		}
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	static void remove() {
		for(Node n : list) {
			newmap[n.y][n.x] = '.';
		}
		list.clear();
	}
	static void move(int ny, int nx) {
		if(newmap[ny][nx] == 'I') {
			over = true;
			return;
		}
		if(newmap[ny][nx] == 'R') list.add(new Node(ny, nx));
		newmap[ny][nx] = 'R';
	}
	static void jsmove(int y, int x, int d) {
		if(d == 5) return;
		jy = y + dy[d];
		jx = x + dx[d];
		if(map[jy][jx] == 'R') {
			over = true;
			return;
		}
		map[jy][jx] = 'I';
		map[y][x] = '.';
		
	}
	static int dist(int ny, int nx) {
		return Math.abs(ny - jy) + Math.abs(nx - jx);
	}
	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
