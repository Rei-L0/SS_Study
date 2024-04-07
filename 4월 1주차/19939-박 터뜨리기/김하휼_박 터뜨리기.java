import java.io.*;
import java.util.*;
 
class prac {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[K];

		int res = (N-((K*(K+1))/2));

		if( res < 0 ) {
			System.out.println(-1);
			return;
		}

		for (int i=0; i<K; i++) {
			arr[i] = (res / K);
			N -= arr[i];
		}

		if (N == ((K*(K+1))/2)) System.out.println(K-1);
		
		else System.out.println(K);
	}
}
	

