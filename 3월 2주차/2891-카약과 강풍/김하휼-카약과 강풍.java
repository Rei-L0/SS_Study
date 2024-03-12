import java.util.*;
import java.io.*;

public class test {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		boolean[] injured = new boolean[N+1];
		boolean[] extra = new boolean[N+1];
		boolean[] ans = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<S; i++) {
			int s = Integer.parseInt(st.nextToken());
			injured[s] = true;
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<R; i++) {
			int r = Integer.parseInt(st.nextToken());
			if (injured[r] == true) {
				injured[r] = false;
			}
			else {
				extra[r] = true;
			}
		}

		for (int i=1; i<=N; i++) {
			if (injured[i]) {
				if (extra[i]) {
					extra[i] = false;
					injured[i] = false;
					ans[i] = true;
				}
				else continue;
			}
			else {
				ans[i] = true;
				if (extra[i]) {
					if (i == 1) {
						if (injured[i+1]) {
							ans[i+1] = true;
							injured[i+1] = false;
						}
					}
					else if (i == N) {
						if (injured[i-1]) {
							ans[i-1] = true;
							injured[i-1] = false;
						}
					} else {
						if (injured[i-1]) {
							ans[i-1] = true;
							injured[i-1] = false;
						}
						else if (injured[i+1]) {
							ans[i+1] = true;
							injured[i+1] = false;
						}
					}
				}
				else {
					continue;
				}
			}
		}
		int cnt=0;
		for (int i=1;i<=N;i++) {
			if (!ans[i]) cnt++;
		}
		System.out.println(cnt);
	}
}
