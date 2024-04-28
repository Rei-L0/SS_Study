import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_18353 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] numbers = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Long.parseLong(st.nextToken());
		}

		int[] dp = new int[N];
		dp[0] = 1;
		int result = 0;
		for (int i = 1; i < N; i++) {
			int input = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (numbers[i] < numbers[j]) {
					input = Math.max(dp[j], input);
				}
			}
			if (input == 0)
				dp[i] = 1;
			dp[i] = input + 1;
			result = Math.max(result, dp[i]);
		}
		if(result == 0) System.out.println(0);
		else System.out.println(N - result);
	}

}
