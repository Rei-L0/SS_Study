import java.io.*;
import java.util.*;

public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;

	static int[] u;

	static Set<Integer> set;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		u = new int[n];
		set = new HashSet<>();

		for (int i = 0; i < n; i++)
			u[i] = Integer.parseInt(br.readLine());

		Arrays.sort(u);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				set.add(u[i] + u[j]);
			}
		}

		solve();

	}

	static void solve() {
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				if (set.contains(u[i] - u[j])) {
					System.out.println(u[i]);
					return;
				}
			}
		}
	}

}