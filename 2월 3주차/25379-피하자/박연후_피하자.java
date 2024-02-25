import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 박연후_피하자 {

    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        long lCnt = 0;
        long rCnt = 0;
        long sum = 0;
        long idx = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num % 2 == 0) {
                sum += idx++;
                lCnt += i;
                rCnt += n - i - 1;
            }
        }

        System.out.println(Math.min(lCnt, rCnt) - sum);

    }
}