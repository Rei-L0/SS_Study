import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		long[] sum = new long[N];
		int min = Integer.MAX_VALUE;
		int minIdx = -1;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			if (i == 0) {
				sum[i] = numbers[i];
			} else {
				sum[i] = sum[i - 1] + numbers[i];
				if (i != N - 1) {
					if (min > numbers[i]) {
						min = numbers[i];
						minIdx = i;
					}
				}
			}
		}

		long case1 = -1, case2 = -1, case3 = -1;

		for (int i = 1; i < N - 1; i++) {
			case1 = Math.max(case1, (sum[i] - numbers[0]) + (sum[N - 2] - sum[i - 1]));
			case2 = Math.max(case2, (sum[N - 1] - numbers[0] - numbers[i]) + (sum[N - 1] - sum[i]));
			case3 = Math.max(case3, (sum[i - 1]) + (sum[N - 2] - numbers[i]));

		}

		long result = Math.max(Math.max(case1, case2), case3);
		System.out.println(result);
	}
}
