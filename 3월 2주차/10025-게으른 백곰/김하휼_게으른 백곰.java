import java.io.*;
import java.util.*;

public class Main {

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;

      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());

      int[] arr = new int[1000001];

      for (int i = 0; i < N; i++) {
         st = new StringTokenizer(br.readLine());
         int g = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         arr[x] = g;
      }
      int idx = 2 * K + 1;
      long ans = -2147000000;
      long tmp = 0;

      for (int i = 0; i <= 1000000; i++) {
         if (i - idx >= 0) {
            tmp -= arr[i - idx];
            tmp += arr[i];
         } else {
            tmp += arr[i];
         }
         ans = Math.max(ans, tmp);
      }
      System.out.println(ans);

   }
}
