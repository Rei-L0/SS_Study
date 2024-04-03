import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, l, r, al, ar;

	static long chk;

	static Long[] num;

	static StringTokenizer st;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		num = new Long[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Long.parseLong(st.nextToken());
		}

		l = 0;
		r = n - 1;
		al = 0;
		ar = 0;
		chk = Long.MAX_VALUE;

		while (l < r) {
			long sum = num[l] + num[r];
			if (chk > Math.abs(sum)) {
				chk = Math.abs(sum);
				al = l;
				ar = r;
			}
			if (sum > 0) {
				r--;
			} else {
				l++;
			}
		}

		System.out.print(num[al] + " " + num[ar]);
	}

}