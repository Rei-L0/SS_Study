import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 
 	[부족했던 부분 CHECK!]
 	0. 런타임 에러(NZEC) : System.exit() 을 사용하고 싶을 땐, 
 	System.exit(0) 을 사용하자 !
 	1. 장애물을 만나기 전까진 같은 방향 이동해야하기 때문에, DFS 파라미터로 방향까지 전달해야함.
 
 */
public class B_13901 {

	static int R, C, obstacleCnt;
	static Pos Robot;
	static boolean[][] visited;
	static int[] dy = { 0, -1, 1, 0, 0 }; // 0 dummy
	static int[] dx = { 0, 0, 0, -1, 1 }; // 0 dummy
	static int[] direction = new int[4];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		visited = new boolean[R][C];

		obstacleCnt = Integer.parseInt(br.readLine());

		for (int i = 0; i < obstacleCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			visited[y][x] = true;
		}

		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		Robot = new Pos(y, x);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			direction[i] = Integer.parseInt(st.nextToken());
		}

		DFS(Robot.y, Robot.x, 0);
	}

	static void DFS(int y, int x, int d) {
		boolean flag = true;
		visited[y][x] = true;

		for (int i = d; i < d + 4; i++) {
			int yy = y + dy[direction[i % 4]];
			int xx = x + dx[direction[i % 4]];
			if (yy < 0 || yy >= R || xx < 0 || xx >= C || visited[yy][xx])
				continue;
			DFS(yy, xx, (i) % 4);
			flag = false;

		}
		if (flag) {
			System.out.println(y + " " + x);
			System.exit(0);
		}
	}

	static class Pos {
		int y;
		int x;

		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
