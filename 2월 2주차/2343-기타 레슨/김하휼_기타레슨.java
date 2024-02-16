import java.io.*;
import java.util.*;

public class Main {
	static int N,M;
	static int[] arr;
	static int min_v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min_v = Integer.MAX_VALUE;
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		bs();
		sb.append(min_v);
		System.out.println(sb);
		
	}
	
	public static void bs() {
		int lt = Arrays.stream(arr).max().getAsInt();
		int rt = Arrays.stream(arr).sum();
		int mid = 0;
		
		while (lt <= rt) {
			mid = (lt+rt) / 2;
			if (check(mid) <= M) {
				min_v = Math.min(min_v, mid);
				rt = mid - 1;
			}
			else lt = mid+1;
		}
	}
	
	public static int check(int size) {
		int cnt= 1;
		int sum=0;
		
		for (int i=0; i<N; i++) {
			if (sum+arr[i] > size) {
				cnt++;
				sum = arr[i];
			}
			else sum += arr[i];
		}
		return cnt;
	}	
}
