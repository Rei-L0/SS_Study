import java.util.*;
import java.io.*;

class prac {
	static int N;
	static ArrayList<Long> ans = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		if (N<=10) {
			System.out.println(N);
		}
		else {
			for (int i=0; i<10; i++) {
				solve(i,1);
			}
			if (N >= ans.size()) {
				System.out.println(-1);
			}
			else {
				Collections.sort(ans);
				System.out.println(ans.get(N));
			}
		}
	}
	static void solve(long num, int cnt) {
		if (cnt > 10) return;

		ans.add(num);
		for (int i=0; i< (num % 10); i++) {
			solve(num*10+i, cnt+1);
		}
	}
}
