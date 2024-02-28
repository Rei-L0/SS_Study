import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 잡아둔 상담을 빨리 끝나는 순서대로 정렬한다.
// 반복문을 돌면서 해당 상담이 시작하는 날보다 빨리 끝나는 이전의 상담이 있다면
// -> dp[1]~dp[con[i].start] != 0 이 아닌 값이 있으면
	// 그 중 가장 큰 값과 현재 값을 더한 값과 현재 dp 값 중 최댓값을 저장함

// 없으면 현재 dp 값과 지금 값의 수익 중 최댓값을 저장함

// dp[n]에 최댓값이 있다는 보장이 X -> dp를 돌며 최댓값을 찾아 출력함

public class Main {

	static int n;
	static Con[] con; // 상담
	static int[] dp; // dp[n] 은 끝나는 날이 n일 때의 최대 수익을 말함
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		con = new Con[n+1];
		con[0] = new Con(0,0,0);
		for(int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			con[i] = new Con(i, Integer.parseInt(st.nextToken()) + i - 1, Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(con);
		int ans = 0;
		dp = new int[n+1];
		for(int i = 1; i <= n; i++) {
			int e = con[i].end;
			if(e > n) break;
			boolean flag = false;
			int now = 0;
			for(int j = con[i].start - 1; j > 0; j--) {
				if(dp[j] > now) {
					now = dp[j];
					flag = true;
				}
			}
			if(!flag) dp[e] = Math.max(dp[e], con[i].cost);
			else dp[e] = Math.max(dp[e], now + con[i].cost);
		}
		for(int i = n; i > 0; i--) {
			if(dp[i] > ans) ans = dp[i];
		}
		System.out.println(ans);
	}

	static class Con implements Comparable<Con>{
		int start, end, cost;

		public Con(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Con o) {
			return end - o.end;
		}
		
	}
}
