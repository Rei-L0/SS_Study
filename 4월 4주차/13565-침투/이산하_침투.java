import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_13565 {
  static int dy[] = { 0, -1, 0, 1 };
  static int dx[] = { -1, 0, 1, 0 };

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] map = new int[N][M];
    boolean[][] visit = new boolean[N][M];
    Queue<int[]> queue = new ArrayDeque<>();

    for (int i = 0; i < N; i++) {
      String[] input = br.readLine().split("");
      for (int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(input[j]);
        if (i == 0 && map[i][j] == 0) {
          queue.offer(new int[] { i, j });
          visit[i][j] = true;
        }
      }

    }

    String result = "NO";
    while (!queue.isEmpty()) {
      int[] now = queue.poll();
      int ny = now[0];
      int nx = now[1];
      if (ny == N - 1) {
        result = "YES";
        break;
      }
      for (int i = 0; i < 4; i++) {
        int yy = ny + dy[i];
        int xx = nx + dx[i];
        if (yy < 0 || yy > N - 1 || xx < 0 || xx > M - 1)
          continue;
        if (visit[yy][xx])
          continue;
        if (map[yy][xx] == 0) {
          queue.offer(new int[] { yy, xx });
          visit[yy][xx] = true;
        }
      }
    }
    System.out.println(result);
  }
}
