import java.util.*;
import java.io.*;

public class 1학년_5557 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N]; // 입력 숫자 저장
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N - 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int total = Integer.parseInt(st.nextToken()); // 마지막 숫자

        long answer = 0; // 답
        
        // 메모이제이션 할 배열
        long[][] dp = new long[N + 1][21];
        dp[1][arr[1]] = 1; // 1 단계의 첫 번째 숫자를 만들 수 있는 경우의 수는 1
        
        // DP 시작
        for (int i = 1; i <= N - 2; i++) {
            // 0 ~ 20까지 각각 만들 수 있는 경우의 수 찾기
            for (int j = 0; j <= 20; j++) {
                // 만든 경우가 있을 때
                if (dp[i][j] != 0) {
                    // 현재 단계의 수에서 다음 단계 수를 더했을 때, 뺐을 때
                    int plus = j + arr[i + 1];
                    int minus = j - arr[i + 1];
                    // 범위 체크 + 경우의 수 반영
                    if (plus >= 0 && plus <= 20) {
                        dp[i + 1][plus] += dp[i][j];
                    }
                    if (minus >= 0 && minus <= 20) {
                        dp[i + 1][minus] += dp[i][j];
                    }
                }
            }
        }
        // 마지막 단계에서 찾는 수를 만들 수 있는 경우의 수
        answer = dp[N - 1][total];

        System.out.println(answer);
    }

}