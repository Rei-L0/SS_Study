import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 절댓값도 가능
public class Main {

  static int N;
  static long[] numbers;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    numbers = new long[N];
    for (int i = 0; i < N; i++) {
      numbers[i] = Long.parseLong(st.nextToken());
    }
    Arrays.sort(numbers);
    int result = 0;
    for (int i = 0; i < N; i++) {
      if (binarySearch(i)) {
        result += 1;
      }
    }
    System.out.println(result);
  }

  static boolean binarySearch(int t) {

    long[] temp_numbers = new long[N - 1];
    for (int i = 0; i < t; i++) {
      temp_numbers[i] = numbers[i];
    }

    for (int i = t; i < N - 1; i++) {
      temp_numbers[i] = numbers[i + 1];
    }

    int left = 0;
    int right = N - 2;
    long target = numbers[t];

    while (left < right) {
      long temp = temp_numbers[left] + temp_numbers[right];
      if (temp == target) {
        return true;
      } else if (temp < target)
        left += 1;
      else
        right -= 1;
    }

    return false;
  }
}
