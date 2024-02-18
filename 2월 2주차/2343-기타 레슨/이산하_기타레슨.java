import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int sum, max;
	static int[] lectures;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		lectures = new int[N];
		sum = 0;
		max = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			lectures[i] = Integer.parseInt(st.nextToken());
			sum += lectures[i];
			max = Math.max(max, lectures[i]);
		}

		int left = max;
		int right = sum - max;
		int mid = (left + right) / 2;

		while (left <= right) {
			mid = (left + right) / 2;

			if (check(mid) <= M) {
				right = mid - 1;

			} else {
				left = mid + 1;

			}
		}
		System.out.println(left);
	}

	static int check(int m) {
		int count = 0;
		int sum = 0;

		for (int i = 0; i < N; i++) {
			if (sum + lectures[i] > m) {
				count++;
				sum = 0;
			}
			sum += lectures[i];

		}
		if (sum >= 0) {
			count++;
		}

		return count;
	}
}
