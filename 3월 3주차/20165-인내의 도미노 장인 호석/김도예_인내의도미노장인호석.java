import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 도미노를 다시 세울 때 원래 높이대로 올려줘야 함
 * -> 원래 높이를 가지고 있는 map, 갱신되는 newmap 생성
 * 높이만큼 쓰러지는 것을 반복문으로 돌리는데 넘어지는 도미노의 높이에 따라 반복 횟수를 늘려줌
 */
public class Main {

	static int N, M, R, cnt;
	static int[][] map, newmap;
	static char[] dc = {'N', 'S', 'W', 'E'};
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		newmap = new int[N+1][M+1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				newmap[i][j] = map[i][j];
			}
		}
		for(int r = 1; r <= R; r++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			char ad = st.nextToken().charAt(0);
			for(int d = 0; d < 4; d++) {
				if(dc[d] == ad) {
					attack(y, x, d);
					break;
				}
			}
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			if(newmap[y][x] == 0) newmap[y][x] = map[y][x];
		}
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				sb.append(newmap[i][j] == 0 ? "F" : "S").append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	static void attack(int y, int x, int d) {
		int n = newmap[y][x];
		for(int i = 0; i < n; i++) {
			if(newmap[y][x] >= 1) {
				n = Math.max(n, newmap[y][x] + i);
				zero(y, x);
			}
			y = y + dy[d];
			x = x + dx[d];
			if(y > N || x > M || y <= 0 || x <= 0) return;
		}
	}
	static void zero(int y, int x) {
		newmap[y][x] = 0;
		cnt++;
	}

}
