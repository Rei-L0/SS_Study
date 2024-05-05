import java.util.*;
import java.io.*;

/*
계속 안되는 부분이 있어서, 아예 새로 하고 산하님 코드 보고 공부해서 풀었습니다 ! 
*/

class prac {
	static int R, C;
	static String[][] cave;
	static boolean[][] visit;
  
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { -1, 0, 1, 0 };
  
	public static void main(String[] args) throws Exception {
	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  
	  StringTokenizer st = new StringTokenizer(br.readLine());
	  R = Integer.parseInt(st.nextToken());
	  C = Integer.parseInt(st.nextToken());
  
	  cave = new String[R][C];
  
	  for (int i = 0; i < R; i++) {
		String[] input = br.readLine().split("");
		for (int j = 0; j < C; j++) {
		  cave[i][j] = input[j];
		}
	  }
  
	  int N = Integer.parseInt(br.readLine());
	  st = new StringTokenizer(br.readLine());
	  for (int i = 0; i < N; i++) {
		int h = Integer.parseInt(st.nextToken());
  
		if (i % 2 == 0) {
		  breakCluster(h, 1);
		} else {
		  breakCluster(h, -1);
		}
  
		dropCluster(h);
  
	  }
  
	  StringBuilder sb = new StringBuilder();
	  for (int i = 0; i < R; i++) {
		for (int j = 0; j < C; j++) {
		  sb.append(cave[i][j]);
		}
		sb.append("\n");
	  }
	  System.out.println(sb);
	}
  
	static void breakCluster(int h, int direction) {
	  int height = R - h;
  
	  if (direction == 1) {
		for (int i = 0; i < C; i += direction) {
		  if (cave[height][i].equals("."))
			continue;
		  cave[height][i] = ".";
		  return;
		}
	  } else {
		for (int i = C - 1; i >= 0; i += direction) {
		  if (cave[height][i].equals("."))
			continue;
		  cave[height][i] = ".";
		  return;
		}
	  }
  
	}
  
	static void dropCluster(int h) {
	  int height = R - h;
	  visit = new boolean[R][C];
  
	  for (int i = 0; i < C; i++) {
		if (cave[R - 1][i].equals("x") && !visit[R - 1][i]) {
		  visit[R - 1][i] = true;
		  findCluster(R - 1, i);
		}
	  }
  
	  int[] temp = new int[C];
	  int moveCnt = Integer.MAX_VALUE;
  
	  for (int x = 0; x < C; x++) {
		int tempCnt = 0;
		for (int y = R - 1; y >= 0; y--) {
		  if (cave[y][x].equals("."))
			tempCnt += 1;
		  else if (cave[y][x].equals("x")) {
			if (visit[y][x]) {
			  tempCnt = 0;
			} else if (!visit[y][x]) {
			  moveCnt = Math.min(moveCnt, tempCnt);
			}
		  }
  
		}
	  }
  
	  for (int y = R - 1; y >= 0; y--) {
		for (int x = 0; x < C; x++) {
		  if (cave[y][x].equals("x") && !visit[y][x])
			moveCluster(y, x, moveCnt);
		}
	  }
	}
  
	static void moveCluster(int sy, int sx, int moveCnt) {
  
	  cave[sy][sx] = ".";
	  cave[sy + moveCnt][sx] = "x";
  
	}
  
	static void findCluster(int sy, int sx) {
	  Queue<int[]> queue = new ArrayDeque<>();
	  queue.offer(new int[] { sy, sx });
  
	  while (!queue.isEmpty()) {
		int[] current = queue.poll();
		int cy = current[0];
		int cx = current[1];
  
		for (int i = 0; i < 4; i++) {
		  int yy = cy + dy[i];
		  int xx = cx + dx[i];
		  if (yy < 0 || xx < 0 || yy > R - 1 || xx > C - 1 || visit[yy][xx])
			continue;
  
		  if (cave[yy][xx].equals("x")) {
			visit[yy][xx] = true;
  
			queue.offer(new int[] { yy, xx });
		  }
  
		}
	  }
	}
}
