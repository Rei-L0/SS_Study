// dp 인건 알겠는데, 더 나은 방법이 있는지는 잘 모르겠습니다..
// 고민하면서 풀었습니다

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    static int N, answer = 1 ;
    static int input[], dp[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        input = new int[N];
        dp = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }
        dp[N-1] = 1;
        for(int i = N-2; i >= 0; i--){
            for(int j = i+1; j < N; j++){
                if(input[i] > input[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            if(dp[i] == 0) dp[i] = 1;
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(N-answer);
    }
}
