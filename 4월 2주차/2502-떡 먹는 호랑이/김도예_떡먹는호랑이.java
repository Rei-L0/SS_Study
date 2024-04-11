import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
/*
 * 완탐 dp
 * 범위 설정이 좀 애매했음
 */
	static int D, K;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] dp = new int[D+1];
		for(int i = 1; i <= K/2; i++) {
			for(int j = 1; j < K; j++) {
				dp[1] = i;
				dp[2] = j;
				for(int d = 3; d <= D; d++) {
					dp[d] = dp[d-1] + dp[d-2];
				}
				if(dp[D] == K) {
					System.out.println(i);
					System.out.println(j);
					System.exit(0);

				}
			}
		}
	}
}
