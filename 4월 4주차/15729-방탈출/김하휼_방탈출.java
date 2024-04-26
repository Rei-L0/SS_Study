import java.io.*;
import java.util.*;

/*
반복문 돌다가 break되는 구문 넣었는데, 거기서 시간초과가 떴었음..!
N이 백만까지라 그런듯
*/

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+2];
		int cnt = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<n; i++) {
			if (arr[i] == 1) {
        arr[i] = 0;
				arr[i+1] = arr[i+1] == 1 ? 0 : 1;
				arr[i+2] = arr[i+2] == 1 ? 0 : 1;
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
