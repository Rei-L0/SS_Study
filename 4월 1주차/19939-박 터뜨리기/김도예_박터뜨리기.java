import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K, ans;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int sum  = 0;
    
    // sum: 바구니에 담을 수 있는 공의 최소 개수
		for(int i = 1; i <= K; i++) {
			sum += i;
		}
		N -= sum;
    // 담을 수 있는 최소 개수보다 공이 더 적으면 나눠 담을 수 없는 것
		if(N < 0) ans = -1;
      
    // 남은 공 개수가 K로 나누어 떨어지면 K-1, 아니면 K
		else {
			if(N % K == 0) ans = K - 1;
			else {
				ans = K;
			}
		}
		System.out.println(ans);
	}
}
