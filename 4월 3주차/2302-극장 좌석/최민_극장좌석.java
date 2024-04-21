import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int dp[]= new int[41];
		int M = Integer.parseInt(br.readLine());
		int vip[] = new int[M];
		
		for(int i=0; i<M ; i++) {
			vip[i]= Integer.parseInt(br.readLine());
		}
		
		dp[0]=1;
		dp[1]=1;
		dp[2]=2;

		for(int i=3; i<=N; i++) { //연속된 자리일 경우의 모든 수 
			dp[i] = dp[i-1]+dp[i-2];
		}
		
		int start=1;
		int result=1;
		for(int i=0; i<M; i++) {
			int end = vip[i];
			result*=dp[end-start];
			start= end+1;
		}
		if(M!=0 && vip[M-1]<N) { //M이 0일때도 생각해주어야 한다..!
			//System.out.println(N-start+1);
			result*=dp[N+1-start];
		}
        
		else if(M==0) { //M이 0인 경우는 dp[N]의 수를 result에 넣어준다.
			result=dp[N];
		}
        
		System.out.println(result);
		}	
}
