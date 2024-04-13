import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t =0; t<T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			int list[][] = new int[n][2];
			
			for(int i=0; i<n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				list[i][0] = Integer.parseInt(st.nextToken());
				list[i][1] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(list, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[0]==o2[0]) {
						return o1[1]-o2[1];
					}
					else {
						return o1[0]-o2[0];
					}
				}	
			});
			
//			for(int i=0; i<n; i++) {
//				System.out.println(list[i][0]+" "+list[i][1]);
//
//			}
			
			int count =1; 
			int a=list[0][1];
			for(int i=1; i<n; i++) {
				if(a>list[i][1]) {
					count++;
					a = list[i][1];
				}
			}
			
			sb.append(count).append("\n");
			
		}
		System.out.println(sb);
	}
}
