import java.io.*;
import java.util.*;

public class B_퇴사_14501 {
    static int N, answer = 0;
    static int np[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        np = new int[N+1];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if(i!=0)
                np[i] = Math.max(np[i],np[i-1]);
            if(i+A<=N)
                np[i+A] = Math.max(np[i+A],np[i]+B);
        }
        for(int n : np) answer = Math.max(n, answer);
        System.out.println(answer);
    }
}
