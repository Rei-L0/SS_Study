import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2174 {
  static int A, B;
  static int N, M;
  static int[][] map;
  static ArrayList<Robot> robot;
  static boolean isError = false;

  static class Robot {
    int x, y;
    String dir;
    int num;

    public Robot(int x, int y, String dir, int num) {
      this.x = x;
      this.y = y;
      this.dir = dir;
      this.num = num;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    A = Integer.parseInt(st.nextToken());
    B = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[B][A];
    robot = new ArrayList<>();

    for (int i = 0; i < B; i++) {
      Arrays.fill(map[i], -1);
    }

    // 열은 똑같고 행을 Math.abs(B-b)해서 저장
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()) - 1; // 열
      int b = Integer.parseInt(st.nextToken()); // 행
      b = Math.abs(b - B);
      String dir = st.nextToken();
      robot.add(new Robot(b, a, dir, i));
      map[b][a] = i;
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      String order = st.nextToken();
      int time = Integer.parseInt(st.nextToken());
      while (time-- != 0) {
        Robot r = robot.get(n - 1);
        if (order.equals("L")) {
          turnLeft(r);
        } else if (order.equals("R")) {
          turnRight(r);
        } else {
          isError = move(r);
          if (isError)
            break;
        }
      }
      if (isError)
        break;
    }

    if (!isError)
      System.out.println("OK");

  }

  private static boolean move(Robot r) {
    map[r.x][r.y] = -1;
    if (r.dir.equals("E")) {
      r.y++;
      if (r.y >= A) {
        System.out.println("Robot " + (r.num + 1) + " crashes into the wall");
        return true;
      }
    } else if (r.dir.equals("N")) {
      r.x--;
      if (r.x < 0) {
        System.out.println("Robot " + (r.num + 1) + " crashes into the wall");
        return true;
      }

    } else if (r.dir.equals("W")) {
      r.y--;
      if (r.y < 0) {
        System.out.println("Robot " + (r.num + 1) + " crashes into the wall");
        return true;
      }

    } else {
      r.x++;
      if (r.x >= B) {
        System.out.println("Robot " + (r.num + 1) + " crashes into the wall");
        return true;
      }
    }

    if (map[r.x][r.y] != -1) {
      System.out.println("Robot " + (r.num + 1) + " crashes into robot " + (map[r.x][r.y] + 1));
      return true;
    }
    map[r.x][r.y] = r.num;

    return false;
  }

  private static void turnRight(Robot r) {
    if (r.dir.equals("E")) {
      r.dir = "S";
    } else if (r.dir.equals("S")) {
      r.dir = "W";
    } else if (r.dir.equals("W")) {
      r.dir = "N";
    } else {
      r.dir = "E";
    }
  }

  private static void turnLeft(Robot r) {
    if (r.dir.equals("E")) {
      r.dir = "N";
    } else if (r.dir.equals("N")) {
      r.dir = "W";
    } else if (r.dir.equals("W")) {
      r.dir = "S";
    } else {
      r.dir = "E";
    }
  }
}
