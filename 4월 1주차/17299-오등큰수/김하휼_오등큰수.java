import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		HashMap<Integer,Integer> hm = new HashMap<>();
		Stack<Integer> stack = new Stack<>();

		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			int k = Integer.parseInt(st.nextToken());
			arr[i] = k;
			hm.put(k,hm.getOrDefault(k,0)+1);
			
		}
		int[] ans = new int[n];
		Arrays.fill(ans, -1);
		
		for (int i=0; i<n; i++) {
			if (stack.isEmpty()) {
				stack.add(i);
			}
			else {
				if (hm.get(arr[stack.peek()]) < hm.get(arr[i])) {
					while (!stack.isEmpty() && hm.get(arr[stack.peek()]) < hm.get(arr[i])) {
						ans[stack.peek()] = arr[i];
						stack.pop();
					}
					stack.add(i);
				} else if (hm.get(arr[stack.peek()]) >= hm.get(arr[i])) {
					stack.add(i);
					
				} 
			}
		}
		for (int x: ans) {
			sb.append(x).append(" ");
		}
		System.out.println(sb);	
	}
}
