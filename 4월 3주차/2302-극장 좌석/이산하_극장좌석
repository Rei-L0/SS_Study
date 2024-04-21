import java.io.BufferedReader;
import java.io.InputStreamReader;
// unsolved -> dp
public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int start = 0;
		int end;
		int result = 1;
		int tmp = 0;
		for (int i = 0; i < M; i++) {
			end = Integer.parseInt(br.readLine());
			tmp = end - start - 1;
			if (tmp == 0) {
				start = end;
				continue;
			} else {
				result *= tmp;
			}
			start = end;
		}

		if ((N - start) != 0) {
			result *= (N - start);
		}
		System.out.println(result);
	}
}
