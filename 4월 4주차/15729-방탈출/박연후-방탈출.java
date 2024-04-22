import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int[] num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            if (num[i] == 1) {
                ans++;
                for (int j = i; j < Math.min(i + 3, n); j++) {
                    num[j] ^= 1;
                }
            }
        }

        System.out.println(ans);

    }

}