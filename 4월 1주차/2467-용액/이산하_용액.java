import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2467 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    long[] arr = new long[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Long.parseLong(st.nextToken());
    }

    int left = 0;
    int right = N - 1;
    int resultLeft = 0, resultRight = 0;
    long min = Long.MAX_VALUE;
    while (left < right) {
      long sum = arr[left] + arr[right];
      if (min > Math.abs(sum)) {
        min = Math.abs(sum);
        resultLeft = left;
        resultRight = right;
      }
      if (sum >= 0) {
        right--;
      } else {
        left++;
      }
    }
    System.out.println(arr[resultLeft] + " " + arr[resultRight]);
  }
}
