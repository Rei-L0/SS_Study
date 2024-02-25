import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long LC,RC,sum;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        LC = 0;
        RC = 0;
        sum = 0;
        int idx = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp%2 == 0) {
                sum+=idx++;
                LC += i;
                RC += N-1-i;
            }
        }

        System.out.println(Math.min(LC, RC) - sum);

    }
}
