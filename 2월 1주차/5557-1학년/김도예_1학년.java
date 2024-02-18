import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] num;
	static long[][] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 숫자 개수
		num = new int[n];
		dp = new long[n][21]; // 연산자 n개 선택했을 때 결과가 k인 경우의 수 == dp[n][k]
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		dp[0][num[0]] = 1;
		for(int i = 1; i < n - 1; i++) {
			for(int j = 0; j < 21; j++) {
				if(j + num[i] <= 20) {
					dp[i][j + num[i]] += dp[i-1][j];
				}
				if(j - num[i] >= 0) {
					dp[i][j - num[i]] += dp[i-1][j];
				}
			}

		}
		System.out.println(dp[n - 2][num[n - 1]]);

	}
}
