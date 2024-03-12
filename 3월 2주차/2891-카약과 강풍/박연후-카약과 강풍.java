import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int n, s, r, ans = 11;

    static ArrayList<Integer> surplus;
    static boolean[] destroy;

    static void solve(int count, int start) {
        if (count == surplus.size()) {
            int cnt = 0;
            for (boolean x : destroy) {
                if (x) {
                    cnt++;
                }
            }
            ans = Math.min(ans, cnt);
            return;
        }
        for (int i = start; i < surplus.size(); i++) {
            int now = surplus.get(i);
            if (now - 1 > -1 && destroy[now - 1]) {
                destroy[now - 1] = false;
                solve(count + 1, i + 1);
                destroy[now - 1] = true;
            }
            if (now + 1 <= n && destroy[now + 1]) {
                destroy[now + 1] = false;
                solve(count + 1, i + 1);
                destroy[now + 1] = true;
            }
            if (destroy[now]) {
                destroy[now] = false;
                solve(count + 1, i + 1);
                destroy[now] = true;
            }
            solve(count + 1, i + 1);
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        destroy = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < s; i++) {
            int x = Integer.parseInt(st.nextToken());
            destroy[x] = true;
        }

        surplus = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < r; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (destroy[x]) {
                destroy[x] = false;
                continue;
            }
            surplus.add(x);
        }

        Collections.sort(surplus);

        solve(0, 0);

        System.out.println(ans);

    }
}