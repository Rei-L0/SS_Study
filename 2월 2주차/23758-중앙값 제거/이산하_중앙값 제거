import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> input = new ArrayList<>();
		int result = 0;

		for (int i = 0; i < N; i++) {
			input.add(Integer.parseInt(st.nextToken()));
		}

		Collections.sort(input);

		for (int i = 0; i < (N + 1) / 2; i++) {
			result += cal(input.get(i));
		}
		System.out.println(result + 1);
	}

	static int cal(int num) {
		int count = 0;
		while (num != 1) {
			num /= 2;
			count++;
		}
		return count;
	}
}
