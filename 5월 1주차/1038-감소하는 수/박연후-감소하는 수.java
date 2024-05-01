import java.io.*;
import java.util.*;

public class Main {

    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Long> ans = new ArrayList<>();

        for (int i = 0; i <= 9; i++) {
            solve(i, ans);
        }

        Collections.sort(ans);

        System.out.println((n < ans.size()) ? ans.get(n) : -1);
    }

    static void solve(long num, List<Long> ans) {
        ans.add(num);
        for (int i = (int) ((num % 10) - 1); i >= 0; i--) {
            solve(num * 10 + i, ans);
        }
    }

}