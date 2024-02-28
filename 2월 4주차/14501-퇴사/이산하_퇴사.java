import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
	[부족했던 점 CHECK !]
	0. dp 식 세우는 것에 어려움을 겪음 ..

 */

public class B_14501 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		int[][] timeTable = new int[N + 2][2];
		int[] dp = new int[N + 2];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());

			timeTable[i][0] = t;
			timeTable[i][1] = p;

		}
		for (int i = 1; i <= N + 1; i++) {

			for (int j = timeTable[i][0] + i; j <= N + 1; j++) {

				if (dp[j] < timeTable[i][1] + dp[i])
					dp[j] = timeTable[i][1] + dp[i];
			}

		}

		System.out.println(dp[N + 1]);
	}

}
