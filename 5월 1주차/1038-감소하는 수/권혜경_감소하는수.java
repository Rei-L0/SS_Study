import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static boolean end = false;
    static int N, L, sum;
    static int dp[][], num[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        N = Integer.parseInt(br.readLine());
        if(N<=10) {
            System.out.println(N);
            return;
        }
        dp = new int[11][10];
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1;
        dp[1][3] = 1;
        dp[1][4] = 1;
        dp[1][5] = 1;
        dp[1][6] = 1;
        dp[1][7] = 1;
        dp[1][8] = 1;
        dp[1][9] = 1;
        sum = 9;
        // i 자리수
        // j 로 시작하는 수
        // i-1번째에서 j-1 까지의 수들은 j를 앞에 추가할 수 있음
        total : for(int i=2;i<=10;i++){
            dp[i][i-1] = 1;
            sum++;
            if(sum==N) {
                for(int j=i-1;j>=0;j--)
                    sb.append(j);
                break;
            }
            for(int j=i;j<10;j++){
                dp[i][j] = dp[i][j-1] + dp[i-1][j-1];

                if(sum+dp[i][j] >= N) {
                    check(i, j);
                    break total;
                }
                sum += dp[i][j];                  
            }
        }
        if(sb.length()==0) sb.append(-1);
        System.out.println(sb.toString());
    }
    static void check(int a, int b){
        num = new int[a];
        num[0] = b;
        L = a;
        go(1, b);
    }
    static void go(int idx, int v){
        if(idx==L) {
            sum++;
            if(sum == N) {
                end = true;
                for(int i=0;i<L;i++){
                    sb.append(num[i]);
                }
            }
            return;
        }
        else if(v>=10) return;
        for(int i=0;i<v;i++){
            num[idx] = i;
            go(idx+1, i);
            if(end) return;
        }
    }
}
