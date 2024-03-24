import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] numbers = new int[N];

		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		int min = numbers[0];
		int max = numbers[0];
		int result = 0;
		for (int i = 1; i < N; i++) {
			if (numbers[i - 1] < numbers[i]) {
				max = numbers[i];

			} else {
				max = numbers[i];
				min = Math.min(numbers[i], min);
			}
			result = Math.max(result, max - min);

		}
		System.out.println(result);

	}

}
