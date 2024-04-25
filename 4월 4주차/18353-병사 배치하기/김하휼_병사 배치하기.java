import java.io.*;
import java.util.*;

/*
최장감소수열로 풀었습니다요
*/

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<N; i++) {
			dp[i] = 1;
			for (int j=0;j<i;j++) {
				if (arr[i] < arr[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}		
		System.out.println(N - Arrays.stream(dp).max().getAsInt());
	}
}
