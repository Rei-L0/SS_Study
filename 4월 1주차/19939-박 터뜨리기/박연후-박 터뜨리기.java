import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, k;

	static StringTokenizer st;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		int sum = 0;
		for (int i = 1; i < k + 1; i++)
			sum += i;
		n -= sum;

		if (n < 0) {
			System.out.println(-1);
		} else {
			if (n % k == 0)
				System.out.println(k - 1);
			else
				System.out.println(k);
		}

	}
}