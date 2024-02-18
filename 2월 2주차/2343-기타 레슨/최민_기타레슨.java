package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_2343_기타레슨 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		st=new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		int left = getMax(arr); // 가장 작은 범위값
		int right = getSum(arr);//가장 큰 범위값
		
		while (left<=right) {
			int mid=(left+right)/2;
			
			int count=0;
			int sum=0;
			
			for(int i=0; i<n; i++) {
				if(sum+arr[i]>mid) {
					count++;
					sum=0;
				}
				sum+=arr[i];
			}
			
			if (count >= m) { //count가 크면 길이를 더 길게
				left=mid+1;
			}
			else {
				right = mid -1;//아니라면 짧게
				answer=mid; 
			}
		}
		System.out.println(answer);
	}
	
	 private static int getMax(int[] arr) {
	        int max = Integer.MIN_VALUE;
	        for (int num : arr) {
	            max = Math.max(max, num);
	        }
	        return max;
	    }

	    private static int getSum(int[] arr) {
	        int sum = 0;
	        for (int num : arr) {
	            sum += num;
	        }
	        return sum;
	    }
}
