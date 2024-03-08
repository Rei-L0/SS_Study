import java.io.*;
import java.util.*;

public class Main {
   static int N, K, res;
   static int[] nums, ans;
   static boolean[] visited;

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;

      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());

      nums = new int[N];
      ans = new int[N];
      visited = new boolean[N];
      res = 0;

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < N; i++) {
         int w = Integer.parseInt(st.nextToken());
         nums[i] = w;
      }

      DFS(0, 500);
      System.out.println(res);

   }

   public static void DFS(int cnt, int sum) {

      if (cnt == N - 1) {
         res++;
         return;
      }

      for (int i = 0; i < N; i++) {
         if (!visited[i] && (sum + nums[i] - K) >= 500) {
            visited[i] = true;
            DFS(cnt + 1, sum + nums[i] - K);
            visited[i] = false;
         }

      }
   }
}
