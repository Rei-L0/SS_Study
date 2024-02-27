import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 박연후_연구소 3 {

	static int n, m, count, ans = Integer.MAX_VALUE;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static int[][] board;
	static ArrayList<Pos> virusList;

	static class Pos {
		int x;
		int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	// 활성 바이러스 조합 생성
	static void combi(int start, int count, int[] arr) {
		if (count == m) {
			ans = Math.min(ans, solve(arr));
			return;
		}
		for (int i = start; i < virusList.size(); i++) {
			arr[count] = i;
			combi(i + 1, count + 1, arr);
		}
	}

	// parameter : 활성화될 바이러스의 인덱스
	// 비활성 바이러스는 0으로 계산
	// 생성한 바이러스에서 퍼지는 시간 계산
	static int solve(int[] arr) {
		// 새로운 연구소 생성
		int[][] map = new int[n][n];
		boolean[][] visit = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = -1;
			}
		}

		// 비활성화 바이러스 0으로 표시
		for (int i = 0; i < virusList.size(); i++) {
			Pos virus = virusList.get(i);
			map[virus.x][virus.y] = 0;
		}
		// 활성화된 바이러스 퍼뜨리기
		// 퍼뜨린 칸의 개수 == count일 때 return
		ArrayDeque<Pos> q = new ArrayDeque<>();
		for (int i = 0; i < m; i++) {
			q.add(virusList.get(arr[i]));
			visit[virusList.get(arr[i]).x][virusList.get(arr[i]).y] = true;
		}

		int spreadCount = count;
		int time = 0;
		while (!q.isEmpty()) {
			Pos now = q.poll();
			if (spreadCount == 0)
				return time;
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (isOut(nx, ny))
					continue;
				if (visit[nx][ny] || board[nx][ny] == 1)
					continue;
				q.add(new Pos(nx, ny));
				visit[nx][ny] = true;
				map[nx][ny] = map[now.x][now.y] + 1;
				time = Math.max(time, map[nx][ny]);
				if (board[nx][ny] == 2)
					continue;
				spreadCount--;
			}
		}
		return Integer.MAX_VALUE;
	}

	static boolean isOut(int x, int y) {
		return (x == -1 || y == -1 || x == n || y == n);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		board = new int[n][n];
		virusList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 2) {
					virusList.add(new Pos(i, j));
				}
				if (board[i][j] == 0) {
					count++;
				}
			}
		}

		combi(0, 0, new int[m]);
		System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);
	}
}