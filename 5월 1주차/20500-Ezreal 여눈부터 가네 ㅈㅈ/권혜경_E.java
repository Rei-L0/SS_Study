import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    static int N;
    static int dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 5의 배수이면서 3으로 나눴을때의 나머지
        dp = new int[N+1][3];

        dp[1][0] = 0;
        dp[1][1] = 0;
        dp[1][2] = 0;

        if(N>=2){
            dp[2][0] = 1;
            dp[2][1] = 1;
            dp[2][2] = 0;
        }

        for(int i=3;i<=N;i++){
            dp[i][0] = (dp[i-1][2] + dp[i-1][1]) % 1_000_000_007;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % 1_000_000_007;;
            dp[i][2] = (dp[i-1][1] + dp[i-1][0]) % 1_000_000_007;;
        }

        System.out.println(dp[N][0]);
    }
}
