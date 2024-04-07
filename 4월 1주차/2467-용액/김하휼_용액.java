import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int min_v = Integer.MAX_VALUE;
		
		ArrayList<Integer> ans = new ArrayList<Integer>();
		int lt = 0;
		int rt = N-1;
		
		while (lt < rt) {			
			int tmp = Math.abs(0 + arr[lt] + arr[rt]);
			if (tmp <= min_v) {
				min_v = tmp;
				ans.clear();
				ans.add(arr[lt]);
				ans.add(arr[rt]);
				rt -= 1;
			}
			else {
				lt += 1;
			}
		}
		Collections.sort(ans);
		for (int x: ans) {
			sb.append(x).append(" ");
		}
		System.out.println(sb);
		
	}
}
