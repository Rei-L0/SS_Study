
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int A = 0;
		int B = 0;

		int[] dp = new int[31];

		outer: for (int i = 1; i <= 50000; i++) {
			dp[1] = i;
			for (int j = i; j <= 50000; j++) {
				dp[2] = j;
				for (int z = 3; z <= 30; z++) {
					dp[z] = dp[z - 1] + dp[z - 2];
					if (z == D) {
						if (dp[z] != K) {
							break;
						} else if (dp[z] == K) {
							A = i;
							B = j;
							break outer;
						}
					}

				}
			}
		}

		System.out.println(A);
		System.out.println(B);

	}
}
