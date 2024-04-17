import java.io.*;
import java.util.*;

// 극장
public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, m, ans = 1;

    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dp = new int[41];
        for (int i = 0; i < 41; i++) {
            if (i == 0 || i == 1) {
                dp[i] = 1;
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }

        int val = 0;
        int num = 0;
        for (int i = 0; i < m; i++) {
            num = Integer.parseInt(br.readLine());
            ans *= dp[num - val - 1];
            val = num;
        }
        ans *= dp[n - val];
        System.out.println(ans);
    }
}