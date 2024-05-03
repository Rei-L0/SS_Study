import java.io.*;
import java.util.*;

public class boj {
	static int N,max_v;
	static int[] arr ;
	static ArrayList<Integer> nums;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		nums = new ArrayList<Integer>();
		max_v = Integer.MIN_VALUE;
		
		for (int i=0; i<N; i++) {
			arr[i]= Integer.parseInt(br.readLine());
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				nums.add(arr[i] + arr[j]);
			}
		}
		
		Collections.sort(nums);
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (bs(arr[i]-arr[j])) {
					max_v = Math.max(max_v, arr[i]);
				}
			}
		}
		System.out.println(max_v);
		
	}
	
	static boolean bs(int val) {
		int lt = 0;
		int rt = nums.size() - 1;
		
		while (lt <= rt) {
			int mid = (lt+rt) / 2;
			
			if (nums.get(mid) < val) {
				lt = mid+1;
			}
			else if (nums.get(mid) > val) {
				rt = mid - 1;
			}
			else {
				return true;
			}
		}
		return false;
	}
}
