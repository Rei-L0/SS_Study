import java.io.*;
import java.util.*;

class Main { 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int cnt = 1;
		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) break;
			else {
				ArrayList<String> names = new ArrayList<>();
				for (int i=0; i<n; i++) {
					names.add(br.readLine());
				}
				
				int[] arr = new int[n+1];
				for (int i=0; i<2*n-1; i++) {
					st = new StringTokenizer(br.readLine());
					int num = Integer.parseInt(st.nextToken());
					String alpha = st.nextToken();
					arr[num]++;
				}

				for (int i =1; i<n+1; i++) {
					if (arr[i] == 1) {
						sb.append(cnt).append(" ");
						sb.append(names.get(i-1)).append("\n");
						break;
					}
				}
			}
			cnt++;
		}
		System.out.println(sb);
	}
}
