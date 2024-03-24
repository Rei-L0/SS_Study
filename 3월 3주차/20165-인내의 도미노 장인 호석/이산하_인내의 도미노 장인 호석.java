import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int N, M, R; // N(행) M(열) R(라운드 수)
  static int[][] board; // 도미노의 높이를 저장
  static String[][] state; // 도미노의 상태를 저장 (F- 넘어짐/ S- 안넘어짐)
  static HashMap<String, int[]> direction = new HashMap<>();
  static int result = 0;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());

    board = new int[N + 1][M + 1]; // 0 dummy
    state = new String[N + 1][M + 1]; // 0 dummy

    for (int i = 1; i < N + 1; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j < M + 1; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());
      }
      Arrays.fill(state[i], "S");
    }

    direction.put("E", new int[] { 0, 1 }); // 동
    direction.put("W", new int[] { 0, -1 }); // 서
    direction.put("S", new int[] { 1, 0 }); // 남
    direction.put("N", new int[] { -1, 0 }); // 북

    while (R-- > 0) {
      st = new StringTokenizer(br.readLine());
      // Attack
      int AY = Integer.parseInt(st.nextToken());
      int AX = Integer.parseInt(st.nextToken());
      String AD = st.nextToken();
      attack(AY, AX, AD);
      st = new StringTokenizer(br.readLine());
      // Defense
      int DY = Integer.parseInt(st.nextToken());
      int DX = Integer.parseInt(st.nextToken());
      defense(DY, DX);

    }
    System.out.println(result);
    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < M + 1; j++) {
        System.out.print(state[i][j] + " ");
      }
      System.out.println();
    }

  }

  static void attack(int sy, int sx, String d) {
    Queue<int[]> queue = new ArrayDeque<>();

    queue.offer(new int[] { sy, sx });
    while (!queue.isEmpty()) {
      int[] now = queue.poll();
      if (state[now[0]][now[1]].equals("F"))
        continue;

      state[now[0]][now[1]] = "F";
      result += 1;
      int size = board[now[0]][now[1]];
      int dy = direction.get(d)[0];
      int dx = direction.get(d)[1];
      int yy = now[0];
      int xx = now[1];
      for (int i = 0; i < size - 1; i++) {
        yy += dy;
        xx += dx;
        if (yy <= 0 || yy > N || xx <= 0 || xx > M)
          break;
        if (state[yy][xx].equals("F"))
          continue;
        queue.offer(new int[] { yy, xx });
      }

    }
    ;
  }

  static void defense(int y, int x) {
    state[y][x] = "S";
  }
}
