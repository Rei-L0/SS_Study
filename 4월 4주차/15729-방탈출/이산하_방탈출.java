import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_15729 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] mine = new int[N];
		int[] target = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		int result = 0;
		for (int i = 0; i < N; i++) {
			target[i] = Integer.parseInt(st.nextToken());
			if (mine[i] != target[i]) {
				for (int j = i; j < i + 3 && j < N; j++) {
					mine[j] = (mine[j] == 1) ? 0 : 1;
				}
				result += 1;
			}
		}
		System.out.println(result);
	}

}
