import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken()); //K(1 ≤ K ≤ 2,000,000) 
		
		int[] arr = new int[1000001];
		
		for(int i =0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken()); //양동이 무게
			int b = Integer.parseInt(st.nextToken()); //양동이 좌표
			arr[b]=w;
		}
		
		int s = 0;
		int r = 2*k+1; //r의 크기가 1000001보다 클수도 있음
		
		if(r>1000000) r=1000001; //그런경우는 현재 존재하는 배열까지만 합을 구해준다.
		
		int sum=0; 
		for(int i=s; i<r; i++) {
			sum+=arr[i];
		}
		int max = sum;
		
		for(int i=0; i+2*k+1<=1000000; i++) {
			sum-=arr[i]; //앞에꺼 하나 빼고
			sum+=arr[i+2*k+1]; //뒤에꺼 하나 더하고
			
			max = Math.max(sum, max);
		}
		System.out.println(max);
	}
}
