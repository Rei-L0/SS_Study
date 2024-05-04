import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 자꾸 예제 답이 안나옴 (더 값이 적게 나옴) - bfs가 틀린 줄..
// 알고보니 에라토스테네스의 체 계산을 잘못함. 2~9의 배수만 볼 것이 아닌, 2~10000까지의 배수로 봤어야 했음
// 실제 소수 수보다 많은 수를 소수로 판단하여 결과 값이 적게 나옴.
public class B_1963 {

  static int N;
  static boolean[] check;
  static boolean[] visit;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    N = Integer.parseInt(br.readLine());

    check = new boolean[10000];

    for (int i = 2; i < 10000; i++) {
      if (check[i])
        continue;
      for (int j = i * 2; j < 10000; j += i) {
        check[j] = true;
      }
    }

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());

      int result = bfs(start, end);
      if (result == -1) {
        System.out.println("Impossible");
      } else {
        System.out.println(result);

      }
    }

  }

  static int bfs(int start, int end) {

    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[] { start, 0 });
    visit = new boolean[10000];
    visit[start] = true;

    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int cn = current[0];
      int cnt = current[1];
      if (cn == end) {
        return cnt;
      }

      for (int i = 0; i < 4; i++) {
        StringBuilder sb = new StringBuilder(cn + "");
        sb.setCharAt(i, '0');
        int nn = Integer.parseInt(sb.toString());
        // System.out.println(nn);
        for (int j = 0; j <= 9; j++) {
          if (i == 0 && j == 0)
            continue;
          int nnn = nn + ((int) Math.pow(10, 3 - i)) * j;
          if (!visit[nnn] && !check[nnn]) {
            queue.add(new int[] { nnn, cnt + 1 });
            visit[nnn] = true;
          }
          // System.out.print((int) Math.pow(10, 3 - i) + " " + nnn + " ");
        }
        // System.out.println();
      }

    }
    return -1;
  }
}
