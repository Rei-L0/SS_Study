import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] numbers = new int[N];

    for (int i = 0; i < N; i++) {
      numbers[i] = Integer.parseInt(st.nextToken());
    }

    int twoCnt = 0;
    int result = 0;

    for (int i = 0; i < N; i++) {
      int now = numbers[i];
      int tempCnt = 0;
      while (true) {
        if (now == 0) {
          break;
        } else if (now % 2 == 0) {
          now /= 2;
          tempCnt += 1;  // 2를 나눌 땐, 현재 cnt 값 + 1
        } else if (now % 2 == 1) {
          now -= 1;
          result += 1;  // 1을 감소할 땐, 전체 cnt 값 + 1
        }
      }
      twoCnt = Math.max(twoCnt, tempCnt);
    }
    System.out.println(result + twoCnt);
  }

}
