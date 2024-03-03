// 미완성코드 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_17142 {

	static int N, M, virusCnt = 0, zeroCnt = 0;
	static int[][] board;
	static List<int[]> virus;
	static int[] tgt;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N][N];
		virus = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 2) {
					virus.add(new int[] { i, j });
					virusCnt += 1;
				} else if (board[i][j] == 0) {
					zeroCnt += 1;
				}
			}
		}

		tgt = new int[M];
		comb(0, 0);
		System.out.println(answer);

	}

	static void comb(int tgtIdx, int srcIdx) {
		if (tgtIdx == M) {
			bfs();
//			System.exit(0);
			return;
		}
		if (srcIdx == virusCnt) {
			return;
		}
		tgt[tgtIdx] = srcIdx;
		comb(tgtIdx + 1, srcIdx + 1);
		comb(tgtIdx, srcIdx + 1);
	}

	static void bfs() {

		Queue<int[]> queue = new ArrayDeque<>();
		int zC = 0;
		int time = 0;
		int[][] visited = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], -1);
		}
		
//		for (int i = 0; i < M; i++) {
//			int[] Virus = virus.get(i);
//			visited[Virus[0]][Virus[1]] = 0;
//		}
		for (int i = 0; i < M; i++) {
			int[] selectedVirus = virus.get(tgt[i]);
			int ny = selectedVirus[0];
			int nx = selectedVirus[1];

			queue.offer(new int[] { ny, nx, 0 });
			visited[ny][nx] = 0;

		}
		System.out.println("===================================");
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(visited[i]));
		}
		System.out.println();
		while (!queue.isEmpty()) {
			int[] nowVirus = queue.poll();
			int ny = nowVirus[0];
			int nx = nowVirus[1];
			
			if(board[ny][nx] != 2)	time = Math.max(time, visited[ny][nx]);
			for (int i = 0; i < 4; i++) {
				int yy = ny + dy[i];
				int xx = nx + dx[i];

				if (yy < 0 || yy >= N || xx < 0 || xx >= N || board[yy][xx] == 1 || visited[yy][xx] != -1)
					continue;

				if (board[yy][xx] == 2 && zC != zeroCnt) { // 비활성화 바이러스를 만났을 경우
					visited[yy][xx] = visited[ny][nx] +1;
					queue.offer(new int[] { yy, xx });
				} else {
					visited[yy][xx] = visited[ny][nx] + 1;
					zC += 1;
					queue.offer(new int[] { yy, xx });
				}

//				System.out.println(ny+" "+nx +" "+ board[ny][nx]+" "+visited[ny][nx] + " "+cnt);

			}

		}
		if (zC == zeroCnt) {
			System.out.println(time);
			answer = Math.min(answer, time);
		}

		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(visited[i]));
		}
		System.out.println();
		System.out.println("===================================");

	}

}
