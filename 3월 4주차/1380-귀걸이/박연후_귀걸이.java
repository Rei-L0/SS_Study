import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;

    static String[] names;
    static int[] ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int idx = 1;
        
        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            names = new String[n + 1];
            ans = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                names[i] = br.readLine();
            }

            for (int i = 0; i < 2 * n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken());
                char c = st.nextToken().charAt(0);
                ans[num]++;
            }
            for (int i = 1; i < n + 1; i++) {
                if (ans[i] == 1) {
                    sb.append(idx++).append(" ").append(names[i]).append("\n");
                }
            }
        }

        System.out.println(sb);
    }

}