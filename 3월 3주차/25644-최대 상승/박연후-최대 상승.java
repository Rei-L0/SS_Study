import java.io.*;
import java.util.*;

public class Main {

    static int n, ans, min = Integer.MAX_VALUE;

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
            if (min > num[i]) {
                min = num[i];
            } else {
                ans = Math.max(ans, num[i] - min);
            }
        }

        System.out.println((ans == Integer.MAX_VALUE) ? 0 : ans);

    }
}