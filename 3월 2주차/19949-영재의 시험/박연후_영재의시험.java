import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int res;

    static int[] ans = new int[10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            ans[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, 0, 0, 0);

        System.out.println(res);

    }

    static void solve(int cnt, int idx, int before, int num) {
        if (idx == 10) {
            if (cnt >= 5) {
                res++;
            }
            return;
        }
        for (int i = 1; i <= 5; i++) {
            if (num == i) {
                if (before == 2) {
                    continue;
                }
                if (ans[idx] == i) {
                    solve(cnt + 1, idx + 1, before + 1, i);
                } else {
                    solve(cnt, idx + 1, before + 1, i);
                }
            } else {
                if (ans[idx] == i) {
                    solve(cnt + 1, idx + 1, 1, i);
                } else {
                    solve(cnt, idx + 1, 1, i);
                }
            }
        }

    }

}