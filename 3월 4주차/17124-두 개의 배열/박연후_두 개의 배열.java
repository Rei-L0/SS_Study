import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int t, n, m;

    static int[] a, b;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(st.nextToken());

        for (int z = 0; z < t; z++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            a = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            b = new int[m];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(b);

            sb.append(solve()).append("\n");
        }
        System.out.print(sb);
    }

    static long solve() {
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += bs(a[i]);
        }
        return ans;
    }

    static long bs(int x) {
        int lo = -1;
        int hi = m;
        long res = Long.MAX_VALUE;
        long v = Integer.MAX_VALUE;
        while (lo + 1 < hi) {

            int mid = (lo + hi) / 2;
            long gap = Math.abs(b[mid] - x);

            if (gap < res) {
                res = gap;
                v = b[mid];
            } else if (gap == res) {
                v = Math.min(v, b[mid]);
            }
            if (b[mid] < x) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return v;
    }

}