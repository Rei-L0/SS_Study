package boj20500;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 아무리 생각해도 생각이 안 나서 다른 사람 풀이 보고 이해해보려고 했는데 이해도 안 됨...
 * 아래는 베낀 코드고 제출 안 함...
 */
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
	    long MOD = 1000000007L;
	    int N = Integer.parseInt(br.readLine());
	
	    if (N == 1) {
	      System.out.println(0);
	      return;
	    }
	
	    long[][] dp = new long[3][N + 1];
	    dp[0][2] = dp[1][2] = 1;
	
	    for (int i = 3; i <= N; i++) {
	      dp[0][i] = (dp[1][i - 1] + dp[2][i - 1]) % MOD;
	      dp[1][i] = (dp[0][i - 1] + dp[2][i - 1]) % MOD;
	      dp[2][i] = (dp[0][i - 1] + dp[1][i - 1]) % MOD;
	    }
	    
	    System.out.println(dp[0][N]);
	}
}
