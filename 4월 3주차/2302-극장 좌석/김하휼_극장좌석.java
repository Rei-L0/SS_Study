import java.io.*;
import java.util.*;
 
class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[] dp = new int[41];

		// vip석이 없다고 생각했을 때 점화식
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for (int i=3; i<=N; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		} 
    
		int ans = 1; // 0으로 시작하면 곱했을 때 0이 나옴 
		int before = 0;

		// 고정자리 설정하고,  그 자리 제외한 나머지 경우의 수 곱셈 
		for (int i=0; i<M; i++) {
			int tmp = Integer.parseInt(br.readLine());
			ans *= dp[tmp-before-1];
			before = tmp;
		}

		ans *= dp[N-before];
		System.out.println(ans);
		

	}
}
	
