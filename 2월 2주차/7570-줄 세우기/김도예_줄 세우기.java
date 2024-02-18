import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] dp = new int[n+1];
		for(int i = 0; i < n; i++) {
			int k = Integer.parseInt(st.nextToken());
			dp[k] = dp[k-1]+1;
		}
		Arrays.sort(dp);
		System.out.println(n-dp[n]);
	}
}
