import java.io.*;
import java.util.*;

public class Main {

	static int n, k, ans;

	static int[] num;

	// 키트 번호를 순열로 탐색한다.
	// 해당 키트 사용하여 중량 500미만이 될 경우 탐색 X
	static void solve(int count, int weight, boolean[] visit) {
		if (count == n) {
			ans++;
			return;
		}
		for (int i = 0; i < n; i++) {
			if (visit[i])
				continue;
			if (weight + num[i] - k >= 500) {
				visit[i] = true;
				solve(count + 1, weight + num[i] - k, visit);
				visit[i] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		num = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			num[i] = Integer.parseInt(st.nextToken());

		// 초기 중량 500에서 시작
		solve(0, 500, new boolean[n]);

		System.out.println(ans);
	}
}