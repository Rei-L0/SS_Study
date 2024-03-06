import java.io.*;
import java.util.*;

public class Main {

	static int r, c, k, sx, sy;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int[] d;
	static int[][] map;

	// 상, 하, 좌, 우 중 움직일 수 있는 방향이 없을 경우 동작 종료
	static boolean isFinish(int x, int y) {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
				continue;
			}
			if (map[nx][ny] == 0)
				count++;
		}
		if (count != 0)
			return false;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new int[r][c];

		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = -1;
		}

		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		map[sx][sy] = 1;

		d = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			d[i] = Integer.parseInt(st.nextToken());
			d[i] -= 1;
		}

		int nowD = 0;
		while (true) {
			int nx = sx + dx[d[nowD]];
			int ny = sy + dy[d[nowD]];
			// 벽을 만날 경우 동작 종료 여부 확인
			// 종료하지 않을 시 방향 전환
			if (nx < 0 || ny < 0 || nx >= r || ny >= c) {
				if (isFinish(sx, sy)) {
					break;
				}
				nowD = (nowD + 1) % 4;
				continue;
			}
			// 이미 지나간 지역일 경우 동작 종료 여부 확인
			// 종료하지 않을 시 방향 전환
			if (map[nx][ny] != 0) {
				if (isFinish(sx, sy)) {
					break;
				}
				nowD = (nowD + 1) % 4;
				continue;
			}
			// 지나가지 않은 공간일 경우 
			map[nx][ny] = map[sx][sy] + 1;
			// 현재 위치 변경
			sx = nx;
			sy = ny;
		}
		System.out.println(sx + " " + sy);
	}
}