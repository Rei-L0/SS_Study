import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*

  [CHECT POINT !]
  0. 계속 틀렸던 이유.. 양동이 위치를 1부터로 생각함.

 */
public class B_10025 {

  static int N, K;
  static int[] board = new int[1000001];

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken())*2 + 1;

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int p = Integer.parseInt(st.nextToken());
      board[p] = s;
    }

    int answer = 0, temp = 0;
    for (int i = 0; i <= 1000000; i++) {
      if(i >= K) temp -= board[i-K];
      temp += board[i];
      answer = Math.max(temp, answer);
    }
    System.out.println(answer);
  }

}
