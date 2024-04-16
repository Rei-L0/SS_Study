import java.io.*;
import java.util.*;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int t, n, ans;

    static Score[] num;

    static class Score implements Comparable<Score> {

        int x;
        int y;

        public Score(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Score o) {
            if (this.x == o.x) {
                return Integer.compare(this.y, o.y);
            }
            return Integer.compare(this.x, o.x);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());

        for (int z = 0; z < t; z++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            num = new Score[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                num[i] = new Score(a, b);
            }

            Arrays.sort(num);

            int f = 0;
            int s = 0;
            ans = 0;
            for (int i = 0; i < n; i++) {
                Score now = num[i];
                if (f == 0 && s == 0) {
                    ans++;
                    f = now.x;
                    s = now.y;
                } else {
                    if (f > now.x || s > now.y) {
                        ans++;
                        f = now.x;
                        s = now.y;
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}