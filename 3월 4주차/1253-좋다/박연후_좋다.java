import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n, ans;

	static int[] num;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		num = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(num);

		for (int i = 0; i < n; i++) {
			int now = num[i];
			int start = 0;
			int end = n - 1;
			if (start == i)
				start++;
			if (end == i)
				end--;
			while (start < end) {
				int sum = num[start] + num[end];
				if (sum > now)
					end--;
				else if (sum < now)
					start++;
				else {
					ans++;
					break;
				}
				if (start == i)
					start++;
				if (end == i)
					end--;
			}
		}

		System.out.println(ans);

	}

}