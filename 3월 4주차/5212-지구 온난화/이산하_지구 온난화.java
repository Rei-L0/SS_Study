import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

  static int R, C;
  static String[][] board, new_board;
  static boolean[][] visited;
  static int[] dy = { -1, 0, 1, 0 };
  static int[] dx = { 0, 1, 0, -1 };
  static int minY = 11, minX = 11, maxY = -1, maxX = -1;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    board = new String[R][C];
    new_board = new String[R][C];
    visited = new boolean[R][C];

    for (int i = 0; i < R; i++) {
      String[] input = br.readLine().split("");
      for (int j = 0; j < C; j++) {
        board[i][j] = input[j];
        new_board[i][j] = input[j];
      }
    }
    for (int i = 0; i < R; i++) {
      for (int j = 0; j < C; j++) {
        if (board[i][j].equals("X")) {
          makeSea(i, j);
        }
      }
    }
    for (int i = minY; i < maxY + 1; i++) {
      for (int j = minX; j < maxX + 1; j++) {
        System.out.print(new_board[i][j]);
      }
      System.out.println();
    }
  }

  // 섬 발견 !
  static void makeSea(int sy, int sx) {
    Deque<int[]> queue = new ArrayDeque<>();
    visited[sy][sx] = true;
    queue.offer(new int[] { sy, sx });

    while (!queue.isEmpty()) {
      int[] now = queue.poll();
      int y = now[0];
      int x = now[1];
      int seaCnt = 0;
      for (int i = 0; i < 4; i++) {
        int yy = y + dy[i];
        int xx = x + dx[i];
        if (yy < 0 || xx < 0 || yy >= R || xx >= C) {
          seaCnt += 1;
          continue;
        }

        if (visited[yy][xx])
          continue;
        if (board[yy][xx].equals("."))
          seaCnt += 1;
        else {
          queue.offer(new int[] { yy, xx });
          visited[yy][xx] = true;
        }
      }
      if (seaCnt > 2) {
        new_board[y][x] = ".";
      } else {
        minX = Math.min(minX, x);
        minY = Math.min(minY, y);
        maxX = Math.max(maxX, x);
        maxY = Math.max(maxY, y);
      }
    }
  }
}
