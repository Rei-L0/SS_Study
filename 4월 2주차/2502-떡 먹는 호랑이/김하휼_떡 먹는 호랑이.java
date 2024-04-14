import java.io.*;
import java.util.*;

// 백준에서 틀려서 반례 보려고 정올 돌렸는데 정답 뜨네요..! 뭐가 틀렸을까요 ㅠ

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

		outer: for (int i = 1; i <= 30; i++) {
			dp[1] = i;
			for (int j = 1; j <= 30; j++) {
				dp[2] = j;
				for (int z = 3; z <= 30; z++) {
					dp[z] = dp[z - 1] + dp[z - 2];
					if (z == D) {
						if (dp[D] != K) {
							break;
						} else if (dp[D] == K) {
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
