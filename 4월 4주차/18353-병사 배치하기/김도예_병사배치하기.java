import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * dp LIS
 * N이 1일 때 1이 출력되어 실패했었음
 */
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] dp = new int[N];
		int[] fight = new int[N];
		Arrays.fill(dp, 1);
		int max = 0;
		fight[0] = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N; i++) {
			fight[i] = Integer.parseInt(st.nextToken());
			for(int j = 0; j < i; j++) {
				if(fight[i] < fight[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
				max = Math.max(max, dp[i]);
			}
		}
		System.out.println(N == 1 ? 0 : N - max);
	}
}
