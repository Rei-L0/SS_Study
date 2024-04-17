import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * dp
 * dp[n] = m : vip가 아닌 n명의 사람이 쭈르륵 앉을 수 있는 경우의 수 m
 * dp[0]을 1로 설정해두어야 함!
 */
public class Main {

	static int N, M, ans = 1;
	static int[] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		dp = new int[41];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3; i <= N; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}

		int before = 0;
		for(int i = 0; i < M; i++) {
			int num = Integer.parseInt(br.readLine());
			ans *= dp[num - before -1];
			before = num;
		}
		ans *= dp[N - before];
		System.out.println(ans);
	}
}
