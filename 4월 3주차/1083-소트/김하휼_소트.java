import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int s = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		int idx = 0;
		while (true) {
			if (cnt == s) break;
			if (idx >= N-1) break;
			if (arr[idx] < arr[idx+1]) {
				int tmp = arr[idx];
				arr[idx] = arr[idx+1];
				arr[idx+1] = tmp;
				idx=0;
				cnt++;
			}
			else {
				idx++;
			}
		}
		for (int x: arr) {
			sb.append(x).append(" ");
		}
		System.out.println(sb);
		
	}
}
