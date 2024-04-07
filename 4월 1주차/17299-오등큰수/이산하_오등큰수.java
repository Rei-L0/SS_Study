import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;

// 시간초과를 해결한 방법
// 1. 빈도수를 저장하는 HashMap -> 배열
// 2. stringbuilder 사용

public class B_17299 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int numbers[] = new int[N];

		int[] F = new int[1000001];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());

			F[numbers[i]] += 1;

		}

		Deque<Integer> stack = new ArrayDeque<>();
		int[] result = new int[N];

		StringBuilder sb = new StringBuilder();

		// 반대로 하나씩 넣으면서 지금 들어오는 수와 가장 위에 있는 수의 빈도수를 비교하여
		// 현재 들어온 수의 빈도수가 가장 위에 있는 수의 빈도수보다 작으면
		// 현재 들어온 수의 result_idx 값에 가장 위에 있는 수를 넣어줌.
		for (int i = N - 1; i >= 0; i--) {
			int now = numbers[i];

			if (stack.isEmpty()) {
				stack.push(now);
				result[i] = -1;
			} else {
				while (!stack.isEmpty() && (F[stack.peek()] <= F[now])) {
					stack.pop();
				}
				if (stack.isEmpty()) {
					result[i] = -1;
				} else {
					result[i] = stack.peek();
				}
				stack.push(now);
			}
		}
		for (int i = 0; i < N; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.print(sb);
	}

}
