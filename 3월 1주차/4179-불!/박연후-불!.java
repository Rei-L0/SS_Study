import java.io.*;
import java.util.*;

public class Main {

	static int r, c, sx, sy, fx, fy, ans;

	static final char Wall = '#';
	
	// 불의 위치를 저장할 큐
	static ArrayDeque<Pos> fire = new ArrayDeque<>();

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static char[][] map;

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static boolean solve() {
		// 지훈이의 움직임 저장할 배열
		int[][] board = new int[r][c];
		// 불의 움직임 저장할 배열
		boolean[][] fireBoard = new boolean[r][c];

		// 지훈이의 움직임 저장할 배열
		ArrayDeque<Pos> ji = new ArrayDeque<>();

		// 지훈이의 시작 위치 Queue에 삽입
		ji.add(new Pos(sx, sy));
		board[sx][sy] = 1;

		while (true) {
			// 현재 불이 위치할 수 있는 좌표의 개수
			int fireSize = fire.size();
			// 현재 불이 위치한 갯수만 bfs
			for (int j = 0; j < fireSize; j++) {
				Pos now = fire.poll();
				fireBoard[now.x][now.y] = true;
				for (int i = 0; i < 4; i++) {
					int nx = now.x + dx[i];
					int ny = now.y + dy[i];
					if (nx < 0 || ny < 0 || nx >= r || ny >= c)
						continue;
					// 벽이거나 이미 불이 있을 경우
					if (map[nx][ny] == Wall || fireBoard[nx][ny])
						continue;
					fire.add(new Pos(nx, ny));
					fireBoard[nx][ny] = true;
				}
			}
			// 현재 지훈이가 위치할 수 있는 좌표의 개수
			int moveSize = ji.size();
			// 현재 지훈이가 위치할 수 없으면 탈출 불가
			if (moveSize == 0)
				return false;
			// 현재 지훈이의 좌표의 개수만 bfs
			for (int j = 0; j < moveSize; j++) {
				Pos now = ji.poll();
				// 지훈이가 미로의 가장자리에 도착했을 경우 탈출 가능!
				if (now.x == 0 || now.x == (r - 1) || now.y == 0 || now.y == (c - 1)) {
					ans = board[now.x][now.y];
					return true;
				}
				for (int i = 0; i < 4; i++) {
					int nx = now.x + dx[i];
					int ny = now.y + dy[i];
					if (nx < 0 || ny < 0 || nx >= r || ny >= c)
						continue;
					// 벽이거나 불이 있거나 이미 방문한 경우
					if (map[nx][ny] == Wall || fireBoard[nx][ny] || board[nx][ny] != 0)
						continue;
					ji.add(new Pos(nx, ny));
					board[nx][ny] = board[now.x][now.y] + 1;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new char[r][c];
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = str.charAt(j);
				// 지훈이 위치 저장
				if (map[i][j] == 'J') {
					sx = i;
					sy = j;
				}
				// 불 위치 Queue에 저장
				if (map[i][j] == 'F') {
					fire.add(new Pos(i, j));
				}
			}
		}

		// 지훈이 도착 가능하면 시간 출력
		if (solve())
			System.out.println(ans);
		else
			System.out.println("IMPOSSIBLE");

	}
}
