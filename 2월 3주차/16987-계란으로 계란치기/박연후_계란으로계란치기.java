import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문제 조건에 맞춰 백트래킹을 시도하며 최대값을 구한다.

public class Main {

	static int n, ans;

	static void solve(int hand, int[][] arr) {
		if (hand == n) {
			int count = 0;
			for (int i = 0; i < n; i++) {
				if (arr[i][0] <= 0) {
					count++;
				}
			}
			ans = Math.max(ans, count);
			return;
		}
		boolean broken = false;
		for (int i = 0; i < n; i++) {
			if (i == hand) {
				continue;
			}
			if (arr[hand][0] <= 0) {
				solve(hand + 1, arr);
				break;
			}
			if (arr[i][0] > 0) {
				broken = true;
				arr[i][0] -= arr[hand][1];
				arr[hand][0] -= arr[i][1];
				solve(hand + 1, arr);
				arr[i][0] += arr[hand][1];
				arr[hand][0] += arr[i][1];
			}
		}
		if (!broken) {
			solve(hand + 1, arr);
		}
	}


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		int[][] egg = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			egg[i][0] = Integer.parseInt(st.nextToken());
			egg[i][1] = Integer.parseInt(st.nextToken());
		}

		solve(0, egg);
		System.out.println(ans);
	}
}