package algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_5557_1학년 {

    public static void main(String[] args) throws NumberFormatException, IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N+1];

        long[][] dp = new long[N][21];

        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 처음 경우의 수는 무조건 1
        dp[1][arr[1]]=1;

   
        for (int i = 2; i < N; i++) { //마지막 숫자 전까지
            for (int j = 0; j <= 20; j++) {
                if (dp[i - 1][j] > 0) { //(누적값 존재하는 경우)
                    if (j + arr[i] <= 20) {//그 누적값 + i번째 입력값을 더하는 경우
                        dp[i][j + arr[i]] += dp[i - 1][j]; //dp[i][j]에서 i번째는 입력한 1~i번까지 계산된거임
                    }
                    if (j - arr[i] >= 0) {//그 누적값 + i번째 입력값을 빼는 경우
                        dp[i][j - arr[i]] += dp[i - 1][j];
                    }
                }
            }
        }

        System.out.println(dp[N - 1][arr[N]]);

    }
}
