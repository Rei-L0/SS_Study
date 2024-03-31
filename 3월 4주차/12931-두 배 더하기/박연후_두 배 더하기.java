import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, ans;

    static int[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        ans = 0;
        while (check()) {
            for (int i = 0; i < n; i++) {
                if (num[i] % 2 != 0) {
                    num[i]--;
                    ans++;
                }
            }
            for (int i = 0; i < n; i++) {
                num[i] /= 2;
            }
            if (check()) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    static boolean check() {
        for (int i = 0; i < n; i++) {
            if (num[i] != 0) {
                return true;
            }
        }
        return false;
    }
}