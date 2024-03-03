package Baekjoon;

import java.io.*;
import java.util.*;

public class Main {
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      int n = Integer.parseInt(br.readLine());
      int[] time = new int[n];
      int[] price = new int[n];
      for (int i = 0; i < n; i++) {
         st = new StringTokenizer(br.readLine());
         time[i] = Integer.parseInt(st.nextToken());
         price[i] = Integer.parseInt(st.nextToken());
      }

      int[] dp = new int[n + 1];

      for (int i = 0; i < n; i++) {
         if (i + time[i] <= n) {
            dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + price[i]);
         }
         dp[i + 1] = Math.max(dp[i + 1], dp[i]);
      }
      System.out.println(dp[n]);
   }
}
