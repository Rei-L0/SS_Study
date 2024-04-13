import java.io.*;
import java.util.*;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int d, k;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        solve();
    }

    static void solve() {
        for (int b = 1; b <= 100_000; b++) {
            for (int a = 1; a <= b; a++) {
                int one = a;
                int two = b;
                int res = 0;
                for (int day = 3; day <= d; day++) {
                    res = (one + two);
                    one = two;
                    two = res;
                }
                if (res == k) {
                    System.out.println(a);
                    System.out.println(b);
                    return;
                }
            }
        }
    }
}