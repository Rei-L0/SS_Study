package b_3987_보이저1호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_25379_피하자 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int [] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		long count = 0;
		int len = 0 ;
		
		// 왼쪽 홀, 오른쪽 짝 // 짝수를 만나면 오른쪽으로 옮긴다.
		for(int i=0; i<n; i++) {
			if(arr[i]%2==0) {
				len++;
			}
			else {
				count+=len;
			}
			
		}
		
		long count1=0;
		int len1=0;
		
		for(int i=0; i<n; i++) { // 홀수를 만나면 오른쪽으로 옮긴다.
			if(! (arr[i]%2==0)) {//홀수를 만났다!
				len1++;
			}
			else {
				count1+=len1;
			}

		}
		
		System.out.println(Math.min(count1, count));
		
		
	}
}
