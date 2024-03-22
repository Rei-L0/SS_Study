import java.io.*;
import java.util.*;

class P25644 { 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int min_v =arr[0];
		int benefit = 0;
		
		for (int i=1; i<N;i ++) {
			if (min_v > arr[i]) {
				min_v = arr[i];
			}
			else {
				benefit = Math.max(benefit, arr[i]-min_v);
			}
		}
		System.out.println(benefit);
	}
}
