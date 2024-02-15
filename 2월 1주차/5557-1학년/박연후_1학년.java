import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static final int MAX_SIZE = 21;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		long[][] dp = new long[n][MAX_SIZE];

		st = new StringTokenizer(br.readLine());
		int[] num = new int[n];
		for (int i = 0; i < n; i++)
			num[i] = Integer.parseInt(st.nextToken());

		dp[0][num[0]] = 1;

		for (int i = 1; i < n - 1; i++) {
			for (int j = 0; j < MAX_SIZE; j++) {
				if (dp[i - 1][j] > 0) {
					if (j - num[i] > -1)
						dp[i][j - num[i]] += dp[i - 1][j];
					if (j + num[i] < MAX_SIZE)
						dp[i][j + num[i]] += dp[i - 1][j];
				}
			}
		}
		System.out.println(dp[n - 2][num[n - 1]]);
	}

}