import java.io.*;
import java.util.*;

public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		int[] arr = new int[1_000_001];
		int ans = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int h = Integer.parseInt(st.nextToken());
			if (arr[h] > 0) {
				arr[h]--;
			}
			arr[h - 1]++;
		}

		for (int i : arr) {
			ans += i;
		}

		System.out.println(ans);
	}

}