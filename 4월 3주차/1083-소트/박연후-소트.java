import java.io.*;
import java.util.*;

public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, s;

	static int[] num;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		num = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		s = Integer.parseInt(br.readLine());

		for (int i = 0; i < n && s > 0; i++) {
			int max = num[i];
			int idx = i;
			for (int j = i + 1; j <= Math.min(n - 1, i + s); j++) {
				if (max < num[j]) {
					max = num[j];
					idx = j;
				}
			}
			s -= (idx - i);
			while (idx > i) {
				num[idx] = num[idx - 1];
				idx--;
			}
			num[i] = max;
		}

		for (int i = 0; i < n; i++) {
			sb.append(num[i] + " ");
		}
		System.out.println(sb);
	}
}