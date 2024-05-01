import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int m = 1_000_000_007;

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[3][n + 1];

        if (n == 1) {
            System.out.println(0);
            return;
        }

        dp[0][2] = dp[1][2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[0][i] = (dp[1][i - 1] + dp[2][i - 1]) % m;
            dp[1][i] = (dp[0][i - 1] + dp[2][i - 1]) % m;
            dp[2][i] = (dp[1][i - 1] + dp[1][i - 1]) % m;
        }

        System.out.println(dp[0][n]);
    }

}