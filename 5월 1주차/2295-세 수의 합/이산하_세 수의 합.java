import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 조합 -> 메모리 초과
// 3% 틀림 -> 세 개의 수가 같을 수도 있음.
// A + B + C = D => A + B = D - C
public class B_2295 {
  static int N;
  static int[] numbers;
  static int result = 0;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    numbers = new int[1001];
    Arrays.fill(numbers, Integer.MAX_VALUE);

    Map<Integer, Integer> sum = new HashMap<>();
    for (int i = 0; i < N; i++) {
      numbers[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(numbers);
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        sum.put(numbers[i] + numbers[j], 0);
      }
    }
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (sum.containsKey(numbers[i] - numbers[j]))
          result = Math.max(result, numbers[i]); // A + B + C = D 이므로, D에 해당하는 numbers[i]
      }
    }

    System.out.println(result);
  }

}
