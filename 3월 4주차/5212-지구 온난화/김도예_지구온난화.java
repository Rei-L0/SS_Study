import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

/*
 * 섬의 좌표를 저장하는 리스트 island
 * 상하좌우를 둘러보고 범위를 벗어나거나 .인 부분이 3개 이상이면 삭제
 * map으로 비교하고 newmap에 갱신, island 리스트도 갱신
 * 지도의 크기도 줄어야 하기 때문에 island 리스트를 이용해 지도의 끝점들을 구함
 * => miny, minx, maxy, maxx
 */
public class Main {

	static int N, M;
	static char[][] map, newmap;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static ArrayList<Node> island = new ArrayList<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		newmap = new char[N][M];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'X') island.add(new Node(i, j));
			}
			Arrays.fill(newmap[i], '.');
		}
		
		for(int i = island.size() - 1; i >= 0; i--) {
			check(island.get(i));
		}

		int miny = 10, minx = 10, maxy = 0, maxx = 0;
		for(int i = island.size() - 1; i >= 0; i--) {
			int iy = island.get(i).y, ix = island.get(i).x;
			newmap[iy][ix] = 'X';
			miny = Math.min(miny, iy);
			minx = Math.min(minx, ix);
			maxy = Math.max(maxy, iy);
			maxx = Math.max(maxx, ix);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = miny; i <= maxy; i++) {
			for(int j = minx; j <= maxx; j++) {
				
				sb.append(newmap[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	static void check(Node n) {
		int cnt = 0;
		for(int d = 0; d < 4; d++) {
			int ny = n.y + dy[d];
			int nx = n.x + dx[d];
			if(ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == '.') cnt++;
		}
		if(cnt >= 3) {
			island.remove(n);
		}
	}
	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			return x == other.x && y == other.y;
		}

		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + "]";
		}
	}
}
