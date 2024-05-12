import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
// bfs
// 예제 답이 계속 안나옴 
// 이유 1 : (0, 0) ~ (500, 500) 이므로, map과 visit을 [501][501]으로 정의하여 사용해야 함.
// 이유 2 : 위험, 죽음 구역을 입력 받을 때, x1, y1 의 값이 x2, y2 보다 큰 경우도 있음.  

public class B_1584 {

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] map = new int[501][501];

		int dangerCnt = Integer.parseInt(br.readLine());
		int x1, x2, y1, y2;
		for (int i = 0; i < dangerCnt; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());

			if (x1 > x2) {
				int tempX = x1;
				x1 = x2;
				x2 = tempX;
			}
			if (y1 > y2) {
				int tempY = y1;
				y1 = y2;
				y2 = tempY;
			}
			for (int y = y1; y <= y2; y++) {
				for (int x = x1; x <= x2; x++) {
					map[y][x] = 1;
				}
			}
		}

		int deathCnt = Integer.parseInt(br.readLine());
		for (int i = 0; i < deathCnt; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			if (x1 > x2) {
				int tempX = x1;
				x1 = x2;
				x2 = tempX;
			}
			if (y1 > y2) {
				int tempY = y1;
				y1 = y2;
				y2 = tempY;
			}
			for (int y = y1; y <= y2; y++) {
				for (int x = x1; x <= x2; x++) {
					map[y][x] = 2;
				}
			}
		}
		map[0][0] = 0;
		// bfs
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { 0, 0, 0 });
		int[][] visit = new int[501][501]; // 잃은 생명에 대한 cnt를 저장
		for (int i = 0; i <= 500; i++) {
			Arrays.fill(visit[i], Integer.MAX_VALUE);
		}
		visit[0][0] = 0;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int cy = current[0];
			int cx = current[1];
			for (int i = 0; i < 4; i++) {
				int yy = cy + dy[i];
				int xx = cx + dx[i];

				if (yy < 0 || xx < 0 || yy > 500 || xx > 500)
					continue;

				if (map[yy][xx] == 2) { // 죽음 구역
					continue;
				}
				if (visit[yy][xx] == Integer.MAX_VALUE) {
					if (map[yy][xx] == 1) {
						visit[yy][xx] = visit[cy][cx] - 1;
						queue.offer(new int[] { yy, xx, visit[yy][xx] });
					} else {
						visit[yy][xx] = visit[cy][cx];
						queue.offer(new int[] { yy, xx, visit[yy][xx] });
					}
				}else {
					if (map[yy][xx] == 1 && visit[yy][xx] < visit[cy][cx] - 1) {
						visit[yy][xx] = visit[cy][cx] - 1;
						queue.offer(new int[] { yy, xx, visit[yy][xx] });
					}
					else if (map[yy][xx] == 0 && visit[yy][xx] < visit[cy][cx]) {
						visit[yy][xx] = visit[cy][cx];
						queue.offer(new int[] { yy, xx, visit[yy][xx] });
					}
				}
		
			}
		}
		if(visit[500][500] == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
		System.out.println(visit[500][500]*-1);}
	}
}
