import java.io.*;
import java.util.*;

class Main { 
	static int n,m;
	static int[] a,b;
	public static void main(String[] args)  throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for (int tc=0; tc<t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			a = new int[n];
			b = new int[m];
			long answer = 0;

			st = new StringTokenizer(br.readLine());
			for (int i=0; i<n; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i=0; i<m; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(b);

			for (int i=0; i<n; i++) {
				int tmp = bs(a[i]);
				answer += tmp;

			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);

	}

	public static int bs(int target) {
		int lt = 0;
		int rt = m-1;
		int gap = Integer.MAX_VALUE;
		int ans = 0;
		
		while (lt <= rt) {
			int mid = (lt+rt) / 2;
			int tmp = Math.abs(target - b[mid]);

			if (tmp < gap) {
				gap = tmp;
				ans = b[mid];
			}
			else if (tmp == gap && b[mid] < ans) {
				ans = b[mid];
			}
		
			if (b[mid] < target) {
				lt = mid+1;
			}
			else {
				rt = mid-1;
			}

		}
		return ans;
	}
}
