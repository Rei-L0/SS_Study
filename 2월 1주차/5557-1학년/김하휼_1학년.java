import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        long[][] dp = new long[n-1][21];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp[0][arr[0]] = 1;

        for (int i=1; i<n-1;i++) {
            for (int j=0; j<=20;j++) {
                if (j - arr[i] >= 0) dp[i][j] += dp[i-1][j-arr[i]];
                if (j + arr[i] <= 20) dp[i][j] += dp[i-1][j+arr[i]];
            }
        }
        sb.append(dp[n-2][arr[n-1]]);
        System.out.println(sb);
    }  
}
