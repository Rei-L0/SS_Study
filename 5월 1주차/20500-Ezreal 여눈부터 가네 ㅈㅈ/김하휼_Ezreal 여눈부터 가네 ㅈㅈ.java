import java.util.*;
import java.io.*;

class prac {
	static int MOD = 1000000007;
  
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		if (N == 1) {
			System.out.println(0);
			System.exit(0);
		}

		long[][] dp = new long[3][N+1];
		dp[0][2] = 1;
		dp[1][2] = 1;

		for (int i=3; i<=N; i++) {
			dp[0][i] = (dp[1][i-1] + dp[2][i-1]) % MOD;
			dp[1][i] = (dp[0][i-1] + dp[2][i-1]) % MOD;
			dp[2][i] = (dp[0][i-1] + dp[1][i-1]) % MOD;
		}

		System.out.println(dp[0][N] % MOD);
	}
}
