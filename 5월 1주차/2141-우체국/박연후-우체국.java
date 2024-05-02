import java.io.*;
import java.util.*;

public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;

	static long sum;

	static class Pos implements Comparable<Pos> {
		int x;
		int a;

		public Pos(int x, int a) {
			super();
			this.x = x;
			this.a = a;
		}

		@Override
		public int compareTo(Pos o) {
			return Integer.compare(this.x, o.x);
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		Pos[] p = new Pos[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			p[i] = new Pos(x, a);
			sum += a;
		}

		Arrays.sort(p);

		long people = 0;

		for (int i = 0; i < n; i++) {
			people += p[i].a;
			if (people >= (sum + 1) / 2) {
				System.out.println(p[i].x);
				break;
			}
		}

	}

}