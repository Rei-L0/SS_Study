package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_7570_줄세우기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
	//연속된 증가 수열 찾기!	//https://mygumi.tistory.com/276 //배열 길이-연속된 증가수열의 길이
		//ex 4 _2_ 1 _3_ 5 2 3이 가장 큰 증가수열
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr[] = new int[n+1];
		int count =0;
		for(int i=0; i<n; i++) {
			int num= Integer.parseInt(st.nextToken());
			arr[num] = arr[num-1]+1; //증가 수열 찾기
			System.out.println(num+" :" +arr[num]+" "+arr[num-1]+1);
			count = Math.max(count, arr[num-1]+1); //갱신
		}
		
		System.out.println(n-count);
		
	}
}
